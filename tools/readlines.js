import fs from 'node:fs';
import readline from 'node:readline';

/**
 * Return readline interface for file
 *
 * @param pathToFile {string}
 * @return {Interface}
 */
export function readLinesInterface(pathToFile) {
  const fileStream = fs.createReadStream(pathToFile);

  return readline.createInterface({
    input: fileStream,
    crlfDelay: Infinity
  });
}

/**
 * Read file line by line using AsyncIterator
 *
 * @param pathToFile {string}
 * @return {AsyncIterator}
 */
export function readLinesIterator(pathToFile) {
  const readLineInterface = readLinesInterface(pathToFile);

  return readLineInterface[Symbol.asyncIterator]();
}

