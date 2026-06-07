import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');

interface OpNot {
	type: "NOT";
	input: string;
	output: string;
}
interface OpOr {
	type: "OR";
	input1: string;
	input2: string;
	output: string;
}
interface OpAnd {
	type: "AND";
	input1: string | number;
	input2: string | number;
	output: string;
}
interface OpShift {
	type: "LSHIFT" | "RSHIFT";
	input: string;
	shift: number;
	output: string;
}
interface OpAssign {
	type: "ASSIGN";
	input: string | number;
	output: string;
}
type Operation = OpNot | OpOr | OpAnd | OpShift | OpAssign;

const signals: Map<string, number> = new Map();
const operations: Operation[] = [];

input.trim().split("\n").forEach((line) => {
	const [left, output] = line.split(" -> ").map(s => s.trim());
	const parts = left.split(" ");
	let op: OpNot | OpOr | OpAnd | OpShift | OpAssign;

	if (parts.length === 1) {
		op = { type: "ASSIGN", input: isNaN(Number(parts[0])) ? parts[0] : Number(parts[0]), output };
	} else if (parts.length === 2) {
		op = { type: "NOT", input: parts[1], output };
	} else if (parts.length === 3) {
		if (parts[1] === "AND") {
			op = { type: "AND", input1: isNaN(Number(parts[0])) ? parts[0] : Number(parts[0]), input2: isNaN(Number(parts[2])) ? parts[2] : Number(parts[2]), output };
		} else if (parts[1] === "OR") {
			op = { type: "OR", input1: parts[0], input2: parts[2], output };
		} else if (parts[1] === "LSHIFT" || parts[1] === "RSHIFT") {
			op = { type: parts[1] as "LSHIFT" | "RSHIFT", input: parts[0], shift: Number(parts[2]), output };
		} else {
			throw new Error(`Unknown operation: ${parts[1]}`);
		}
	} else {
		throw new Error(`Invalid instruction format: ${line}`);
	}

	operations.push(op);
});

while (operations.length > 0) {
	let progressed = false;

	for (let i = 0; i < operations.length; i++) {
		const op = operations[i];
		let result: number | null = null;

		switch (op.type) {
			case "ASSIGN":
				if (typeof op.input === "number" || signals.has(op.input)) {
					signals.set(op.output, typeof op.input === "number" ? op.input : signals.get(op.input) ?? 0);
					progressed = true;
					operations.splice(i, 1);
					i--;
					continue;
				}
				break;
			case "NOT":
				if (signals.has(op.input)) {
					signals.set(op.output, ~(signals.get(op.input) ?? 0) & 0xFFFF);
					progressed = true;
					operations.splice(i, 1);
					i--;
					continue;
				}
				break;
			case "AND":
				if (typeof op.input1 === "number" || signals.has(op.input1)) {
					if (typeof op.input2 === "number" || signals.has(op.input2)) {
						signals.set(op.output, (typeof op.input1 === "number" ? op.input1 : signals.get(op.input1) ?? 0) & (typeof op.input2 === "number" ? op.input2 : signals.get(op.input2) ?? 0));
						progressed = true;
						operations.splice(i, 1);
						i--;
						continue;
					}
				}
				break;
			case "OR":
				if (signals.has(op.input1) && signals.has(op.input2)) {
					signals.set(op.output, (signals.get(op.input1) ?? 0) | (signals.get(op.input2) ?? 0));
					progressed = true;
					operations.splice(i, 1);
					i--;
					continue;
				}
				break;
			case "LSHIFT":
				if (signals.has(op.input)) {
					signals.set(op.output, ((signals.get(op.input) ?? 0) << op.shift) & 0xFFFF);
					progressed = true;
					operations.splice(i, 1);
					i--;
					continue;
				}
				break;
			case "RSHIFT":
				if (signals.has(op.input)) {
					signals.set(op.output, (signals.get(op.input) ?? 0) >> op.shift);
					progressed = true;
					operations.splice(i, 1);
					i--;
					continue;
				}
				break;
		}
	}

	if (!progressed) {
		throw new Error("No progress made, possible circular dependency or missing signal");
	}
}

console.log(signals.get("a"));
