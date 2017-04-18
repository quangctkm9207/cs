package datastructure.stack;

/**
 * Created by user on 3/17/17.
 */
public class ReverseArray {

    public static <E> void reverse(E[] a) {
        Stack<E> buffer = new ArrayStack<E>(a.length);
        for (int i = 0; i < a.length; i++) {
            buffer.push(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = buffer.pop();
        }
    }
}
