package sort;

import java.util.Comparator;
import java.io.File;

/**
 * this class implements a merge sort algorithm. also in a package for convinience for
 * future expansion for different sorts as needed.
 */
public class MSorter {
    /**
     * sorts an array of files according to a given compare parameter
     *
     * @param array array of files
     * @param c     the compare parameter to sort based on.
     */
    public static void arraysSort(File[] array, Comparator<File> c) {
        mergeSort(array, 0, array.length - 1, c);
    }

    /**
     * merge sort based on the algorithm implemented back than in C WORKSHOP first exercise.
     *
     * @param array - the array to sort.
     * @param begin - the starting index.
     * @param end   - the end index.
     * @param c     - the compare parameter.
     */
    private static void mergeSort(File[] array, int begin, int end, Comparator<File> c) {
        if (begin < end) {
            int middle = ((begin + end) / 2);
            mergeSort(array, begin, middle, c);
            mergeSort(array, middle + 1, end, c);
            merge(array, begin, middle, end, c);
        }
    }

    /**
     * merges two parts of the sorted array into single sorted array - inplace action.
     *
     * @param array  - the array of files
     * @param begin  - begin index
     * @param middle - the start of the second sorted array - end of first one
     * @param end    - the end of the second array
     * @param c      - the compare parameter
     */
    private static void merge(File[] array, int begin, int middle, int end, Comparator<File> c) {
        int sizeLeft = middle - begin + 1;
        int sizeRight = end - middle;
        File[] left = new File[sizeLeft];
        File[] right = new File[sizeRight];
        int leftRunningIndex = 0;
        int rightRunningIndex = 0;
        int beginOfMergedArrayIndex = begin;
        if (sizeLeft >= 0) {
            System.arraycopy(array, begin, left, 0, sizeLeft);
        }
        if (sizeRight >= 0) {
            System.arraycopy(array, middle + 1, right, 0, sizeRight);
        }
        while ((leftRunningIndex < sizeLeft) && (rightRunningIndex < sizeRight)) {
            if (c.compare(left[leftRunningIndex], right[rightRunningIndex]) > 0) {
                array[beginOfMergedArrayIndex] = right[rightRunningIndex];
                rightRunningIndex++;
            } else {
                array[beginOfMergedArrayIndex] = left[leftRunningIndex];
                leftRunningIndex++;
            }
            beginOfMergedArrayIndex++;
        }
        if (rightRunningIndex < sizeRight) {
            while (rightRunningIndex < sizeRight) {
                array[beginOfMergedArrayIndex] = right[rightRunningIndex];
                rightRunningIndex++;
                beginOfMergedArrayIndex++;
            }
        } else {
            while (leftRunningIndex < sizeLeft) {
                array[beginOfMergedArrayIndex] = left[leftRunningIndex];
                leftRunningIndex++;
                beginOfMergedArrayIndex++;
            }
        }
    }

}
