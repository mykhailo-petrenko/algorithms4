package demo.tasks.c1_3;

import org.junit.Assert;
import org.junit.Test;

public class ParenthesesTest {

    @Test()
    public void basicSuccessTest() {
        Assert.assertTrue(
            Parentheses.isBalanced("({[<>]})")
        );
    }

    @Test()
    public void basicFailTest() {
        Assert.assertFalse(
                Parentheses.isBalanced("({[<]})>")
        );
    }

    @Test()
    public void sequencesFromBook() {
        Assert.assertTrue(
                Parentheses.isBalanced("[()]{}{[()()]()}")
        );

        Assert.assertFalse(
                Parentheses.isBalanced("[(])")
        );
    }

    @Test
    public void opensOverflow() {
        Assert.assertFalse(
                Parentheses.isBalanced("[[{()")
        );
        Assert.assertFalse(
                Parentheses.isBalanced("[[")
        );
    }

    @Test
    public void closesOverflow() {
        Assert.assertFalse(
                Parentheses.isBalanced("[])}]")
        );
        Assert.assertFalse(
                Parentheses.isBalanced("}])")
        );
    }
}
