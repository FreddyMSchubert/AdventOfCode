import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let niceStringCount: number = 0;
input.trim().split("\n").forEach((line) => {
  // one repeating sequence
  if (!/(.)(.).*\1\2/.test(line)) return;

  // one double-letter with another in between
  if (!/(.).\1/.test(line)) return;

  niceStringCount++;
});

console.log("Amount of nice strings found: " + niceStringCount);
