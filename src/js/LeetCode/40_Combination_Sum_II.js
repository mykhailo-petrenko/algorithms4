/**
 * # LeetCode. 40. Combination Sum II (https://leetcode.com/problems/combination-sum-ii/description/)
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Constraints:
 *
 * - 1 <= candidates.length <= 100
 * - 1 <= candidates[i] <= 50
 * - 1 <= target <= 30
 */

/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {
  const out = [];
  candidates.sort((a, b) => a - b);

  const combination = [];
  const suffix = [...candidates];

  for (let i= candidates.length-2; i>=0; i--) {
    suffix[i] = suffix[i] + suffix[i+1];
  }

  const backtrack = (index, sum) => {
    if (sum === 0) {
      out.push([...combination]);
      return;
    }

    if (
      index >= candidates.length // out of range
      ||
      sum < candidates[index] // to big number left
      ||
      sum > suffix[index] // not enough numbers to reach sum
    ) {
      return;
    }

    for (let i = index; i < candidates.length; i++) {
      // prevent duplicates
      if (i > index && candidates[i] === candidates[i-1]) {
        continue;
      }

      combination.push(candidates[i]);

      backtrack(i + 1, sum - candidates[i]);

      combination.pop();
    }
  };


  backtrack(0, target);

  return out;
};

/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2Bruteforce2 = function(candidates, target) {
  const dedupe = new Set();
  const out = [];
  const hash = (list) => list.join('-');

  candidates.sort((a, b) => a-b);

  if (candidates.reduce((sum, n) => sum + n, 0) < target) {
    return out;
  }

  let prefix = [0];
  for (let i=0; i<candidates.length; i++) {
    prefix[i+1] = prefix[i] + candidates[i];
  }

  const combinations = (prev, index, sum, isLeft=true) => {
    if ((prefix[index] + sum) < target) {
      return;
    }

    if (!isLeft && sum === target) {

      if (!dedupe.has(hash(prev))) {
        out.push(prev);
        dedupe.add(hash(prev));
      }

      return;
    }

    if (index < 0) {
      return;
    }

    index--;

    combinations(prev, index, sum);
    combinations([...prev, candidates[index]], index, sum+candidates[index], false);
  };

  combinations([], candidates.length, 0);

  return out;
};

/**
 * This function generates all combinations via bruteforce.
 *
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2Bruteforce1 = function(candidates, target) {
  const dedupe = new Set();
  const out = [];
  candidates.sort((a, b) => a-b);

  const hash = (list) => list.join('-');
  const sum = (list) => list.reduce((sum, n) => sum + n, 0);

  const combinations = (prev, index, isLeft=true) => {
    if (!isLeft && sum(prev) === target) {
      if (!dedupe.has(hash(prev))) {
        out.push(prev);
        dedupe.add(hash(prev));
      }
      return;
    }

    if (index >= candidates.length) {
      return;
    }

    combinations(prev, index+1);
    combinations([...prev, candidates[index]], index+1, false);
  };

  combinations([], -1);

  return out;
};

export default combinationSum2;
