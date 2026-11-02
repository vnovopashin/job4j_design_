package ru.job4j.algo.hash;

import java.util.HashMap;

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
