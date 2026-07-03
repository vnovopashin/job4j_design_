package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int inCount = 0;
    private int outCount = 0;

    public T poll() {
        if (outCount == 0) {
            while (inCount > 0) {
                output.push(input.pop());
                inCount--;
                outCount++;
            }
        }
        if (outCount == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        outCount--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inCount++;
    }
}
