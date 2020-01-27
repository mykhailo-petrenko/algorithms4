package kata.fuelinjection;

import java.math.BigInteger;

/**
 * Fuel Injection Perfection
 * =========================
 *
 * Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for her LAMBCHOP doomsday device.
 * It's a great chance for you to get a closer look at the LAMBCHOP - and maybe sneak in a bit of sabotage while you're at it - so you took the job gladly.
 *
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time.
 * However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.
 *
 * The fuel control mechanisms have three operations:
 *
 * 1) Add one fuel pellet
 * 2) Remove one fuel pellet
 * 3) Divide the entire group of fuel pellets by 2
 * (due to the destructive energy released when a quantum antimatter pellet is cut in half,
 *  the safety controls will only allow this to happen if there is an even number of pellets)
 *
 * Write a function called solution(n) which takes a positive integer as a string
 * and returns the minimum number of operations needed to transform the number of pellets to 1.
 * The fuel intake control panel can only display a number up to 309 digits long,
 * so there won't ever be more pellets than you can express in that many digits.
 *
 * For example:
 * solution(4) returns 2: 4 -> 2 -> 1
 * solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 *
 *
 * Test cases
 * ==========
 * Your code should pass the following test cases.
 * Note that it may also be run against hidden test cases not shown here.
 *
 * -- Java cases --
 * Input:
 * Solution.solution('4')
 * Output:
 *     2
 *
 * Input:
 * Solution.solution('15')
 * Output:
 *     5
 */
public class Solution {

    public static BigInteger ZERO = new BigInteger("0");
    public static BigInteger ONE = new BigInteger("1");
    public static BigInteger FOR = new BigInteger("4");


    public static int[] buff = new int[]{0, 0, 1, 2, 2};

    public static int solution(String x) {
        int steps = 0;

        BigInteger pellets = new BigInteger(x);

        while (pellets.compareTo(FOR) > 0) {

            if (pellets.and(ONE).equals(ZERO)) {
                pellets = pellets.shiftRight(1);
            } else {
                BigInteger sub = pellets.subtract(ONE);
                BigInteger add = pellets.add(ONE);

                int subCount = 0;
                int addCount = 0;

                while (sub.and(ONE).equals(ZERO)) {
                    sub = sub.shiftRight(1);
                    subCount++;
                }

                while (add.and(ONE).equals(ZERO)) {
                    add = add.shiftRight(1);
                    addCount++;
                }

                if (addCount > subCount) {
                    pellets = pellets.add(ONE);
                } else {
                    pellets = pellets.subtract(ONE);
                }
            }

            steps++;
        }

        return steps + buff[Integer.parseInt(pellets.toString())];
    }
//
//    public static int solutionLong(String x) {
//        int steps = 0;
//        long pellets = Long.parseLong(x, 10);
//
//        while (pellets > 4) {
//
//            if ((pellets & 1) == 0) {
//                pellets = pellets >> 1;
//            } else {
//                long sub = pellets - 1;
//                int subCount = 0;
//                long add = pellets + 1;
//                int addCount = 0;
//
//                sub = ~sub & (sub - 1);
//                add = ~add & (add - 1);
//
//                while (sub != 0) {
//                    sub = sub >> 1;
//                    subCount++;
//                }
//
//                while (add != 0) {
//                    add = add >> 1;
//                    addCount++;
//                }
//
//                if (addCount > subCount) {
//                    pellets = pellets + 1;
//                } else {
//                    pellets = pellets - 1;
//
//                }
//            }
//
//            steps++;
//        }
//
//        return steps + buff[(int)pellets];
//    }
//
//    public static int solutionFirst(String x) {
//        int pellets = Integer.parseInt(x, 10);
//
//        return go(pellets);
//    }
//
//    public static int go(int pellets) {
//        int operations = 0;
//
//        while (pellets != 1) {
//
//            if (pellets % 2 != 0) {
//                return Math.min(go(pellets - 1), go(pellets + 1)) + 1 + operations;
//            }
//
//            pellets = pellets / 2;
//            operations++;
//        }
//
//        return operations;
//    }
//
//
////    public static int[] buff = new int[]{0, 0, 1, 2, 2};
//
//    // 21^024 = 179,769,313,486,231,590,772,930,...,304,835,356,329,624,224,137,216 (309 digits)
//    public static int solutionTryNoFate(String x) {
//        int operations = 0;
//        int pellets = Integer.parseInt(x, 10);
//
//
//        while (pellets > 4) {
//
//            if ((pellets & 1) == 1) {
//
//                if ((pellets & (pellets + 1)) == 0) {
//                    pellets = pellets + 1;
//                } else {
//                    pellets = pellets - 1;
//                }
//            } else {
//                pellets = pellets >> 1;
//            }
//
//            operations++;
//        }
//
//        return operations + buff[pellets];
//    }
}
