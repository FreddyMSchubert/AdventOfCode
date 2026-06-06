import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let level: number = 0;
for (const c of input) {
  if (c == "(")
    level++;
  else if (c == ")")
    level--;
}
console.log("Final Level: " + level);
