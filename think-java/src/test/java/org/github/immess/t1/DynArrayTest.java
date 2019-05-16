package org.github.immess.t1;

import org.junit.Assert;
import org.junit.Test;

public class DynArrayTest {
    @Test
    public void testPush() {
        DynArray arr = new DynArray();
        arr.push(12464);
        Assert.assertEquals(1, arr.size());
        Assert.assertEquals(12464, arr.get(0));
    }

    @Test
    public void testPushMany() {
        DynArray arr = new DynArray();
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
        DynArray arr = new DynArray();
        arr.push(78561);
        arr.remove(0);
        Assert.assertEquals(0, arr.size());
    }

    @Test
    public void testRemoveSomeElement() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 5; ++i) {
            arr.push(i);
        }
        arr.remove(2);
        int[] expected = new int[]{0, 1, 3, 4};
        Assert.assertArrayEquals(expected, arr.toArray());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementOutOfIndex() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 3; ++i) {
            arr.push(i);
        }
        arr.remove(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveFromEmptyArray() {
        DynArray arr = new DynArray();
        arr.remove(0);
    }

    @Test
    public void testGet() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }
        Assert.assertEquals(3, arr.get(3));
        Assert.assertEquals(0, arr.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOutOfBoundUp() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }
        arr.get(6);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOutOfBoundDown() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 4; ++i) {
            arr.push(i);
        }
        arr.get(-5);
    }

    @Test
    public void testInsertSomeElement() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 5; ++i) {
            arr.push(i);
        }
        arr.insert(0, 3);
        int[] expected = new int[]{0, 1, 2, 3, 4};
        //todo
    }

    @Test
    public void testInsertElementWithExtend() {
        DynArray arr = new DynArray();
        for (int i = 0; i < 10; ++i) {
            arr.push(i);
        }
        arr.insert(0, 7);
        //todo
    }

}
