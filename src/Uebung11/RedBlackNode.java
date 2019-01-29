import java.util.ArrayList;

class RedBlackNode<E> {
	/**
	 * The value stored in this node.
	 */
	protected final E value;
	/**
	 * The parent node of this node or null if this node is the root of the tree.
	 */
	protected RedBlackNode<E> parent;
	/**
	 * The left child of this node (contains the values smaller than this node).
	 */
	protected RedBlackNode<E> left;
	/**
	 * The right child of this node (contains the values greater than this node).
	 */
	protected RedBlackNode<E> right;
	/**
	 * The colour of this node.
	 */
	protected RedBlackColour colour;

	protected RedBlackNode(E value) {
		if (value == null) {
			throw new NullPointerException("The value must not be null.");
		}
		//System.out.println(value);
		this.value = value;
		this.colour = RedBlackColour.RED;
	}
	
	
	@Override
	public String toString() {
		return (left != null ? left.toString() : "") + "[ " +  value.toString() + "  " + colour   + "(Parent:" + (parent != null ? parent.value.toString() : "istRoot") + ")" +  "   Kinder: ( " + (left != null ? left.value.toString() : "null") + " | " + (right != null ? right.value.toString() : "null") + ") ]" + (right != null ? right.toString() : "");
	}
	
	public ArrayList<E> listForChecking(){
		ArrayList<E> tmp = (left != null ? left.listForChecking() : new ArrayList<E>());
		tmp.add(value);
		tmp.addAll((right != null ? right.listForChecking() : new ArrayList<E>()));
		return tmp;
	}
}