package org.github.immess.t1;

public class DynArray implements Sequence{
    private static final int DEF_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 2;

    private int[] array;
    private int size;

    public DynArray() {
        this.array = new int[DEF_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(int element) {
        if (size == array.length) {
            extendArray();
        }
        array[size++] = element;
    }

    @Override
    public int get(int index) {
        SequenceUtils.checkIndex(index, this);

        return array[index];
    }

    @Override
    public int remove(int index) {
        SequenceUtils.checkIndex(index, this);

        int temp = array[index];
        for (int i = index; i < size - 1; ++i) {
            array[i] = array[i+1];
        }
        --size;
        return temp;
    }

    @Override
    public void insert(int element, int index) {
        SequenceUtils.checkIndex(index, this);

        if (size == array.length) {
            extendArray();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

}
