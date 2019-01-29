import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BinNode<T extends Comparable<T>> extends AbstractBinNode<T> {

	public BinNode(T value, BinNode<T> child, BinNode<T> sibling) {
		super(value, child, sibling);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTree() {
		// check if root
		if (sibling != null) {
			return false;
		}

		// check if DAG
		BinNode[] toVisit = new BinNode[15000];
		HashSet<BinNode> visited = new HashSet<>();
		int pointer = 0, max = 0;
		toVisit[max] = this;
		max+=1;
		while (max > pointer) {
			BinNode tmp = toVisit[pointer++];
			

			if (visited.contains(tmp)) {
				return false;
			} else {
				visited.add(tmp);
			}

			if (tmp.child != null) {
				toVisit[max++] = tmp.child;
			}

			if (tmp.sibling != null) {
				toVisit[max++] = tmp.sibling;
			}

		}

		return true;
	}

	@Override
	public int getHeight() {
		if (!isTree()) {
			return -1;
		}

		if (child == null) {
			return 0;
		}
		return child.getHeightNotRoot() + 1;

	}

	private int getHeightNotRoot() {
		if (child == null && (sibling == null || sibling != null && sibling.child == null)) {
			return 0;
		}

		int mC = 0, sC = 0;

		if (child != null) {
			mC = child.getHeightNotRoot();
		}

		if (sibling != null && sibling.child != null) {
			sC = sibling.child.getHeightNotRoot();
		}

		if (mC == -1 || sC == -1) {
			return -1;
		}

		return mC >= sC ? mC + 1 : sC + 1;
	}

	@Override
	public int getBranchingFactor() {
		if (!isTree()) {
			return -1;
		}

		if (child == null) {
			return 0;
		}
		return child.getBranchingFactorNotRoot();
	}

	private int getBranchingFactorNotRoot() {
		int sBF = 0, cBF = 0, scBF = 0;

		if (sibling != null) {
			sBF = countSiblings() + 1;
		}

		if (child != null) {
			cBF = child.getBranchingFactorNotRoot();
		}

		if (sibling != null) {
			scBF = sibling.getBranchingFactorNotRoot();
		}

		// find max
		if (sBF < cBF) {
			sBF = cBF;
		}

		return sBF >= scBF ? sBF : scBF;

	}

	private int countSiblings() {
		if (sibling != null) {
			return sibling.countSiblings() + 1;
		}

		return 0;
	}

	@Override
	public boolean isBinaryTree() {
		if (!isTree() || getBranchingFactor() != 2) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isBinarySearchTree() {
		return isBinaryTree() ? isBinarySearchTreeCheckOrder() : false;
	}

	private boolean isBinarySearchTreeCheckOrder() {
		if (child == null && (sibling == null || sibling.child == null && value.compareTo(sibling.value) < 0)) {
			return true;
		}

		return child.isBinarySearchTreeCheckOrder()
				&& (child.sibling == null || child.sibling.isBinarySearchTreeCheckOrder())
				&& child.value.compareTo(value) < 0
				&& (child.sibling == null || value.compareTo(child.sibling.value) < 0);

	}

	@Override
	public boolean isAVLTree() {
		return isBinarySearchTree() ? isAVLTreeCheckOrder() : false;
	}

	public boolean isAVLTreeCheckOrder() {
		if (child == null) {
			return true;
		}

		if (child != null && child.sibling != null) {
			int x = child.getHeight() - child.sibling.getHeight();
			return (x < 0 ? x * -1 <= 2 : x <= 2) && child.isAVLTreeCheckOrder() && child.sibling.isAVLTreeCheckOrder();
		}

		return child.isAVLTreeCheckOrder() && (child.sibling == null && child.getHeight() <= 1);
	}

	@Override
	public boolean isMinHeap() {
		return isBinaryTree() ? isMinHeapCheckOrder() : false;
	}

	private boolean isMinHeapCheckOrder() {
		if (child == null && sibling == null) {
			return true;
		}

		return (child == null || child.isMinHeapCheckOrder())
				&& (child == null || child.sibling == null || child.sibling.isMinHeapCheckOrder())
				&& (child == null || child.sibling == null || child.value.compareTo(value) >= 0)
				&& (child == null || child.sibling == null || child.sibling.value.compareTo(value) >= 0);

	}

	@Override
	public boolean isMaxHeap() {
		return isBinaryTree() ? isMaxHeapCheckOrder() : false;
	}

	private boolean isMaxHeapCheckOrder() {
		if (child == null && sibling == null) {
			return true;
		}

		return (child == null || child.isMinHeapCheckOrder())
				&& (child == null || child.sibling == null || child.sibling.isMinHeapCheckOrder())
				&& (child == null || child.sibling == null || child.value.compareTo(value) <= 0)
				&& (child == null || child.sibling == null || child.sibling.value.compareTo(value) <= 0);

	}

}
