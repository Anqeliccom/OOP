package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    @Test
    void testHeapsortLargeArray() {
        Random random = new Random();
        int size = 1000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(100);
        }
        int[] expectedArray = largeArray.clone();
        Arrays.sort(expectedArray);
        HeapSort.heapsort(largeArray);
        Assertions.assertArrayEquals(expectedArray, largeArray);
    }

    @Test
    void testReverseArray() {
        int[] array = {5, 4, 3, 2, 1};
        HeapSort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        HeapSort.heapsort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        HeapSort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] array = {5, 5, 4, 2, 4};
        HeapSort.heapsort(array);
        assertArrayEquals(new int[]{2, 4, 4, 5, 5}, array);
    }

    @Test
    void testSwap() {
        int[] array = {1, 2, 3, 4, 5};
        HeapSort.swap(array, 0, 4);
        Assertions.assertArrayEquals(new int[]{5, 2, 3, 4, 1}, array);
    }

    @Test
    void testSiftDown() {
        int[] array = {1, 10, 5, 6, 2};
        HeapSort.sift_down(array, array.length, 0);
        int[] expectedArray = {10, 6, 5, 1, 2};
        Assertions.assertArrayEquals(expectedArray, array);
    }
}