import { readLinesInterface, readLinesIterator } from '../../../tools/readlines.js';

import criticalConnections from './1192_Critical_Connections.js';
import { UF } from './1192_Critical_Connections.js';
import { puml } from '../../../tools/puml.js';

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
  const readLine = readLinesInterface(path);
  // Note: we use the crlfDelay option to recognize all instances of CR LF
  // ('\r\n') in input.txt as a single line break.

  const dataBuilder = new TestDataBuilder();
  const out = [];

  let n = 0;
  for await (const line of readLine) {
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
    // puml(4, [[0,1],[1,2],[2,0],[1,3]], './basic1.puml');
    expect(actual).toEqual(expected);
  });

  test('Basic test #2', () => {
    const expected = [[0,1]];
    const actual = criticalConnections(2, [[0,1]]);
    expect(actual).toEqual(expected);
  });

  it('should be [[1,3]]', function () {
    const actual = criticalConnections(6, [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]]);
    expect(actual).toEqual([[1,3]]);
  });

  it('6 should be []', function () {
    const actual = criticalConnections(6, [[0,1],[1,2],[2,3],[3,4],[4,5],[5,0]]);
    expect(actual).toEqual([]);
  });

  it('9 should be [8,5]', function () {
    const N = 9;
    const actual = criticalConnections(N, [[0,1],[1,2],[2,3],[3,0],[3,4],[3,6],[4,5], [7,5], [6,7], [8,5]]);

    expect(actual).toEqual([[8, 5]]);
  });

  it('temp 1423', async () => {
    const iterator = readLinesIterator('../../data/mediumG.txt');

    const line = async () => await iterator.next();

    const N = parseInt((await line()).value, 10);
    const Nlines = parseInt((await line()).value, 10);

    const edges = [];

    while (true) {
      const {value, done} = await line();

      if (done) {
        break;
      }

      const edge = value.split(' ').map((n) => parseInt(n, 10));
      edges.push(edge);
    }

    // puml(N, edges, './mediumG.puml');
    criticalConnections(N, edges);
  });

  xit('N=10000. Performance test', async () => {
    const PATH_TO_NETWORK_CASES = '../../data/my/10000-vertices-graph-for-critical-connections.txt';
    const cases = await readGraphFromFile(PATH_TO_NETWORK_CASES);

    for (let {n, connections, expected} of cases) {
      // puml(n, connections, './n1000.puml');
      const actual = criticalConnections(n, connections);
      expect(actual).toEqual(expected);
    }


  });
});

describe('1192 Critical Connections. UF', () => {
  it('smoke test', () => {
    const {union, find} = new UF(5);

    expect(find(0)).toEqual(0);
    expect(find(1)).toEqual(1);
    expect(find(2)).toEqual(2);
    expect(find(3)).toEqual(3);
    expect(find(4)).toEqual(4);

    union(1, 4);

    expect(find(1)).toEqual(find(4));
  });

  it('worst case', () => {
    const {union, find, debug} = new UF(5);

    union(0,1);
    union(1,2);
    union(2,3);
    union(3,4);

    // debug();

    expect(find(1)).toEqual(0);
    expect(find(2)).toEqual(0);
    expect(find(3)).toEqual(0);
    expect(find(4)).toEqual(0);
  });

  it('check the rank', () => {
    const {union, find, debug} = new UF(6);

    union(0,1);
    union(1,2);
    union(3,4);
    union(4,5);

    union(0,3);

    // debug();


    expect(find(5)).toEqual(0);
    expect(find(3)).toEqual(0);
    expect(find(0)).toEqual(0);
  });

});
