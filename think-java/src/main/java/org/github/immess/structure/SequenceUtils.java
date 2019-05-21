package org.github.immess.structure;

public class SequenceUtils {
    public static void checkIndex(int i, int size) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " is out of bound for size " + size + "!");
        }
    }

    public static void checkIndex(int i, Sequence target) {
        checkIndex(i, target.size());
    }
}
