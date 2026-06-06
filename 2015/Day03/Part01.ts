import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

const map = new Set<string>();

function key(r: number, c: number): string {
  return `${r},${c}`;
}

let currCol: number = 0;
let currRow: number = 0;
for (const c of input) {
  map.add(key(currCol, currRow));

  if (c == ">")
    currCol++;
  else if (c == "<")
    currCol--;
  else if (c == "v")
    currRow++;
  else if (c == "^")
    currRow--;
}

console.log("Unique Houses visited: " + map.size);
