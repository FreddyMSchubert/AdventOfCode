import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

const map = new Set<string>();

function key(r: number, c: number): string {
  return `${r},${c}`;
}

let currCol: number = 0;
let currRow: number = 0;
let roboCurrCol: number = 0;
let roboCurrRow: number = 0;

let currMove: number = 0;
while (currMove < input.length) {
  map.add(key(currCol, currRow));
  let c = input[currMove++];
  if (c == ">")
    currCol++;
  else if (c == "<")
    currCol--;
  else if (c == "v")
    currRow++;
  else if (c == "^")
    currRow--;

  map.add(key(roboCurrCol, roboCurrRow));
  c = input[currMove++];
  if (c == ">")
    roboCurrCol++;
  else if (c == "<")
    roboCurrCol--;
  else if (c == "v")
    roboCurrRow++;
  else if (c == "^")
    roboCurrRow--;
}

console.log("Unique Houses visited: " + map.size);
