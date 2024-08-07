/**
# LeetCode. 857. Minimum Cost to Hire K Workers (https://leetcode.com/problems/minimum-cost-to-hire-k-workers/description/)
There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group must be paid at least their minimum wage expectation.
In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.



## Example 1:
  Input: quality = [10,20,5], wage = [70,50,30], k = 2
  Output: 105.00000
  Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

## Example 2:
  Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
  Output: 30.66667
  Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


## Constraints:
- n == quality.length == wage.length
- 1 <= k <= n <= 10^4
- 1 <= quality[i], wage[i] <= 10^4
 */

import { MaxPriorityQueue } from '@datastructures-js/priority-queue';

/**
 * @param {number[]} quality
 * @param {number[]} wage
 * @param {number} k
 * @return {number}
 */
var mincostToHireWorkers = function(quality, wage, k) {
  const medewerkers = [];

  for (let i = 0; i < quality.length; i++) {
    medewerkers[i] = {
      quality: quality[i],
      wage: wage[i],
      efficiency: wage[i] / quality[i],
    };
  }

  medewerkers.sort((a, b) => {
    return a.efficiency - b.efficiency;
  })


  const pQueue = new MaxPriorityQueue();
  let minCost = Infinity;
  let totalQuality = 0;

  for (const w of medewerkers) {
    totalQuality = totalQuality + w.quality;
    pQueue.enqueue(w.quality);

    if (pQueue.size() > k) {
      const expensiveOne = pQueue.dequeue().element;
      totalQuality = totalQuality - expensiveOne;
    }

    if (pQueue.size() === k) {
      minCost = Math.min(minCost, (w.wage/w.quality) * totalQuality);
    }
  }

  return Math.round(minCost * 100000) / 100000;
};

export default mincostToHireWorkers;
