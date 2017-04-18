package datastructure.list;

/**
 * Created by user on 3/19/17.
 *
 * Represents positional label in Positional List
 */
public interface Position<E> {

    /**
     * Returns the element stored at this position
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
