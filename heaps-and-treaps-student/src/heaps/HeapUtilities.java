/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {
    /**
     * Returns true iff the subtree of a starting at index i is a max-heap.
     * @param a an array representing a mostly-complete tree, possibly a heap
     * @param i an index into that array representing a subtree rooted at i
     * @return true iff the subtree of a starting at index i is a max-heap
     */
    static boolean isHeap(double[] a, int i) {
        //For max heap
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // Check if left child exists and is greater than parent
        if (leftChild < a.length && a[leftChild] > a[i]) {
            return false;
        }

        // Check if right child exists and is greater than parent
        if (rightChild < a.length && a[rightChild] > a[i]) {
            return false;
        }

        // Recursively check if left and right subtrees are max-heaps
        if (leftChild < a.length && !isHeap(a, leftChild)) {
            return false;
        }

        if (rightChild < a.length && !isHeap(a, rightChild)) {
            return false;
        }

        return true;
    }

    /**
     * Perform the heap siftdown operation on index i of the array a. 
     * 
     * This method assumes the subtrees of i are already valid max-heaps.
     * 
     * This operation is bounded by n (exclusive)! In a regular heap, 
     * n = a.length, but in some cases (for example, heapsort), you will 
     * want to stop the sifting at a particular position in the array. 
     * siftDown should stop before n, in other words, it should not 
     * sift down into any index great than (n-1).
     * 
     * @param a the array being sifted
     * @param i the index of the element to sift down
     * @param n the bound on the array (that is, where to stop sifting)
     */
    static void siftDown(double[] a, int i, int n) {
        int maxChild;
        double parent = a[i];

        while (2 * i + 1 < n) {  // Check if i has a left child
            maxChild = 2 * i + 1;  // Assume left child is maximum

            // Check if right child exists and is greater than left child
            if (maxChild + 1 < n && a[maxChild + 1] > a[maxChild]) {
                maxChild = maxChild + 1;
            }

            // Check if the maximum child is greater than the parent
            if (a[maxChild] > parent) {
                a[i] = a[maxChild];
                i = maxChild;
            } else {
                break;
            }
        }

        a[i] = parent;
    }

    /**
     * Heapify the array a in-place in linear time as a max-heap.
     * @param a an array of values
     */
    static void heapify(double[] a) {
        int n = a.length;

        // Starting from the first non-leaf node, sift down to create a max-heap
        for (int i = n/2 - 1; i >= 0; i--) {
            siftDown(a, i, n);
        }
    }

    /**
     * Heapsort the array a in-place, resulting in the elements of
     * a being in ascending order.
     * @param a
     */
    static void heapSort(double[] a) {
        int n = a.length;

        // Heapify the array to create a max-heap
        heapify(a);

        // Repeatedly extract the maximum element and restore the heap property
        for (int i = n - 1; i > 0; i--) {
            // Swap the maximum element (at index 0) with the last element of the heap
            double temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            // Sift down the element at index 0 to restore the heap property
            siftDown(a, 0, i);
        }
    }
    
    public static void main(String[] args) {
        Random r = new Random(0);
        int length = 15;
        double[] l = new double[length];
        for (int i = 0; i < length; i++) {
            l[i] = r.nextInt(20);
        }
        System.out.println(Arrays.toString(l));

        heapify(l);

        System.out.println(Arrays.toString(l));

        heapSort(l);

        System.out.println(Arrays.toString(l));
    }
    

   

   
}
