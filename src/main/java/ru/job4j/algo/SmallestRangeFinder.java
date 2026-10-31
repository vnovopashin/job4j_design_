package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return null;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int bestLeft = -1, bestRight = -1;
        int bestLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            freq.merge(nums[right], 1, Integer::sum);

            while (freq.size() == k) {
                int curLen = right - left + 1;
                if (curLen < bestLen) {
                    bestLen = curLen;
                    bestLeft = left;
                    bestRight = right;
                }

                int leftVal = nums[left];
                int cnt = freq.get(leftVal);
                if (cnt == 1) {
                    freq.remove(leftVal);
                } else {
                    freq.put(leftVal, cnt - 1);
                }
                left++;
            }
        }

        if (bestLeft == -1) {
            return null;
        }
        return new int[]{bestLeft, bestRight};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
