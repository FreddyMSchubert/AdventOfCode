import * as fs from 'node:fs';
const input: string = fs.readFileSync('input.txt', 'utf8');
console.log("The input is: " + input);

interface coord {
  x: number;
  y: number;
}

function parseCoords(coordsString: string): coord {
  let coordstrings: string[] = coordsString.split(",");
  return {
    x: Number(coordstrings[0]),
    y: Number(coordstrings[1]),
  };
}

enum OperationType {
  TurnOff,
  TurnOn,
  Toggle
}

let lights: number[][] = [];
for (let i = 0; i < 1000; i++) {
  lights[i] = [];
  for (let j = 0; j < 1000; j++) {
    lights[i][j] = 0;
  }
}

input.trim().split("\n").forEach((line) => {
  // slice off unnecessary info to make parsing of different space amount including args easier
  let args: string[] = line.slice(5).split(" ");
  
  let coord1: coord = parseCoords(args[1]);
  let coord2: coord = parseCoords(args[3]);

  let opType: OperationType | null = null;
  if (args[0] == "on")
    opType = OperationType.TurnOn;
  else if (args[0] == "off")
    opType = OperationType.TurnOff;
  else if (args[0] == "e")
    opType = OperationType.Toggle;

  if (opType == null) {
    console.error("Unable to parse operationType");
    return;
  }

  for (let y = coord1.y; y <= coord2.y; y++) {
    for (let x = coord1.x; x <= coord2.x; x++) {
      if (opType == OperationType.TurnOff)
        lights[y][x] = Math.max(0, lights[y][x] - 1);
      else if (opType == OperationType.TurnOn)
        lights[y][x]++;
      else if (opType == OperationType.Toggle)
        lights[y][x] += 2;
    }
  }
});

let lightsOnCount = 0;
for (let y = 0; y < 1000; y++)
  for (let x = 0; x < 1000; x++)
    lightsOnCount += lights[y][x];

console.log("Lights activated: " + lightsOnCount);
