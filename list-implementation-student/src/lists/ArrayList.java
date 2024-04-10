/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class ArrayList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    E[] array;
    int size;

    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + array[i].hashCode();
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
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void add(E e) {
        try {
            array[size] = e;
            size += 1;
        } catch (Exception exception) {
            enlarge();
            array[size] = e;
            size += 1;
        }
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == array.length) {
            enlarge();
        }
        for (int j = this.size; j > index; j--) {
            array[j] = array[j - 1];

        }
        array[index] = e;
        size += 1;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E removed = array[index];
        for (int j = index; j < size - 1; j++) {
            array[j] = array[j + 1];

        }
        size -= 1;
        array[size] = null;
        return removed;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E prev_element = array[index];
        this.remove(index);
        this.add(index, e);
        return prev_element;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.get(i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void enlarge() {
        E[] newArray = (E[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}