import * as fs from 'node:fs';

const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

let totalRibbonNeeded = 0;

for (const l of input.trim().split("\n")) {
  const dimensions: string[] = l.split("x");
  const ldim: number = Number.parseInt(dimensions[0]);
  const wdim: number = Number.parseInt(dimensions[1]);
  const hdim: number = Number.parseInt(dimensions[2]);

  console.log("Checking dimensions " + ldim + "x" + wdim + "x" + hdim + ". :)");

  const ribbonPresent: number = Math.min(2 * ldim + 2 * wdim, 2 * ldim + 2 * hdim, 2 * hdim + 2 * wdim);
  const ribbonBow: number = ldim * wdim * hdim;

  totalRibbonNeeded += ribbonBow + ribbonPresent;
}

console.log("Total Ribbon Length Needed: " + totalRibbonNeeded);

