import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');

let irrelevantCharCount: number = 0;
for (let line of input.trim().split("\n")) {
  console.log("Now cleaning up line:" + line);

  const codeChars: number = line.length;
  line = line.slice(1, -1);

  while (true) {
    let doubleBackslashIndex = line.indexOf("\\\\");
    if (doubleBackslashIndex >= 0)
      line = line.slice(0, doubleBackslashIndex) + "_" + line.slice(doubleBackslashIndex + 2);
    else
      break;
  }

  while (true) {
    let escapedExclamMarkIndex = line.indexOf("\\\"");
    if (escapedExclamMarkIndex >= 0)
      line = line.slice(0, escapedExclamMarkIndex) + line.slice(escapedExclamMarkIndex + 1);
    else
      break;
  }

  while (true) {
    let escapedXIndex = line.indexOf("\\x");
    if (escapedXIndex >= 0)
      line = line.slice(0, escapedXIndex) + line.slice(escapedXIndex + 3);
    else
      break;
  }

  console.log("Cleaned up line: " + line);

  const contentChars: number = line.length;

  irrelevantCharCount += codeChars - contentChars;
}

console.log("Irrelevant Chars Count: " + irrelevantCharCount);
