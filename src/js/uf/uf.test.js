import { UF } from './uf.js';

describe('UF', () => {

  it('smoke test', () => {
    const uf = new UF(10);

    uf.add(4, 3);
    uf.add(3, 8);
    uf.add(6, 5);
    uf.add(9, 4);
    uf.add(2, 1);

    expect(uf.connected(8, 9)).toBeTruthy();

    uf.add(5, 0);
    uf.add(7, 2);
    uf.add(6, 1);

    expect(uf.connected(1, 0)).toBeTruthy();
    expect(uf.connected(6, 7)).toBeTruthy();
    expect(uf.connected(1, 7)).toBeTruthy();
    expect(uf.connected(8, 4)).toBeTruthy();

    expect(uf.connected(0, 9)).toBeFalsy();
    expect(uf.connected(7, 3)).toBeFalsy();
    expect(uf.connected(7, 8)).toBeFalsy();
    expect(uf.connected(2, 3)).toBeFalsy();
  });

  it('groups count', () => {
    const uf = new UF(4);

    expect(uf.groupsCount()).toBe(4);

    uf.add(0,1);
    uf.add(2,3);

    expect(uf.groupsCount()).toBe(2);

    uf.add(1,3);

    expect(uf.groupsCount()).toBe(1);

    uf.add(0,1);
    uf.add(2,3);

    expect(uf.groupsCount()).toBe(1);
  });
});
