package org.example;
import java.util.Arrays;

/**
 * The {@code HeapSort} class realize heap sort algorithm.
 */
public class HeapSort {

    /**
     * Sort a sample array and print the original and sorted arrays.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        int[] array = {5, 4, 3, 2, 1};
        System.out.println("Исходный массив: " + Arrays.toString(array));
        heapsort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

    /**
     * Sorts array of integers using the heap sort algorithm.
     *
     * @param array array of integers to be sorted.
     */
    public static void heapsort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            sift_down(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            sift_down(array, i, 0);
        }
    }

    /**
     * Method ensures heap property by transforming subtree with root node {@code i}.
     *
     * @param array array of integers representing heap.
     * @param n size of the heap.
     * @param i index of the root node of subtree to be sifted down.
     */
    static void sift_down(int[] array, int n, int i) {
        int biggest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[biggest])
            biggest = left;

        if (right < n && array[right] > array[biggest])
            biggest = right;

        if (biggest != i) {
            swap(array, i, biggest);
            sift_down(array, n, biggest);
        }
    }
    /**
     * Swaps two elements in array.
     *
     * @param array array in which elements are to be swapped.
     * @param i index of the first element to swap.
     * @param j index of the second element to swap.
     */
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
