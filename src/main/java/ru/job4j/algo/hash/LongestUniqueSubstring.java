package ru.job4j.algo.hash;

import java.util.HashMap;

/**
 * Решение задачи "Самая длинная подстрока из уникальных символов"
 * Используем скользящее окно (sliding window) с HashMap, где храним последнюю позицию каждого символа.
 * Левую границу окна сдвигаем при обнаружении повтора.
 * <p>
 * left — начало текущего окна, right — конец (движется циклом).
 * lastIndex хранит позицию последнего вхождения символа.
 * Если текущий символ c уже встречался и его позиция prev >= left (то есть внутри окна) — сдвигаем left = prev + 1.
 * Иначе окно расширяем.
 * Обновляем lastIndex.put(c, right).
 * Если текущая длина right - left + 1 больше рекорда — запоминаем bestStart и bestLen.
 * В конце возвращаем str.substring(bestStart, bestStart + bestLen).
 */
public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        HashMap<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int bestStart = 0;
        int bestLen = 0;

        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            Integer prev = lastIndex.get(c);

            /* если символ уже встречался внутри текущего окна — сдвигаем левую границу*/
            if (prev != null && prev >= left) {
                left = prev + 1;
            }

            lastIndex.put(c, right);

            int len = right - left + 1;
            if (len > bestLen) {
                bestLen = len;
                bestStart = left;
            }
        }

        return str.substring(bestStart, bestStart + bestLen);
    }
}
