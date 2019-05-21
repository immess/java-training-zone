package org.github.immess.structure;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest extends AbstractSequenceTest {
    @Override
    protected Sequence createSequence() {
        return new Queue();
    }

    @Test
    public void ultraTest() {
        Queue queue = new Queue();
        int[] source = new int[]{0, 1, 2, 3, 4, 5};

        for (int i : source) {
            queue.push(i);
        }

        int[] expected = new int[]{0, 1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, queue.toArray());

        Assert.assertEquals(0, queue.peek());

        Assert.assertEquals(0, queue.pop());
        Assert.assertEquals(1, queue.peek());

        Assert.assertEquals(1, queue.pop());
        Assert.assertEquals(2, queue.pop());

        expected = new int[]{3, 4, 5};
        Assert.assertArrayEquals(expected, queue.toArray());
    }
}
