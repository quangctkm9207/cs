package algorithm.sort;

import java.util.Arrays;

/**
 * Bubble Sort
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * A simple sort that repeatedly traverse along the sequence of elements,
 * compare each pair of adjacent items and swaps them if they are in a wrong order.
 *
 * @author Quang Nguyen.
 */
public class BubbleSort {

  public void sort(int[] numbers) {

    int N = numbers.length;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N - i - 1; j++) {
        if (numbers[j] > numbers[j + 1]) {
          int temp = numbers[j];
          numbers[j] = numbers[j + 1];
          numbers[j + 1] = temp;
        }
      }
    }
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5};
    BubbleSort bubbleSort = new BubbleSort();
    bubbleSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
