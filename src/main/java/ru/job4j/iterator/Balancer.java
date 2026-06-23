package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        /* тут должна быть реализация */
        if (nodes == null || nodes.isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = 0;
        while (source.hasNext()) {
            Integer elem = source.next();
            nodes.get(index).add(elem);
            index = (index + 1) % nodes.size();
        }
    }
}
