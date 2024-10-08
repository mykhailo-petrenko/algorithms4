package training;

import java.util.LinkedList;
import java.util.Stack;

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
class StockSpanner {
    private Stack<Integer> counts;
    private Stack<Integer> prices;

    public StockSpanner() {
        counts = new Stack<>();
        prices = new Stack<>();
    }

    public int next(int price) {
        int count = 1;

        while (!prices.empty() && prices.peek() <= price) {
            count += counts.pop();
            prices.pop();
        }

        counts.add(count);
        prices.add(price);

        return count;
    }

    public static void main(String[] args) {
        StockSpanner spanner = new StockSpanner();

        int[] values = new int[] {100, 80, 60, 70, 60, 75, 85};

        for (int v : values) {
            System.out.printf("%d>> %d \n", v, spanner.next(v));
        }
    }
}
