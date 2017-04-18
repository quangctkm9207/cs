package algorithm.sort;

import java.util.Arrays;

/**
 * Selection Sort
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * @author Quang Nguyen
 */
public class SelectionSort {

  public void sort(int[] numbers) {
    int N = numbers.length;
    for (int i = 0; i < N - 1; i ++) {
      for (int j = i; j < N - 1; j ++) {
        if (numbers[j] > numbers[j + 1]){
          int temp = numbers[j + 1];
          numbers[j + 1] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5};
    SelectionSort selectionSort = new SelectionSort();
    selectionSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
