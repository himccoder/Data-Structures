/*
 * Copyright 2023 Marc Liberatore.
 */

package sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class SortingExercises {

    /**
     * Swap the values at a[i] and a[j].
     */
    static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Perform an in-place insertion sort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j - 1] > a[j]) {
                swap(a, j, j - 1);
                j--;
            }
        }
    }

    /**
     * Perform an in-place insertion sort of the range start (inclusive) 
     * through end (exclusive) on array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a, int start, int end) {
        for (int i = start+1;i<end;i++){
            int j = i-1;
            while (j >=start && a[j ] > a[j+1]) {
                swap(a, j, j + 1);
                j--;
            }
        }
    }
    
    
        

    
        
   

    /**
     * Perform a destructive mergesort on the array a.
    
     * The array will be in ascending order (least-to-greatest) after sorting; the original
     * values will be overwritten.
     * Additional array space will be allocated by this method.
     * 
     * For efficiency, this method will *insertion sort* any array of length less than 10.
     */
    public static void mergeSort(double[] a) {
        
        if (a.length < 2) {
            return; // base case: array is already sorted
        }
        if (a.length < 10) {
            insertionSort(a);
            return; // early return for small arrays
        }
        int mid = a.length / 2;
        double[] left = Arrays.copyOfRange(a, 0, mid);
        double[] right = Arrays.copyOfRange(a, mid, a.length);
        mergeSort(left);
        mergeSort(right);
        merge(a, left, right);
        
    }

    /**
     * Merge the sorted arrays l and r into the array a (overwriting its previous contents).
     */
    static void merge(double[] a, double[] l, double[] r) {
        int i = 0, j = 0, k = 0;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < l.length) {
            a[k++] = l[i++];
        }
        while (j < r.length) {
            a[k++] = r[j++];
        }
    
    }



    /**
     * Perform an in-place quicksort on the array a.
     */
    static void quickSort(double[] a) {
        quickSort(a, 0, a.length);
    }

    /**
     * Perform an in-place quicksort on the range i (inclusive) to j 
     * (exclusive) of the array a.
     * 
     * For efficiency, this method will *insertion sort* any range of 
     * length less than 10.
     */
    static void quickSort(double[] a, int i, int j) {
        if (j - i < 2) {
            return;
        }
    
        if (j - i < 10) {    //special insertion sort for small range arrays
            insertionSort(a); //takes only one argument instead of start and end indexes
        }
    
        int pivotIndex = partition(a, i, j);
        quickSort(a, i, pivotIndex);
        quickSort(a, pivotIndex + 1, j - 1);   
    
    
    }

    /**
     * Perform an in-place partition on the  range i (inclusive) to j 
     * (exclusive) of the array a, returning the index of the pivot
     * after partitioning.
     * 
     * To cut down on worst case choices, this method will chose the 
     * pivot value at random from among the values in the range.
     * 
     * @return the index of the pivot after the partition
     */
    static int partition(double[] a, int i, int j) {
        int first = i;
        int pivotIndex = ThreadLocalRandom.current().nextInt(i, j);
        double pivot = a[pivotIndex];
        swap(a, i, pivotIndex);
        pivotIndex = i;

        j = j - 1;
        do {
            while ((i < j) && (pivot >= a[i])) {
                i++;
            }
            while (pivot < a[j]) {
                j--;
            }
            if (i < j) {
                swap(a, i, j);
            }
        } while (i < j);
        swap(a, first, j);
        return j;
        }
        
    
    }

    