package org.github.immess.t1;

public interface Sequence {
    void push(int value);

    int get(int index);

    int remove(int index);

    void insert(int value, int index);

    int size();

    int[] toArray();
}
