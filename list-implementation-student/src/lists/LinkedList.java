/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class LinkedList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Node<E> n = head;
        while (n != null) {
            result = prime * result + head.data.hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List other = (List) obj;
        if (size != other.size())
            return false;
        // TODO before returning true, make sure each element of the lists are equal!
        for (int i = 0; i < this.size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int j = 0;
        Node n = head;
        while (true) {
            if (index == j) {
                return (E) n.data;
            }
            n = n.next;
            j++;
        }
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);

        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;

    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> prevNode = (Node<E>) this.get(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E removedElement;
        if (index == 0) {
            removedElement = head.data;
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedElement = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removedElement;

    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E previousElement = current.data;
        current.data = e;
        return previousElement;

    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }
}
