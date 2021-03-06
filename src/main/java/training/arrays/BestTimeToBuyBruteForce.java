package training.arrays;

public class BestTimeToBuyBruteForce implements BestTimeToBuy {
    public int[][] cache;
    public int shift = 0;

    @Override
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int N = prices.length;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < prices[i + 1]) {
                break;
            }

            N = i + 1;
        }

        for (int i = 1; i < N; i++) {
            if (prices[i-1] < prices[i]) {
                break;
            }

            shift = i;
        }

        if (N - shift < 2) {
            return 0;
        }

        cache = new int[N - shift][N - shift];
        for (int i = 0; i < N - shift; i++) {
            for (int j = 0; j < N - shift; j++) {
                cache[i][j] = -1;
            }
        }
        return maxSubProfit(prices, shift, N - 1);
    }

    public int maxSubProfit(int[] prices, int start, int end) {
        int maxProfit = 0;
        int profit;
        int subProfit;

        if (cache[start - shift][end - shift] != -1) {
            return cache[start - shift][end - shift];
        }

        if (start == end) {
            return maxProfit;
        }

        for (int buy = end - 1; buy >= start; buy--) {
            for (int sell = end; sell > buy; sell--) {
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
        cache[start - shift][end - shift] = maxProfit;
        return maxProfit;
    }
}
