package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

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
}
