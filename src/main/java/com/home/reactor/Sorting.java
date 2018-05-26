package com.home.reactor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sorting {
    public static int[] mergeSort(int[] a) {

        if (a.length <= 1) return a;

        int mid = a.length >> 1;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int i = 0;
        int j = 0;

        int[] result = new int[left.length + right.length];
        for (int k = 0; k < result.length; ++k) {
            if (i == left.length) {
                result[k] = right[j++];
            } else if (j == right.length) {
                result[k] = left[i++];
            } else if (left[i] < right[j]) {
                result[k] = left[i++];
            } else {
                result[k] = right[j++];
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] a = ThreadLocalRandom.current().ints(15, 0, 100).toArray();
        System.out.println(Arrays.toString(mergeSort(a)));
        System.out.println(Arrays.toString(quickSort(a)));

    }

    private static int[] quickSort(int[] a) {
        if (a.length <= 1) return a;

        int pivot = a[a.length - 1];

        Map<Boolean, List<Integer>> listMap =
                Arrays.stream(Arrays.copyOfRange(a, 0, a.length - 1))
                        .boxed()
                        .collect(Collectors.partitioningBy((el -> el < pivot)));

        List<Integer> smalls = listMap.get(Boolean.TRUE);
        int[] left = new int[smalls.size()];
        IntStream.range(0, smalls.size()).forEach(i -> left[i] = smalls.get(i));

        List<Integer> bigger = listMap.get(Boolean.FALSE);
        int[] right = new int[bigger.size()];
        IntStream.range(0, bigger.size()).forEach(i -> right[i] = bigger.get(i));

        return combine(quickSort(left), pivot, quickSort(right));
    }

    private static int[] combine(int[] left, int pivot, int[] right) {
        int[] result = new int[left.length + right.length + 1];
        for (int i = 0; i < left.length; ++i) {
            result[i] = left[i];
        }

        result[left.length] = pivot;

        for (int i = 0; i < right.length; ++i) {
            result[left.length + 1 + i] = right[i];
        }

        return result;
    }
}
