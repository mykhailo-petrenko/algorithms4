package kata.cutthecacke;

import kata.cutthecake.Solution;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {

    @Test
    public void basicTests() {
        validate("asdasdasdasd", 4);
        validate("abccbaabccba", 2);
        validate("aaa", 3);
    }

    void validate(String s, int expected) {
        Assert.assertEquals(expected, Solution.solution(s));
    }
}
