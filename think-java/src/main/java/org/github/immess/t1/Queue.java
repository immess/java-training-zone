package org.github.immess.t1;

public class Queue implements Sequence {
    private QElement head;
    private QElement tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public void push(int value) {
        tail = new QElement(null, tail, value);
        if (head == null) {
            head = tail;
        } else {
            tail.prev.next = tail;
        }

        ++size;
    }

    @Override
    public int get(int index) {
        SequenceUtils.checkIndex(index, this);

        return find(index).value;
    }

    @Override
    public int remove(int index) {
        SequenceUtils.checkIndex(index, this);

        QElement result = find(index);
        if (result == tail && result == head) {
            head = null;
            tail = null;
        } else if (result == tail) {
            tail = result.prev;
            tail.next = null;
        } else if (result == head) {
            head = result.next;
            head.prev = null;
        } else {
            result.prev.next = result.next;
            result.next.prev = result.prev;
        }

        --size;

        return result.value;
    }

    @Override
    public void insert(int value, int index) {
        SequenceUtils.checkIndex(index, this);

        QElement movedElement = find(index);
        QElement insertedElement = new QElement(movedElement, movedElement.prev, value);

        if (movedElement == head) {
            head = insertedElement;
        } else {
            movedElement.prev.next = insertedElement;
        }
        movedElement.prev = insertedElement;

        ++size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[size];
        QElement element = head;

        for (int i = 0; i < size; element = element.next) {
            result[i++] = element.value;
        }

        return result;
    }

    private QElement find(int index) {
        QElement result = head;
        for (int i = 0; i < index; ++i) {
            result = result.next;
        }
        return result;
    }

    private static class QElement {
        QElement next;
        QElement prev;
        int value;

        QElement(QElement next, QElement prev, int value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }

}
