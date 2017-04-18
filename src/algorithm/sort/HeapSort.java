package algorithm.sort;

import java.util.Arrays;

/**
 * Heap Sort
 * Time Complexity: O(nlogn).
 * in-place sorting algorithm.
 *
 * @author Quang Nguyen.
 */
public class HeapSort {

  public void sort(int[] numbers) {
    int N = numbers.length;

    // Build heap (rearrange array)
    for (int i = N / 2 - 1; i >= 0; i--) {
      heapify(numbers, N, i);
    }
    // One by one extract an element from heap
    for (int i = N - 1; i >= 0; i--) {
      // Move current root to end
      int temp = numbers[0];
      numbers[0] = numbers[i];
      numbers[i] = temp;

      // Call max heapify on the reduced heap
      heapify(numbers, i, 0);
    }
  }

  /**
   * Heapifies a subtree
   * @param numbers original array.
   * @param n size of heap.
   * @param i rooted index.
   */
  public void heapify(int[] numbers, int n, int i) {
    int largest = i; // initialize largest as root
    int l = 2*i + 1; // left = 2*i + 1
    int r = 2*i + 2; // right = 2*i + 2

    // if left child is larger than root
    if (l < n && numbers[l] > numbers[largest]) {
      largest = l;
    }

    // if right child is larger than largest so far
    if (r < n && numbers[r] > numbers[largest]) {
      largest = r;
    }

    // if largest is not root
    if (largest != i) {
      int temp = numbers[i];
      numbers[i] = numbers[largest];
      numbers[largest] = temp;

      // Recursively heapify the affected sub-tree
      heapify(numbers, n, largest);
    }
  }

  public static void main(String[] args) {
    // A test case
    int[] numbers = {1, 4, 2, 14, 3, 5, 34, 19, 10, 7, 4};
    HeapSort heapSort = new HeapSort();
    heapSort.sort(numbers);
    System.out.print(Arrays.toString(numbers));
  }
}
