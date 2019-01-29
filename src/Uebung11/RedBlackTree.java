import java.util.Iterator;

public class RedBlackTree<E extends Comparable<E>> extends AbstractRedBlackTree<E> {

	@Override
	public boolean contains(E o) {
		if (root == null) {
			return false;
		}

		RedBlackNode<E> tmp = root;

		while (tmp != null) {
			int compare = tmp.value.compareTo(o);

			if (compare == 0) {
				return true;
			} else {
				if (compare < 0) {
					tmp = tmp.right;
				} else {
					tmp = tmp.left;
				}
			}
		}

		return false;
	}

	@Override
	public E first() {
		if (root == null) {
			return null;
		}
		RedBlackNode<E> tmp = root;

		while (tmp.left != null) {
			tmp = tmp.left;
		}

		return tmp.value;
	}

	@Override
	public E last() {
		if (root == null) {
			return null;
		}
		RedBlackNode<E> tmp = root;

		while (tmp.right != null) {
			tmp = tmp.right;
		}

		return tmp.value;
	}


	@Override
	public boolean add(E e) throws NullPointerException {
		
		RedBlackNode<E> next = new RedBlackNode<E>(e);

		if (root == null) {
			next.colour = RedBlackColour.BLACK;
			root = next;
			return true;
		}

		RedBlackNode<E> tmp = root;
		while (tmp != null) {

			int i = tmp.value.compareTo(e);
			if (i == 0) {
				return false;
			}

			if (i > 0) {
				if (tmp.left == null) {
					next.parent = tmp;
					tmp.left = next;
					tmp = null;
				} else {
					tmp = tmp.left;
				}
			} else {
				if (tmp.right == null) {
					next.parent = tmp;
					tmp.right = next;
					tmp = null;
				} else {
					tmp = tmp.right;
				}
			}

		}

		RedBlackNode<E> helper = next;

		while (helper != null) {

			if (helper.parent == null) {
				helper.colour = RedBlackColour.BLACK;
				return true;
			}

			if (helper.parent.colour == RedBlackColour.BLACK) {
				return true;
			}

			if (helper.parent.parent == null && helper.parent.colour == RedBlackColour.RED
					|| helper.parent.colour == RedBlackColour.BLACK) {
				helper.parent.colour = RedBlackColour.BLACK;

				return true;
			}

			if (helper.parent.parent != null && helper.parent.colour == RedBlackColour.RED) {
				RedBlackNode<E> g = helper.parent.parent;
				RedBlackNode<E> p = helper.parent;

				if (p.equals(g.left)) {

					if (g != null && g.right == null || g.right.colour == RedBlackColour.BLACK) {
						if (p.right != null && p.right.equals(helper) && g.left != null && g.left.equals(p)
								&& p.colour == RedBlackColour.RED) {

							RedBlackNode<E> t1 = p.left;
							if (t1 != null)
								t1.parent = p;
							RedBlackNode<E> t2 = helper.left;
							if (t2 != null)
								t2.parent = p;
							RedBlackNode<E> t3 = helper.right;
							if (t3 != null)
								t3.parent = helper;

							g.left = helper;
							helper.parent = g;
							helper.left = p;
							p.parent = helper;
							helper.left.right = t2;
							helper = p;
						}
					}

				} else {

					if (g != null && g.left == null || g.left.colour == RedBlackColour.BLACK) {
						if (p.left != null && p.left.equals(helper) && g.right != null && g.right.equals(p)
								&& p.colour == RedBlackColour.RED) {

							RedBlackNode<E> t1 = helper.left;
							if (t1 != null)
								t1.parent = helper;
							RedBlackNode<E> t2 = helper.right;
							if (t2 != null)
								t2.parent = p;
							RedBlackNode<E> t3 = p.right;
							if (t3 != null)
								t3.parent = p;

							g.right = helper;
							helper.parent = g;
							helper.right = p;
							p.parent = helper;
							helper.right.left = t2;

							helper = p;

						}
					}
				}

			}

			if (helper.parent.parent != null && helper.parent.colour == RedBlackColour.RED) {
				RedBlackNode<E> g = helper.parent.parent;
				RedBlackNode<E> p = helper.parent;

				if (p.equals(g.left)) {
					if (g.right == null || g.right.colour == RedBlackColour.BLACK) {
						if (p.left != null && p.left.equals(helper) && g.left != null && g.left.equals(p)) {

							RedBlackNode<E> t1 = helper.left;
							if (t1 != null)
								t1.parent = helper;
							RedBlackNode<E> t2 = helper.right;
							if (t2 != null)
								t2.parent = helper;
							RedBlackNode<E> t3 = p.right;
							if (t3 != null)
								t3.parent = g;
							RedBlackNode<E> t4 = g.right;
							if (t4 != null)
								t4.parent = g;

							p.parent = g.parent;

							if (p.parent == null) {
								root = p;
							} else {
								if (p.parent.left != null && p.parent.left.equals(g)) {
									p.parent.left = p;
								} else {
									p.parent.right = p;
								}
							}

							p.left = helper;
							helper.parent = p;
							p.right = g;
							g.parent = p;

							p.left.left = t1;
							p.left.right = t2;
							p.right.left = t3;
							p.right.right = t4;

							p.left.colour = p.right.colour = RedBlackColour.RED;
							p.colour = RedBlackColour.BLACK;

							return true;

						}
					}
				} else {
					if (g.left == null || g.left.colour == RedBlackColour.BLACK) {
						if (p.right != null && p.right.equals(helper) && g.right != null && g.right.equals(p)) {

							RedBlackNode<E> t1 = g.left;
							if (t1 != null)
								t1.parent = g;
							RedBlackNode<E> t2 = p.left;
							if (t2 != null)
								t2.parent = g;
							RedBlackNode<E> t3 = helper.left;
							if (t3 != null)
								t3.parent = helper;
							RedBlackNode<E> t4 = helper.right;
							if (t4 != null)
								t4.parent = helper;

							p.parent = g.parent;

							if (p.parent == null) {
								root = p;
							} else {
								if (p.parent.left != null && p.parent.left.equals(g)) {
									p.parent.left = p;
								} else {
									p.parent.right = p;
								}
							}

							p.left = g;
							g.parent = p;
							p.right = helper;
							helper.parent = p;

							p.left.left = t1;
							p.left.right = t2;
							p.right.left = t3;
							p.right.right = t4;

							p.left.colour = p.right.colour = RedBlackColour.RED;
							p.colour = RedBlackColour.BLACK;

							return true;
						}
					}
				}
			}

			if (helper.parent.parent != null && helper.parent.colour == RedBlackColour.RED) {
				RedBlackNode<E> g = helper.parent.parent;
				RedBlackNode<E> p = helper.parent;

				if (g != null && p.equals(g.left)) {
					if (g.right != null && g.right.colour == RedBlackColour.RED) {

						RedBlackNode<E> u = g.right;
						g.colour = RedBlackColour.RED;
						p.colour = u.colour = RedBlackColour.BLACK;
						helper = g;

					}
				} else {
					if (g != null && g.left != null && g.left.colour == RedBlackColour.RED) {

						RedBlackNode<E> u = g.left;
						g.colour = RedBlackColour.RED;
						p.colour = u.colour = RedBlackColour.BLACK;

						helper = g;

					}
				}
			}
		}

		return true;

	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> i = new Iterator<E>() {
			RedBlackNode<E> smallest = findSmallest();
			RedBlackNode<E> biggest = findBiggest();
			RedBlackNode<E> tmp = findSmallest();

			@Override
			public boolean hasNext() {
				return !(tmp == null);
			}

			@Override
			public E next() {
				E value = tmp.value;
				if (tmp.equals(biggest)) {
					tmp = null;
					return value;
				}

				if (tmp.left == null && tmp.right == null) {
					if (tmp.parent.left != null && tmp.parent.left.equals(tmp)) {
						tmp = tmp.parent;
						return value;
					} else {
						RedBlackNode<E> helper = tmp.parent;
						while (helper.right != null && helper.right.equals(tmp)) {
							tmp = helper;
							helper = helper.parent;
						}
						tmp = helper;
						return value;
					}
				} else if (tmp.right != null) {
					tmp = tmp.right;

					if (tmp.left != null) {
						while (tmp.left != null) {
							tmp = tmp.left;
						}
					}
					return value;
				}

				return value;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			private RedBlackNode<E> findSmallest() {
				RedBlackNode<E> tmp = root;

				while (tmp.left != null) {
					tmp = tmp.left;
				}
				return tmp;
			}

			private RedBlackNode<E> findBiggest() {
				RedBlackNode<E> tmp = root;

				while (tmp.right != null) {
					tmp = tmp.right;
				}
				return tmp;
			}

		};

		return i;
	}

}
