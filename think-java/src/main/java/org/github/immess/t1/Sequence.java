package org.github.immess.t1;

public interface Sequence {
    void push(int value);

    int get(int index);

    int remove(int index);

    void insert(int element, int index);

    int size();

    public int[] toArray();
}
