import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');

let newlyEscapingChars: number = 0;
for (let line of input.trim().split("\n")) {
  let escapedCharsCount: number = 0;
  for (const c of line) {
    if ("\\\"".includes(c))
      escapedCharsCount++;
  }
  newlyEscapingChars += 2 + escapedCharsCount;
}

console.log("Newly Added Escaping Chars: " + newlyEscapingChars);
