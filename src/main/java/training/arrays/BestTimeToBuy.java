package training.arrays;

/**
 * Best Time to Buy and Sell Stock II
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 */
public class BestTimeToBuy {
    public int maxProfit(int[] prices) {
        return maxSubProfit(prices, 0, prices.length - 1);
    }

    public int maxSubProfit(int[] prices, int start, int end) {
        int maxProfit = 0;
        int profit;
        int subProfit;

        if (start == end) {
            return maxProfit;
        }

        for (int buy = start; buy < end; buy++) {
            for (int sell = buy +1; sell <= end; sell++) {
                profit = prices[sell] - prices[buy];

                if (profit <= 0) {
                    continue;
                }

                if (sell + 1 < end) {
                    subProfit = maxSubProfit(prices, sell + 1, end);
                } else {
                    subProfit = 0;
                }

                if ((profit + subProfit) > maxProfit) {
                    maxProfit = profit + subProfit;
                }
            }
        }

        return maxProfit;
    }
}
