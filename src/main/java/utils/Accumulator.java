package utils;

public class Accumulator {
    private int N;
    private double total;

    public void addValue(double value) {
        this.N++;
        this.total += value;
    }

    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean " + this.mean() + " of " + N + " values";
    }
}
