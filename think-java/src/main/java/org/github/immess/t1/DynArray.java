package org.github.immess.t1;

public class DynArray {
    private static final int DEF_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 2;

    private int[] array;
    private int size;

    public DynArray() {
        this.array = new int[DEF_CAPACITY];
        this.size = 0;
    }

    public void push(int element) {
        if (size == array.length) {
            extendArray();
        }
        array[size++] = element;
    }

    public int get(int index) {
        checkIndex(index);
        return array[index];
    }

    public int remove(int index) {
        checkIndex(index);
        int temp = array[index];
        for (int i = index; i < size - 1; ++i) {
            array[i] = array[i+1];
        }
        --size;
        return temp;
    }

    public void insert(int element, int index) {
        checkIndex(index);
        if (size == array.length) {
            extendArray();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        int[] copiedArray = new int[size];
        System.arraycopy(array, 0, copiedArray, 0, size);
        return copiedArray;
    }

    private void extendArray() {
        int[] biggerArray = new int[(int)(array.length * GROWTH_FACTOR)];
        System.arraycopy(array, 0, biggerArray, 0, array.length);
        this.array = biggerArray;
    }

    private void checkIndex(int i) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " is out of bound for size " + size + "!");
        }
    }
}
