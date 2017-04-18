package algorithm.sort;

import java.util.Arrays;

/**
 * Quick Sort
 * Time complexity:
 * - average case: O(nlogn).
 * - worst case: O(n^2).
 *
 * @author Quang Nguyen.
 */
public class QuickSort {

  public void sort(int[] numbers) {
    int N = numbers.length;
    if (N < 2) {
      return;
    }
    quickSort(numbers, 0, N - 1);
  }

  public void quickSort(int[] numbers, int low, int high) {
    if (low > high) return; // subarray is trivially sorted
    int left = low;
    int right = high - 1;
    int pivot = numbers[high];
    int temp;
    while (left < right) {
      // scan until reaching value equal or larger than pivot (or right marker)
      while (left <= right && numbers[left] < pivot) left++;
      // scan until reaching value equal or smaller than pivot (or left marker)
      while (left <= right && numbers[right] > pivot) right--;
      if (left <= right) {
        // so swap values and shrink range
        temp = numbers[left];
        numbers[left] = numbers[right];
        numbers[right] = temp;
        left++;
        right--;
      }
    }
    // put pivot into its final place (currently marked by left index)
    temp = numbers[left];
    numbers[left] = numbers[high];
    numbers[high] = temp;

    // make recursive calls
    quickSort(numbers, low, left - 1);
    quickSort(numbers, left + 1, high);
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5, 34, 19, 10, 7, 4};
    QuickSort quickSort = new QuickSort();
    quickSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
