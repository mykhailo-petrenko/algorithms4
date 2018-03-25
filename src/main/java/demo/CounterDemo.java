package demo;

import edu.princeton.cs.algorithms.Counter;
import edu.princeton.cs.introcs.StdRandom;

public class CounterDemo {
    public static void main(String[] args) {
        Counter heads = new Counter("Heads");
        Counter tails = new Counter("Tails");

        int N = 100;
        boolean isHead;

        for(int i=0; i<N; i++) {
            isHead = StdRandom.bernoulli(0.5);

            if(isHead) {
                heads.increment();
            } else {
                tails.increment();
            }
        }

        System.out.println(heads);
        System.out.println(tails);
    }
}
