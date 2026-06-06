import * as fs from 'node:fs';

const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let totalWrappingPaperNeeded = 0;

for (const l of input.trim().split("\n")) {
  const dimensions: string[] = l.split("x");
  const ldim: number = Number.parseInt(dimensions[0]);
  const wdim: number = Number.parseInt(dimensions[1]);
  const hdim: number = Number.parseInt(dimensions[2]);

  console.log("Checking dimensions " + ldim + "x" + wdim + "x" + hdim + ". :)");

  const required: number = 2 * ldim * wdim + 2 * wdim * hdim + 2 * hdim * ldim;
  // const slack: number = ldim * wdim * hdim / Math.max(wdim, Math.max(ldim, hdim));
  const slack: number = Math.min(ldim * wdim, wdim * hdim, hdim * ldim);

  totalWrappingPaperNeeded += required + slack;
}

console.log("Total Wrapping Paper Needed: " + totalWrappingPaperNeeded);

