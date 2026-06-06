import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let niceStringCount: number = 0;
input.trim().split("\n").forEach((line) => {
  // at least 3 vowels
  if ((line.match(/[aeiou]/g)?.length ?? 0) < 3) return;

  // one double-letter
  if (!/(.)\1/.test(line)) return;

  // forbidden letter sequences
  if (/ab|cd|pq|xy/.test(line)) return;
  
  niceStringCount++;
});

console.log("Amount of nice strings found: " + niceStringCount);
