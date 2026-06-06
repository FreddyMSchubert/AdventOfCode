import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let level: number = 0;
for (let i = 0; i < input.length; i++) {
  const c: string = input.charAt(i);
  if (c == "(")
    level++;
  else if (c == ")")
    level--;
  if (level < 0) {
    console.log("Entered basement at step " + (i + 1));
    break;
  }
}
