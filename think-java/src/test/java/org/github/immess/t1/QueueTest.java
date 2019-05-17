package org.github.immess.t1;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {
    @Test
    public void testPush() {
        Queue arr = new Queue();

        arr.push(12464);

        Assert.assertEquals(1, arr.size());
        Assert.assertEquals(12464, arr.get(0));
    }

    @Test
    public void testPushMany() {
        Queue arr = new Queue();
        int[] source = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        for (int item : source) {
            arr.push(item);
        }

        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Assert.assertArrayEquals(expected, arr.toArray());
        Assert.assertEquals(source.length, arr.size());
    }

    @Test
    public void testRemoveSingleElement() {
        Queue arr = new Queue();

        arr.push(78561);
        arr.remove(0);

        Assert.assertEquals(0, arr.size());
    }

    @Test
    public void testRemoveSomeElement() {
        Queue arr = new Queue();
        int[] source = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

        for (int i : source) {
            arr.push(i);
        }

        arr.remove(2);

        int[] expected = new int[]{0, 1, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(expected, arr.toArray());
        Assert.assertEquals(expected.length, arr.size());

        arr.remove(6);

        int[] expected1 = new int[]{0, 1, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected1, arr.toArray());
        Assert.assertEquals(expected1.length, arr.size());

        arr.remove(0);

        int[] expected2 = new int[]{1, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected2, arr.toArray());
        Assert.assertEquals(expected2.length, arr.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementOutOfIndex() {
        Queue arr = new Queue();

        for (int i = 0; i < 3; ++i) {
            arr.push(i);
        }

        arr.remove(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveFromEmptyArray() {
        Queue arr = new Queue();

        arr.remove(0);
    }

    @Test
    public void testGet() {
        Queue arr = new Queue();

        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }

        Assert.assertEquals(3, arr.get(3));
        Assert.assertEquals(0, arr.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOutOfBoundUp() {
        Queue arr = new Queue();

        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }

        arr.get(6);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOutOfBoundDown() {
        Queue arr = new Queue();

        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }

        arr.get(-5);
    }

    @Test
    public void testInsertSomeElement() {
        Queue arr = new Queue();
        int[] source = new int[]{0, 1, 2, 3, 4};

        for (int i : source) {
            arr.push(i);
        }
        arr.insert(0, 3);

        int[] expected = new int[]{0, 1, 2, 0, 3, 4};
        Assert.assertArrayEquals(expected, arr.toArray());
        Assert.assertEquals(expected.length, arr.size());

    }

    @Test
    public void testInsertElementWithExtend() {
        Queue arr = new Queue();
        int[] source = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i : source) {
            arr.push(i);
        }
        arr.insert(0, 7);

        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 0, 7, 8, 9};
        Assert.assertArrayEquals(expected, arr.toArray());
        Assert.assertEquals(expected.length, arr.size());
    }

}
