package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedValue = container[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        container[newSize] = null;
        size = newSize;
        modCount++;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        int newCapacity = container.length == 0 ? 10 : container.length * 2;
        Object[] temp = new Object[newCapacity];
        System.arraycopy(container, 0, temp, 0, size);
        container = (T[]) temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                checkModification();
                return index < size;
            }

            @Override
            public T next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }

            private void checkModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
