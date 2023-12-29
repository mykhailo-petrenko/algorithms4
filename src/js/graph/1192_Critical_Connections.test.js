import criticalConnections from './1192_Critical_Connections.js';
import fs from 'node:fs';
import readline from 'node:readline';

class TestDataBuilder {
  _data = {};
  _out = null;

  constructor() {
    this._reset();
  }

  _reset() {
    this._data = {
      n: 0,
      connections: null,
      expected: null
    };
  }

  setN(v) {
    this._data.n = parseInt(v);
  }

  setConnection(line) {
    this._data.connections = JSON.parse(line);
  }

  setExpected(line) {
    this._data.expected = JSON.parse(line);
  }

  build() {
    this._out = this._data;
    this._reset();

    return this._out;
  }
}

async function readGraphFromFile(path) {
  const fileStream = fs.createReadStream(path);

  const rl = readline.createInterface({
    input: fileStream,
    crlfDelay: Infinity
  });
  // Note: we use the crlfDelay option to recognize all instances of CR LF
  // ('\r\n') in input.txt as a single line break.

  const dataBuilder = new TestDataBuilder();
  const out = [];

  let n = 0;
  for await (const line of rl) {
    n++;
    switch (n) {
      case 1: dataBuilder.setN(line); break;
      case 2: dataBuilder.setConnection(line); break;
      case 3:
        dataBuilder.setExpected(line);
        n = 0;
        out.push(dataBuilder.build());
    }
  }

  return out;
}

describe('1192 Critical Connections', () => {

  test('Basic test #1', () => {
    const expected = [[1,3]];
    const actual = criticalConnections(4, [[0,1],[1,2],[2,0],[1,3]]);
    expect(actual).toEqual(expected);
  });

  test('Basic test #2', () => {
    const expected = [[0,1]];
    const actual = criticalConnections(2, [[0,1]]);
    expect(actual).toEqual(expected);
  });

  test('N=10000. Performance test', async () => {
    const cases = await readGraphFromFile('../../data/my/10000-vertices-graph-for-critical-connections.txt');

    for (let {n, connections, expected} of cases) {
      const actual = criticalConnections(n, connections);
      expect(actual).toEqual(expected);
    }
  });
});
