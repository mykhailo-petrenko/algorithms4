import openLock from './752_open_the_lock.js';

describe('752_open_the_lock', () => {

  it('smoke test #1', () => {
    const actual = openLock(
      ["0201","0101","0102","1212","2002"],
      '0202'
    );

    expect(actual).toEqual(6);
  });

  it('smoke test #2', () => {
    const actual = openLock(
      ["8888"],
      '0009'
    );

    expect(actual).toEqual(1);
  });

  it('smoke test #3', () => {
    const actual = openLock(
      ["8887","8889","8878","8898","8788","8988","7888","9888"],
      '8888'
    );

    expect(actual).toEqual(-1);
  });
});
