package org.github.immess.structure;

public interface Sequence {
    void push(int value);

    int get(int index);

    int remove(int index);

    void insert(int value, int index);

    int size();

    int[] toArray();
}
