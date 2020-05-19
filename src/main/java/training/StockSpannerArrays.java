package training;

/**
 * Online Stock Span
 * ===========================
 *
 * Write a class StockSpanner which collects daily price quotes for some stock,
 * and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
 * for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were `[100, 80, 60, 70, 60, 75, 85]`,
 * then the stock spans would be `[1, 1, 1, 2, 1, 4, 6]`.
 */
class StockSpannerArrays {
    private int[] pt;
    private int[] prices;
    private int N = 0;

    public StockSpannerArrays() {
        resize(128);
    }

    public int next(int price) {
        int count = 1;
        if (pt.length == (N + 1)) {
            resize(pt.length * 2);
        }

        prices[N] = price;

        while ((N - count >= 0) && (prices[N] >= prices[N - count])) {
            count += pt[N - count];
        }

        pt[N] = count;

        N++;
        return count;
    }

    private void resize(int newSize) {
        int[] pt = new int[newSize];
        int[] prices = new int[newSize];

        for (int i = 0; i < N; i++) {
            pt[i] = this.pt[i];
            prices[i] = this.prices[i];
        }

        this.pt = pt;
        this.prices = prices;
    }

    public static void main(String[] args) {
        StockSpannerArrays spanner = new StockSpannerArrays();

        int[] values = new int[] {100, 80, 60, 70, 60, 75, 85};

        for (int v : values) {
            System.out.printf("%d>> %d \n", v, spanner.next(v));
        }
    }
}
