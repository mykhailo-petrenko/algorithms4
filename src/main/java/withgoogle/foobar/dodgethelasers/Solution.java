package withgoogle.foobar.dodgethelasers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Dodge the Lasers!
 * ==================
 *
 * Oh no! You've managed to escape Commander Lambdas collapsing space station in an escape pod with the rescued bunny prisoners - but Commander Lambda isnt about to let you get away that easily. She's sent her elite fighter pilot squadron after you - and they've opened fire!
 *
 * Fortunately, you know something important about the ships trying to shoot you down. Back when you were still Commander Lambdas assistant, she asked you to help program the aiming mechanisms for the starfighters. They undergo rigorous testing procedures, but you were still able to slip in a subtle bug. The software works as a time step simulation: if it is tracking a target that is accelerating away at 45 degrees, the software will consider the targets acceleration to be equal to the square root of 2, adding the calculated result to the targets end velocity at each timestep. However, thanks to your bug, instead of storing the result with proper precision, it will be truncated to an integer before adding the new velocity to your current position.  This means that instead of having your correct position, the targeting software will erringly report your position as sum(i=1..n, floor(i*sqrt(2))) - not far enough off to fail Commander Lambdas testing, but enough that it might just save your life.
 *
 * If you can quickly calculate the target of the starfighters' laser beams to know how far off they'll be, you can trick them into shooting an asteroid, releasing dust, and concealing the rest of your escape.  Write a function solution(str_n) which, given the string representation of an integer n, returns the sum of (floor(1*sqrt(2)) + floor(2*sqrt(2)) + ... + floor(n*sqrt(2))) as a string. That is, for every number i in the range 1 to n, it adds up all of the integer portions of i*sqrt(2).
 *
 * For example, if str_n was "5", the solution would be calculated as
 * floor(1*sqrt(2)) +
 * floor(2*sqrt(2)) +
 * floor(3*sqrt(2)) +
 * floor(4*sqrt(2)) +
 * floor(5*sqrt(2))
 * = 1+2+4+5+7 = 19
 * so the function would return "19".
 *
 * str_n will be a positive integer between 1 and 10^100, inclusive. Since n can be very large (up to 101 digits!), using just sqrt(2) and a loop won't work. Sometimes, it's easier to take a step back and concentrate not on what you have in front of you, but on what you don't.
 *
 *
 * SOLUTION
 * ================
 * - try to find out the polynomial approximation (failed because not precise enough)
 * - google: 1, 2, 4, 5, 7, 8, 9 - and got the http://oeis.org/A001951 name of this sequence http://oeis.org/A001951
 * - https://mathworld.wolfram.com/BeattySequence.html
 * - https://en.wikipedia.org/wiki/Beatty_sequence
 * - google: Sum of Beatty Sequence and got solution: https://math.stackexchange.com/questions/2052179/how-to-find-sum-i-1n-left-lfloor-i-sqrt2-right-rfloor-a001951-a-beatty-s
 */
public class Solution {
    // 1
    static BigDecimal ONE = new BigDecimal("1");
    // 2
    static BigDecimal TWO = new BigDecimal("2");
    // 3
    static BigDecimal THREE = new BigDecimal("3");
    // 7
    static BigDecimal SEVEN = new BigDecimal("7");
    // sqrt(2) - 1
    static BigDecimal SQRT2_1 = new BigDecimal("0.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

    // n' = Math.floor( (sqrt(2) - 1) * n )
    static BigDecimal nPrime(BigDecimal n) {
        return SQRT2_1.multiply(n).setScale(0, RoundingMode.FLOOR);
    }

    static BigDecimal arithmeticProgressionSum(BigDecimal n) {
        return n.multiply(n.add(ONE)).divide(TWO);
    }

    // sum(n) = n*n' + n(n+1)/2 - n'(n'+1)/2 - sum(n')
    static BigDecimal sum(BigDecimal n) {
        if (n.equals(ONE)) {
            return ONE;
        }

        if (n.equals(TWO)) {
            return THREE;
        }

        if (n.equals(THREE)) {
            return SEVEN;
        }

        BigDecimal np = nPrime(n);
        BigDecimal sum_np = sum(np);
        return n.multiply(np).add(arithmeticProgressionSum(n)).subtract(arithmeticProgressionSum(np)).subtract(sum_np);
    }

    public static String solution(String s) {


        return sum(new BigDecimal(s)).toBigInteger().toString();
    }


}
