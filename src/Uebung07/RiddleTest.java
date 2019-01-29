import java.util.Arrays;

import org.junit.Test;
import org.junit.Assert.*;

public class RiddleTest {

	@Test
	public void testSetNeighboursNullTooSmallTooBig() {
		RiddleNode r = new RiddleNode(null, 0, false);

		try {
			//r.setNeighbours(null);
		} catch (IllegalArgumentException e) {

		}
		try {
			//r.setNeighbours(new RiddleNode[5]);
		} catch (IllegalArgumentException e) {

		}
		try {
			r.setNeighbours(new RiddleNode[1]);
		} catch (IllegalArgumentException e) {

		}

		r.setNeighbours(
				new RiddleNode[] { new RiddleNode(RiddleType.VAL, 1, false), new RiddleNode(RiddleType.VAL, 2, false),
						new RiddleNode(RiddleType.VAL, 3, false), new RiddleNode(RiddleType.VAL, 4, false) });

	}

	@Test
	public void testGetNeighboursToString() {
		RiddleNode r = new RiddleNode(RiddleType.ADD, 0, false);

		RiddleNode[] expected = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 1, false),
				new RiddleNode(RiddleType.ADD, 2, false), new RiddleNode(RiddleType.DIV, 2, false) };

		r.setNeighbours(expected);
		RiddleNode[] actual = r.getNeighbours();
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals("Should be the same", expected[i], actual[i]);
		}

		Assert.assertEquals("toString wrong", "[VAL:1]", actual[0].toString());
		Assert.assertEquals("toString wrong", "[ADD]", actual[1].toString());
		Assert.assertEquals("toString wrong", "[DIV]", actual[2].toString());

		expected = new RiddleNode[] { new RiddleNode(RiddleType.MUL, 2, false),
				new RiddleNode(RiddleType.SUB, 2, false), new RiddleNode(RiddleType.EQ, 2, false),
				new RiddleNode(RiddleType.EQ, 2, true) };

		r.setNeighbours(expected);
		actual = r.getNeighbours();
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals("Should be the same", expected[i], actual[i]);
		}

		Assert.assertEquals("toString wrong", "[MUL]", actual[0].toString());
		Assert.assertEquals("toString wrong", "[SUB]", actual[1].toString());
		Assert.assertEquals("toString wrong", "[EQ]", actual[2].toString());
		Assert.assertEquals("toString wrong", "[EQ:#]", actual[3].toString());

	}

	@Test
	public void RiddleSolver() {
		RiddleSolver r = new RiddleSolver();
	}

	@Test
	public void testIsSolution() {
		RiddleNode[] r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 5, false),
				new RiddleNode(RiddleType.DIV, 0, false), new RiddleNode(RiddleType.VAL, 2, false) };

		Assert.assertEquals("Should be false", false, RiddleSolver.isSolution(r));

		r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 5, false) };

		Assert.assertEquals("Should be false", false, RiddleSolver.isSolution(r));
		

		r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.DIV, 0, false),
				new RiddleNode(RiddleType.VAL, 2, false), new RiddleNode(RiddleType.EQ, 5, false) };

		Assert.assertEquals("Should be false", false, RiddleSolver.isSolution(r));

		r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.EQ, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.EQ, 5, false) };

		Assert.assertEquals("Should be false", false, RiddleSolver.isSolution(r));

		r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 0, false), new RiddleNode(RiddleType.ADD, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.SUB, 0, false),
				new RiddleNode(RiddleType.VAL, 2, false), new RiddleNode(RiddleType.MUL, 0, false),
				new RiddleNode(RiddleType.VAL, 10, false), new RiddleNode(RiddleType.DIV, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.EQ, 0, false),
				new RiddleNode(RiddleType.VAL, 6, false) };

		Assert.assertEquals("(0+5-2)*10/5 = 6", true, RiddleSolver.isSolution(r));

		r = new RiddleNode[] { new RiddleNode(RiddleType.VAL, 0, false), new RiddleNode(RiddleType.ADD, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.SUB, 0, false),
				new RiddleNode(RiddleType.VAL, 2, false), new RiddleNode(RiddleType.MUL, 0, false),
				new RiddleNode(RiddleType.VAL, 10, false), new RiddleNode(RiddleType.DIV, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false), new RiddleNode(RiddleType.EQ, 0, false),
				new RiddleNode(RiddleType.VAL, 5, false) };

		Assert.assertEquals("(0+5-2)*10/5 != 5", false, RiddleSolver.isSolution(r));

	}

	@Test
	public void testAllPath() {
		RiddleNode r00 = new RiddleNode(RiddleType.VAL, 10, false);
		RiddleNode r02 = new RiddleNode(RiddleType.VAL, 10, false);
		RiddleNode r02t = new RiddleNode(RiddleType.VAL, 10, true);
		RiddleNode r11 = new RiddleNode(RiddleType.VAL, 1, false);
		RiddleNode r11t = new RiddleNode(RiddleType.VAL, 1, true);
		RiddleNode r20 = new RiddleNode(RiddleType.VAL, 2, false);
		RiddleNode r20t = new RiddleNode(RiddleType.VAL, 2, true);
		RiddleNode r22 = new RiddleNode(RiddleType.VAL, 20, false);
		RiddleNode r22t = new RiddleNode(RiddleType.VAL, 20, true);

		RiddleNode r01 = new RiddleNode(RiddleType.ADD, 0, false);
		RiddleNode r10 = new RiddleNode(RiddleType.MUL, 0, false);
		RiddleNode r12 = new RiddleNode(RiddleType.EQ, 0, false);
		RiddleNode r21 = new RiddleNode(RiddleType.EQ, 0, false);

		r00.setNeighbours(r10, r01);
		r01.setNeighbours(r00, r02, r11);
		r02.setNeighbours(r01, r12);
		r10.setNeighbours(r00, r11, r20);
		r11.setNeighbours(r01, r10, r12, r21);
		r12.setNeighbours(r22t, r11, r02);
		r20.setNeighbours(r10, r21);
		r21.setNeighbours(r22t, r20, r11);
		r22.setNeighbours(r12, r21);

		RiddleNode[][] path = RiddleSolver.allPaths(r00, new RiddleNode[0], new RiddleNode[0][]);
		Assert.assertEquals("Should be the same size", 12, path.length);

		RiddleNode[] expected = new RiddleNode[] { r00, r10, r20, r21, r22t };
		RiddleNode[] actual = RiddleSolver.solve(r00);
		System.out.println(Arrays.toString(actual));
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals("Should be the same", expected[i], actual[i]);
		}

		r12.setNeighbours(r22, r11, r02);
		r20.setNeighbours(r10, r21);
		r21.setNeighbours(r22, r20, r11);

		path = RiddleSolver.allPaths(r00, new RiddleNode[0], new RiddleNode[0][]);
		Assert.assertEquals("SHould be the same", new RiddleNode[0].length, path.length);

		actual = RiddleSolver.solve(r00);
		Assert.assertEquals("Should be null", null, actual);

	}

}
