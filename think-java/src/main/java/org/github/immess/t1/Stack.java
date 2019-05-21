package org.github.immess.t1;

public class Stack implements Sequence, Popable {
    private Element head;
    private int size;

    public Stack() {
        head = null;
        size = 0;
    }

    @Override
    public void push(int value) {
        head = new Element(head, value);

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

        Element result;
        if (index == 0) {
            result = head;
            head = head.next;
        } else {
            Element prev = find(index - 1);
            result = prev.next;
            prev.next = result.next;
        }

        --size;
        return result.value;
    }

    @Override
    public void insert(int value, int index) {
        SequenceUtils.checkIndex(index, this);

        if (index == 0) {
            push(value);
        } else {
            Element prev = find(index - 1);
            prev.next = new Element(prev.next, value);
            ++size;
        }

    }

    @Override
    public int pop() {
        return remove(0);
    }

    @Override
    public int peek() {
        return get(0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[size];
        Element element = head;

        for (int i = 0; i < size; element = element.next) {
            result[i++] = element.value;
        }

        return result;
    }

    private Element find(int index) {
        Element result = head;
        for (int i = 0; i < index; ++i) {
            result = result.next;
        }
        return result;
    }

    private class Element {

        public Element(Element next, int value) {
            this.next = next;
            this.value = value;
        }

        private Element next;
        private int value;

    }
}
