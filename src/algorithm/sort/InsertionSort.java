package algorithm.sort;

import java.util.Arrays;

/**
 * InsertionSort
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * @author Quang Nguyen.
 */
public class InsertionSort {

  public void sort(int[] numbers) {
    int N = numbers.length;
    int value;
    int hole;
    for(int i = 1; i < N; i++) {
      value = numbers[i];
      hole = i;
      while (hole > 0 && numbers[hole - 1] > value) {
        numbers[hole] = numbers[hole - 1];
        hole --;
      }
      numbers[hole] = value;
    }
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5};
    InsertionSort insertionSort = new InsertionSort();
    insertionSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
