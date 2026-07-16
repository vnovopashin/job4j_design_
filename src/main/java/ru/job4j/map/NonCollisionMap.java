package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            MapEntry<K, V> entry = table[index];
            if (Objects.hashCode(entry.key) == Objects.hashCode(key)
                    && Objects.equals(entry.key, key)) {
                entry.value = value;
                return true;
            }
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            MapEntry<K, V> entry = table[index];
            K key1 = entry.key;
            if (Objects.hashCode(key1) == Objects.hashCode(key)) {
                if (Objects.equals(key1, key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int index;

            @Override
            public boolean hasNext() {
                boolean res = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        res = true;
                        break;
                    }
                }
                return res;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
        map.put(0, "Hello");
        map.put(1, "world");
        System.out.println(map);
    }
}
