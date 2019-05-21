package org.github.immess.structure;

public class Stack {
    private Element head;
    private int size;

    public Stack() {
        head = null;
        size = 0;
    }

    public void push(int value) {
        head = new Element(head, value);

        ++size;
    }

    public int pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }

        Element result = head;
        head = head.next;

        --size;
        return result.value;
    }

    public int peek() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }

        return head.value;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        int[] result = new int[size];
        Element element = head;

        for (int i = 0; i < size; element = element.next) {
            result[i++] = element.value;
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
