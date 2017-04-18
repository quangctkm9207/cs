package algorithm.sort;

import java.util.Arrays;

/**
 * Merge Sort
 * Time complexity: O(nlogn).
 * Space complexity: O(n) in array.
 *
 * Steps:
 * 1, if the array length < 2, return.
 * 2, divide into smaller arrays.
 * 3, sort them.
 * 4, combine them back to make the sorted array.
 *
 * @author Quang Nguyen.
 */
public class MergeSort {

  public void sort(int[] numbers) {
    int N = numbers.length;
    if (N < 2) {
      return;
    }

    // divide
    int mid = N / 2;
    int[] n1 = Arrays.copyOfRange(numbers, 0, mid);
    int[] n2 = Arrays.copyOfRange(numbers, mid, N);

    // conquer
    sort(n1);
    sort(n2);

    // combine
    combine(n1, n2, numbers);
  }

  public void combine(int[] n1, int[] n2, int[] n) {
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < n1.length && j < n2.length) {
      if (n1[i] < n2[j]) {
        n[k] = n1[i];
        i++;
      } else {
        n[k] = n2[j];
        j++;
      }
      k++;
    }

    if (i < n1.length) {
      // add remaining elements from n1 to n
      while (i < n1.length) {
        n[k] = n1[i];
        i++;
        k++;
      }
    } else {
      while (j < n2.length) {
        n[k] = n2[j];
        j++;
        k++;
      }
    }
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5, 34, 19, 10, 7, 4};
    MergeSort mergeSort = new MergeSort();
    mergeSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
