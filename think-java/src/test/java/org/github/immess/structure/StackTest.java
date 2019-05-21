package org.github.immess.structure;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {
    @Test
    public void ultraTest() {
        Stack stack = new Stack();
        int[] source = new int[]{0, 1, 2, 3, 4, 5};

        for (int i : source) {
            stack.push(i);
        }

        int[] expected = new int[]{5, 4, 3, 2, 1, 0};
        Assert.assertArrayEquals(expected, stack.toArray());

        Assert.assertEquals(5, stack.peek());

        stack.pop();
        Assert.assertEquals(4, stack.peek());

        stack.pop();
        stack.pop();
        int[] expected1 = new int[]{2, 1, 0};
        Assert.assertArrayEquals(expected1, stack.toArray());
    }

}
