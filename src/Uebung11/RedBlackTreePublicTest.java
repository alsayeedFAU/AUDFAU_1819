import org.junit.*;
import static org.junit.Assert.*;

import java.io.Console;
import java.lang.reflect.*;
import java.util.*;

public class RedBlackTreePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_contains_first_last = "contains/first/last";
	protected static final String EX_NAME_add = "add";
	protected static final String EX_NAME_iterator = "iterator";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	protected static final void check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		@SuppressWarnings("rawtypes")
		Class<RedBlackTree> clazz = RedBlackTree.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", AbstractRedBlackTree.class,
				clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " soll keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1,
				getDeclaredConstructors(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!",
				getDeclaredMethods(AbstractRedBlackTree.class).length, getDeclaredMethods(clazz).length);
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__contains_first_last__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__add__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__iterator__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	// ========== PUBLIC TEST ==========
	// -------------------- EX_NAME_contains_first_last --------------------
	@Test(timeout = 1666)
	public void pubTest__contains() {
		for (int pass = 0; pass < 7; pass++) {
			int height = 5 + RND.nextInt(10);
			RedBlackNode<AuDing> root = generateFullTree(height);
			LinkedList<RedBlackNode<AuDing>> nodes = collectAndColourizeNodes(root, true);
			LinkedList<AuDing> values = collectAndFillInValues(nodes, true);
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			tree.root = root;
			for (AuDing value : values) {
				AuDing oLT = new AuDing(value.v - 1), oEQ = new AuDing(value.v), oGT = new AuDing(value.v + 1);
				assertFalse("Wrong result.", tree.contains(oLT));
				assertEquals("Wrong number of comparisons!", height, oLT.ccc);
				boolean actual = tree.contains(oEQ);
				assertTrue("Wrong result.", actual);
				assertEquals("Wrong number of comparisons!", oGT.depthInTree, oGT.ccc);
				assertFalse("Wrong result.", tree.contains(oGT));
				assertEquals("Wrong number of comparisons!", height, oGT.ccc);
			}
		}
	}

	@Test(timeout = 1666)
	public void pubTest__first__last() {
		for (int pass = 0; pass < 7; pass++) {
			int height = 5 + RND.nextInt(10);
			RedBlackNode<AuDing> root = generateFullTree(height);
			LinkedList<RedBlackNode<AuDing>> nodes = collectAndColourizeNodes(root, true);
			collectAndFillInValues(nodes, true);
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			tree.root = root;
			AuDing actualFirst = tree.first(), actualLast = tree.last();
			assertSame("Wrong result.", nodes.getFirst().value, actualFirst);
			assertSame("Wrong result.", nodes.getLast().value, actualLast);
			for (RedBlackNode<AuDing> node : nodes) {
				assertEquals("Wrong number of comparisons!", 0, node.value.ccc);
			}
		}
	}

	// -------------------- EX_NAME_add --------------------
	@Test(timeout = 1666)
	public void pubTest__add__cases_0_1a_3a_4a__random() {
		for (int pass = 0; pass < 7; pass++) {
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			RedBlackNode<AuDing> trt, l, r;
			assertNull("init: Wrong root node.", tree.root);
			// ---------- case 0 ----------
			AuDing a = new AuDing(RND.nextInt(4711));
			assertTrue("case 0: Wrong result.", tree.add(a));
			assertEquals("case 0: Wrong number of comparisons!", 0, a.ccc);
			trt = tree.root;
			assertNotNull("case 0: Wrong root node.", trt);
			assertSame("case 0: Wrong root value.", a, trt.value);
			assertNull("case 0: Wrong root links.", trt.parent);
			assertNull("case 0: Wrong root links.", trt.left);
			assertNull("case 0: Wrong root links.", trt.right);
			assertSame("case 0: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			// ---------- case 1a ----------
			AuDing b = new AuDing(a.v - 42 - RND.nextInt(42));
			assertTrue("case 1a: Wrong result.", tree.add(b));
			assertEquals("case 1a: Wrong number of comparisons!", 1, a.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 1, b.ccc);
			trt = tree.root;
			assertNotNull("case 1a: Wrong root node.", trt);
			assertSame("case 1a: Wrong root value.", a, trt.value);
			assertNull("case 1a: Wrong root links.", trt.parent);
			assertNotNull("case 1a: Wrong root links.", trt.left);
			assertNull("case 1a: Wrong root links.", trt.right);
			assertSame("case 1a: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			l = trt.left;
			assertNotNull("case 1a: Wrong child node.", l);
			assertSame("case 1a: Wrong child value.", b, l.value);
			assertSame("case 1a: Wrong child links.", trt, l.parent);
			assertNull("case 1a: Wrong child links.", l.left);
			assertNull("case 1a: Wrong child links.", l.right);
			assertSame("case 1a: Wrong child color.", RedBlackColour.RED, l.colour);
			// ---------- case 3a+4a ----------
			AuDing c = new AuDing(a.v - 15 - RND.nextInt(15));
			assertTrue("case 1: Wrong result.", tree.add(c));
			assertEquals("case 1a: Wrong number of comparisons!", 2, a.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 2, b.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 2, c.ccc);
			trt = tree.root;
			assertNotNull("case 3a/4a: Wrong root node.", trt);
			assertSame("case 3a/4a: Wrong root value.", c, trt.value);
			assertNull("case 3a/4a: Wrong root links.", trt.parent);
			assertNotNull("case 3a/4a: Wrong root links.", trt.left);
			assertNotNull("case 3a/4a: Wrong root links.", trt.right);
			assertSame("case 3a/4a: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			l = trt.left;
			assertNotNull("case 3a/4a: Wrong child node.", l);
			assertSame("case 3a/4a: Wrong child value.", b, l.value);
			assertSame("case 3a/4a: Wrong child links.", trt, l.parent);
			assertNull("case 3a/4a: Wrong child links.", l.left);
			assertNull("case 3a/4a: Wrong child links.", l.right);
			assertSame("case 3a/4a: Wrong child color.", RedBlackColour.RED, l.colour);
			r = trt.right;
			assertNotNull("case 3a/4a: Wrong child node.", r);
			assertSame("case 3a/4a: Wrong child value.", a, r.value);
			assertSame("case 3a/4a: Wrong child links.", trt, r.parent);
			assertNull("case 3a/4a: Wrong child links.", r.left);
			assertNull("case 3a/4a: Wrong child links.", r.right);
			assertSame("case 3a/4a: Wrong child color.", RedBlackColour.RED, l.colour);
			// ---------- case false ----------
			AuDing x = new AuDing(a.v), y = new AuDing(b.v), z = new AuDing(c.v);
			assertFalse("case false: Wrong result.", tree.add(x));
			assertFalse("case false: Wrong result.", tree.add(y));
			assertFalse("case false: Wrong result.", tree.add(z));
			assertEquals("case 1a: Wrong number of comparisons!", 2, x.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 2, y.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 1, z.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 3, a.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 3, b.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 5, c.ccc);
		}
		
		check__add__random();
	}

	@Test(timeout = 1666)
	public void pubTest__add__cases_0_1b_3b_4b__random() {
		for (int pass = 0; pass < 7; pass++) {
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			RedBlackNode<AuDing> trt, l, r;
			assertNull("init: Wrong root node.", tree.root);
		// ---------- case 0 ----------
			AuDing a = new AuDing(RND.nextInt(4711));
			assertTrue("case 0: Wrong result.", tree.add(a));
			assertEquals("case 1b: Wrong number of comparisons!", 0, a.ccc);
			trt = tree.root;
			assertNotNull("case 0: Wrong root node.", trt);
			assertSame("case 0: Wrong root value.", a, trt.value);
			assertNull("case 0: Wrong root links.", trt.parent);
			assertNull("case 0: Wrong root links.", trt.left);
			assertNull("case 0: Wrong root links.", trt.right);
			assertSame("case 0: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			// ---------- case 1b ----------
			AuDing b = new AuDing(a.v + 42 + RND.nextInt(42));
			assertTrue("case 1b: Wrong result.", tree.add(b));
			assertEquals("case 1b: Wrong number of comparisons!", 1, a.ccc);
			assertEquals("case 1b: Wrong number of comparisons!", 1, b.ccc);
			trt = tree.root;
			assertNotNull("case 1b: Wrong root node.", trt);
			assertSame("case 1b: Wrong root value.", a, trt.value);
			assertNull("case 1b: Wrong root links.", trt.parent);
			assertNull("case 1b: Wrong root links.", trt.left);
			assertNotNull("case 1b: Wrong root links.", trt.right);
			assertSame("case 1b: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			r = trt.right;
			assertNotNull("case 1b: Wrong child node.", r);
			assertSame("case 1b: Wrong child value.", b, r.value);
			assertSame("case 1b: Wrong child links.", trt, r.parent);
			assertNull("case 1b: Wrong child links.", r.left);
			assertNull("case 1b: Wrong child links.", r.right);
			assertSame("case 1b: Wrong child color.", RedBlackColour.RED, r.colour);
			// ---------- case 3b+4b ----------
			AuDing c = new AuDing(a.v + 15 + RND.nextInt(15));
			assertTrue("case 1b: Wrong result.", tree.add(c));
			assertEquals("case 1b: Wrong number of comparisons!", 2, a.ccc);
			assertEquals("case 1b: Wrong number of comparisons!", 2, b.ccc);
			assertEquals("case 1b: Wrong number of comparisons!", 2, c.ccc);
			trt = tree.root;
			assertNotNull("case 3a/4a: Wrong root node.", trt);
			assertSame("case 3a/4a: Wrong root value.", c, trt.value);
			assertNull("case 3a/4a: Wrong root links.", trt.parent);
			assertNotNull("case 3a/4a: Wrong root links.", trt.left);
			assertNotNull("case 3a/4a: Wrong root links.", trt.right);
			assertSame("case 3a/4a: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			l = trt.left;
			assertNotNull("case 3a/4a: Wrong child node.", l);
			assertSame("case 3a/4a: Wrong child value.", a, l.value);
			assertSame("case 3a/4a: Wrong child links.", trt, l.parent);
			assertNull("case 3a/4a: Wrong child links.", l.left);
			assertNull("case 3a/4a: Wrong child links.", l.right);
			assertSame("case 3a/4a: Wrong child color.", RedBlackColour.RED, l.colour);
			r = trt.right;
			assertNotNull("case 3a/4a: Wrong child node.", r);
			assertSame("case 3a/4a: Wrong child value.", b, r.value);
			assertSame("case 3a/4a: Wrong child links.", trt, r.parent);
			assertNull("case 3a/4a: Wrong child links.", r.left);
			assertNull("case 3a/4a: Wrong child links.", r.right);
			assertSame("case 3a/4a: Wrong child color.", RedBlackColour.RED, l.colour);
			// ---------- case false ----------
			AuDing x = new AuDing(a.v), y = new AuDing(b.v), z = new AuDing(c.v);
			assertFalse("case false: Wrong result.", tree.add(x));
			assertFalse("case false: Wrong result.", tree.add(y));
			assertFalse("case false: Wrong result.", tree.add(z));
			assertEquals("case 1a: Wrong number of comparisons!", 2, x.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 2, y.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 1, z.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 3, a.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 3, b.ccc);
			assertEquals("case 1a: Wrong number of comparisons!", 5, c.ccc);
		}

		check__add__random();
	}
	
	
	

	@Test(timeout = 1666)
	public void pubTest__add__cases_0_1x2_5a__random() {
		for (int pass = 0; pass < 7; pass++) {
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			RedBlackNode<AuDing> trt, l, r, ll;
			assertNull("init: Wrong root node.", tree.root);
			// ---------- case 0, 1a, 1b ----------
			AuDing a = new AuDing(RND.nextInt(4711));
			AuDing b = new AuDing(a.v - 42 - RND.nextInt(42));
			AuDing c = new AuDing(a.v + 42 + RND.nextInt(42));
			assertTrue("case 0: Wrong result.", tree.add(a));
			if (RND.nextBoolean()) {
				assertTrue("case 1a: Wrong result.", tree.add(b));
				assertTrue("case 1b: Wrong result.", tree.add(c));
			} else {
				assertTrue("case 1b: Wrong result.", tree.add(c));
				assertTrue("case 1a: Wrong result.", tree.add(b));
			}
			assertEquals("case 1b: Wrong number of comparisons!", 2, a.ccc);
			assertEquals("case 1b: Wrong number of comparisons!", 1, b.ccc);
			assertEquals("case 1b: Wrong number of comparisons!", 1, c.ccc);
			trt = tree.root;
			assertNotNull("case 1x2: Wrong root node.", trt);
			assertSame("case 1x2: Wrong root value.", a, trt.value);
			assertNull("case 1x2: Wrong root links.", trt.parent);
			assertNotNull("case 1x2: Wrong root links.", trt.left);
			assertNotNull("case 1x2: Wrong root links.", trt.right);
			assertSame("case 1x2: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			l = trt.left;
			assertNotNull("case 1x2: Wrong child node.", l);
			assertSame("case 1x2: Wrong child value.", b, l.value);
			assertSame("case 1x2: Wrong child links.", trt, l.parent);
			assertNull("case 1x2: Wrong child links.", l.left);
			assertNull("case 1x2: Wrong child links.", l.right);
			assertSame("case 1x2: Wrong child color.", RedBlackColour.RED, l.colour);
			r = trt.right;
			assertNotNull("case 1x2: Wrong child node.", r);
			assertSame("case 1x2: Wrong child value.", c, r.value);
			assertSame("case 1x2: Wrong child links.", trt, r.parent);
			assertNull("case 1x2: Wrong child links.", r.left);
			assertNull("case 1x2: Wrong child links.", r.right);
			assertSame("case 1x2: Wrong child color.", RedBlackColour.RED, l.colour);
			
			// ---------- case 5a ----------
			AuDing d = new AuDing(b.v - 42 - RND.nextInt(42));
			assertTrue("case 5a: Wrong result.", tree.add(d));
			trt = tree.root;
			assertNotNull("case 5a: Wrong root node.", trt);
		
			assertSame("case 5a: Wrong root value.", a, trt.value);
			assertNull("case 5a: Wrong root links.", trt.parent);
			assertNotNull("case 5a: Wrong root links.", trt.left);
			assertNotNull("case 5a: Wrong root links.", trt.right);
			assertSame("case 5a: Wrong root color.", RedBlackColour.BLACK, trt.colour);
			l = trt.left;
			assertNotNull("case 5a: Wrong child node.", l);
			assertSame("case 5a: Wrong child value.", b, l.value);
			assertSame("case 5a: Wrong child links.", trt, l.parent);
			assertNotNull("case 5a: Wrong child links.", l.left);
			assertNull("case 5a: Wrong child links.", l.right);
			assertSame("case 5a: Wrong child color.", RedBlackColour.BLACK, l.colour);
			r = trt.right;
			assertNotNull("case 5a: Wrong child node.", r);
			assertSame("case 5a: Wrong child value.", c, r.value);
			assertSame("case 5a: Wrong child links.", trt, r.parent);
			assertNull("case 5a: Wrong child links.", r.left);
			assertNull("case 5a: Wrong child links.", r.right);
			assertSame("case 5a: Wrong child color.", RedBlackColour.BLACK, l.colour);
			ll = l.left;
			assertNotNull("case 5a: Wrong sub-child node.", ll);
			assertSame("case 5a: Wrong sub-child value.", d, ll.value);
			assertSame("case 5a: Wrong sub-child links.", l, ll.parent);
			assertNull("case 5a: Wrong sub-child links.", ll.left);
			assertNull("case 5a: Wrong sub-child links.", ll.right);
			assertSame("case 5a: Wrong sub-child color.", RedBlackColour.RED, ll.colour);
		}
		check__add__random();
	}
	
	

	protected static final void check__add__random() {
		AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
		HashSet<Long> valuesLong = new HashSet<>();
		HashSet<AuDing> values = new HashSet<>();
		for (int charge = 0; charge < 7; charge++) {
			for (int i = 0; i < 100; i++) {
				long v = RND.nextInt(4711);
				AuDing x = new AuDing(v);
				if (valuesLong.add(v)) {
					assertTrue("add: Wrong result.", tree.add(x));
					ArrayList<AuDing> tmp = tree.root.listForChecking();
					if(x.ccc > ((int) (2 * Math.log(valuesLong.size() + 2) / Math.log(2) - 2)))
					System.out.println("ist: " + x.ccc + "  soll: "	+ ((int) (2 * Math.log(valuesLong.size() + 2) / Math.log(2) - 2)));
					assertTrue("add: Wrong number of comparisons!",x.ccc <= (int) (2 * Math.log(valuesLong.size() + 2) / Math.log(2) - 2));
					values.add(x);
				} else {
					boolean actual = tree.add(x);
					assertFalse("add: Wrong result.", actual);
				}
				//System.out.println(tree.root);
				
				
			}
			@SuppressWarnings("unchecked")
			HashSet<AuDing> valuesClone = (HashSet<AuDing>) values.clone();
			//System.out.println(tree.root.toString());
			check__tree_structure(tree.root, valuesClone, new HashSet<RedBlackNode<AuDing>>());
			assertTrue("Tree is missing some values!", valuesClone.isEmpty());
			LinkedList<RedBlackNode<AuDing>> nodes = collectAndColourizeNodes(tree.root, false);
			for (int i = 1; i < nodes.size(); i++) {
				assertTrue("Tree violates the binary search tree requirement (smaller <- root -> bigger).",
						nodes.get(i - 1).value.v < nodes.get(i).value.v);
			}
			check_black_height(tree.root);

//			System.out.println("---------------");
		}
	}
	
	

	private static final void check__tree_structure(RedBlackNode<AuDing> node, HashSet<AuDing> values,
			HashSet<RedBlackNode<AuDing>> visited) {
		if (node != null) {
			assertTrue("Tree contains loops!", visited.add(node));
			assertTrue("Node has no value!", node.value != null);
			assertTrue("Tree contains duplicate values!", values.remove(node.value));
			assertTrue("Node has no color!", node.colour == RedBlackColour.BLACK || node.colour == RedBlackColour.RED);
			if (node.parent != null) {
				assertTrue("Parent doesn't know his own child!", node.parent.left == node || node.parent.right == node);
			}
			if (node.left != null) {
				assertTrue("Left child doesn't know his own parent!", node.left.parent == node);
				if (node.colour == RedBlackColour.RED) {
					assertTrue("Left child violates \"!RR\" requirement!", node.left.colour != RedBlackColour.RED);
				}
				check__tree_structure(node.left, values, visited);
			}
			if (node.right != null) {
				if(!(node.right.parent == node)) {
//					System.out.println("   " + node.value);
				}
				assertTrue("Right child doesn't know his own parent!", node.right.parent == node);
				if (node.colour == RedBlackColour.RED) {
					assertTrue("Right child violates \"!RR\" requirement!", node.right.colour != RedBlackColour.RED);
				}
				check__tree_structure(node.right, values, visited);
			}
		}
	}

	private static final int check_black_height(RedBlackNode<AuDing> node) {
		if (node == null) {
			return 0;
		} else {
			int bdLeft = check_black_height(node.left);
			int bdRight = check_black_height(node.left);
			assertTrue(
					"Tree violates \"S#=\" requirement (same number of black nodes from any root to every one of its leaves)!",
					bdLeft == bdRight);
			return ((node.colour == RedBlackColour.BLACK) ? 1 : 0) + bdLeft;
		}
	}
	
	

	// -------------------- EX_NAME_iterator --------------------
	@Test(timeout = 1666)
	public void pubTest__iterator() {
		for (int pass = 0; pass < 7; pass++) {
			int height = 4; // + RND.nextInt(10);
			RedBlackNode<AuDing> root = generateFullTree(height);
			LinkedList<RedBlackNode<AuDing>> nodes = collectAndColourizeNodes(root, true);
			LinkedList<AuDing> values = collectAndFillInValues(nodes, true);
			AbstractRedBlackTree<AuDing> tree = new RedBlackTree<>();
			tree.root = root;
			Iterator<AuDing> iterClean = values.iterator();
			Iterator<AuDing> iterStud = tree.iterator();
			Iterator<AuDing> iterCleanAsync = values.iterator();
			Iterator<AuDing> iterStudAsync = tree.iterator();
			while (iterClean.hasNext()) {
				assertTrue("Wrong result.", iterStud.hasNext());
				assertSame("Wrong result.", iterClean.next(), iterStud.next());
				if (RND.nextBoolean()) {
					assertTrue("Wrong result.", iterStudAsync.hasNext());
					assertSame("Wrong result.", iterCleanAsync.next(), iterStudAsync.next());
				}
			}
			assertFalse("Wrong result.", iterStud.hasNext());
			while (iterCleanAsync.hasNext()) {
				assertTrue("Wrong result.", iterStudAsync.hasNext());
				assertSame("Wrong result.", iterCleanAsync.next(), iterStudAsync.next());
			}
			assertFalse("Wrong result.", iterStudAsync.hasNext());
		}
	}

	// ========== HELPER ==========
	protected static final class AuDing implements Comparable<AuDing> {
		protected long ccc = 0; // compareTo call count
		protected long depthInTree = 0;
		protected long v;

		protected AuDing() {
		}

		protected AuDing(long v) {
//			System.out.println(v);
			this.v = v;
		}

		@Override
		public int compareTo(AuDing o) {
			ccc++;
			if (this != o) {
				o.ccc++; // make comparison "symmetrical" wrt ccc
			}
			return v > o.v ? 1 : v == o.v ? 0 : -1;
		}

		@Override
		public String toString() {
			return Long.toString(v);
		}
	}

	protected static final RedBlackNode<AuDing> generateFullTree(int depth) {
		RedBlackNode<AuDing> root = new RedBlackNode<AuDing>(new AuDing());
		if (depth > 1) {
			RedBlackNode<AuDing> left = generateFullTree(depth - 1);
			RedBlackNode<AuDing> right = generateFullTree(depth - 1);
			left.parent = right.parent = root;
			root.left = left;
			root.right = right;
		}
		return root;
	}

	protected static final <T> LinkedList<RedBlackNode<AuDing>> collectAndColourizeNodes(RedBlackNode<AuDing> root,
			boolean colourize) {
		LinkedList<RedBlackNode<AuDing>> collection = new LinkedList<RedBlackNode<AuDing>>();
		collectAndColourizeNodes(root, colourize, 0, collection);
		return collection;
	}

	protected static final void collectAndColourizeNodes(RedBlackNode<AuDing> root, boolean colourize, long depthInTree,
			LinkedList<RedBlackNode<AuDing>> collection) {
		if (root != null) {
			if (root.left != null) {
				collectAndColourizeNodes(root.left, colourize, depthInTree + 1, collection);
			}
			root.value.depthInTree = depthInTree;
			if (colourize) {
				root.colour = depthInTree % 2 == 0 ? RedBlackColour.BLACK : RedBlackColour.RED;
			}
			collection.add(root);
			if (root.right != null) {
				collectAndColourizeNodes(root.right, colourize, depthInTree + 1, collection);
			}
		}
	}

	protected static final LinkedList<AuDing> collectAndFillInValues(LinkedList<RedBlackNode<AuDing>> nodes,
			boolean fillIn) {
		LinkedList<AuDing> values = new LinkedList<>();
		long value = -RND.nextInt(4711_666);
		for (RedBlackNode<AuDing> node : nodes) {
			if (fillIn) {
				value = value + 2 + RND.nextInt(42);
				node.value.v = value;
			}
			values.add(node.value);
		}
		return values;
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE
	// REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	private static final Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static final Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static final Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static final Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}
}