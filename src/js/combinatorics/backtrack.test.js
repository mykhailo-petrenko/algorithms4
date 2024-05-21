import backtrack from './backtrack.js';

describe('Generate combinations using backtrack', () => {
  it('2^1-1 => 1 combinations', () => {
    const out = backtrack([1]);

    console.log(out);
    expect(out.length).toEqual(1);
  });

  it('2^2-1 => 3 combinations', () => {
    const out = backtrack([1, 2]);

    console.log(out);
    expect(out.length).toEqual(3);
  });

  it('2^3-1 => 7 combinations', () => {
    const out = backtrack([1, 2, 3]);

    console.log(out);
    expect(out.length).toEqual(7);
  });

  it('2^4-1 => 15 combinations', () => {
    const out = backtrack([1, 2, 3, 4]);

    console.log(out);
    expect(out.length).toEqual(15);
  });
});
