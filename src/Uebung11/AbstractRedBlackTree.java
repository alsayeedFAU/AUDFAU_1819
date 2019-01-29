import java.util.Iterator;

public abstract class AbstractRedBlackTree<E extends Comparable<E>> {
	/**
	 * The root node of this tree or null if this tree is empty.
	 */
	protected RedBlackNode<E> root;

	/**
	 * Returns {@code true} if this tree contains the specified element. More formally, returns {@code true} if and only if this tree contains an element {@code e} that equals {@code o} in terms of {@link #Comparable.compareTo(Object)}.
	 * 
	 * @param o
	 *            - object to be checked for containment in this tree
	 * @return {@code true} if this tree contains the specified element
	 */
	public abstract boolean contains(E o);

	/**
	 * Returns the first (lowest) element currently in this tree or {@code null} if this tree is empty.
	 * 
	 * @return the first (lowest) element currently in this tree or {@code null} if this tree is empty
	 */
	public abstract E first();

	/**
	 * Returns the last (highest) element currently in this tree or {@code null} if this tree is empty.
	 * 
	 * @return the last (highest) element currently in this tree or {@code null} if this tree is empty
	 */
	public abstract E last();

	/**
	 * Adds the specified element to this tree if it is not already present. More formally, adds the specified element {@code e} to this tree if the tree contains no element {@code e2} that equals {@code e} in terms of {@link #Comparable.compareTo(Object)}. If this tree already contains the element, the call leaves the tree unchanged and returns {@code false}.
	 * 
	 * @param e
	 *            - element to be added to this tree
	 * @return {@code true} if this tree did not already contain the specified element
	 * @throws NullPointerException
	 *             if the specified element is null
	 */
	public abstract boolean add(E e);

	/**
	 * Returns an iterator over the elements in this tree in ascending order.
	 * 
	 * @return an iterator over the elements in this tree in ascending order
	 */
	public abstract Iterator<E> iterator();
}