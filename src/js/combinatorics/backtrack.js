
var backtrack = function backtrackGenerate(initialSet) {
  const out = [];

  const dfs = (set, start) => {
    if (set.length > 0) {
      out.push([...set]);
    }

    for (let i=start; i<initialSet.length; i++) {
      set.push(initialSet[i]);
      dfs(set, i+1);
      set.pop();
    }
  }

  dfs([], 0);

  return out;
}

export default backtrack;
