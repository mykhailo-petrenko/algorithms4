/**
# LeetCode. 752. Open the Lock (https://leetcode.com/problems/open-the-lock/description/)


 */
const mutateLock = function(state) {
  const out = [];

  for (let i=0; i<state.length; i++) {
    out.push([...state]);
    out[out.length-1][i] = (state[i] + 1) % 10;

    out.push([...state]);
    out[out.length-1][i] = (state[i] + 9) % 10;
  }

  return out;
}

/**
 * @param {string[]} deadends
 * @param {string} target
 * @return {number}
 */
var openLock = function(deadends, target) {
  const visited = new Set(deadends);
  const deque = [['0000', 0]];

  if (visited.has(target)) {
    return -1;
  }

  while (deque.length > 0) {
    const [state, distance] = deque.shift();

    if (visited.has(state)) {
      continue;
    }

    if (state === target) {
      return distance;
    }

    visited.add(state);

    const arr = state.split('').map(c => parseInt(c, 10));

    for (let next of mutateLock(arr)) {
      const nextState = next.join('');
      if (visited.has(nextState)) {
        continue;
      }

      deque.push([nextState, distance+1]);
    }
  }

  return -1;
};


export default openLock;
