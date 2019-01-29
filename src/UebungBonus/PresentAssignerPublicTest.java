import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;




public class PresentAssignerPublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME_makeQuadratic      = "PresentAssigner.makeQuadratic";
	public static final String EX_NAME_initialAssignment  = "PresentAssigner.initialAssignment";
	public static final String EX_NAME_singleAuctionPhase = "PresentAssigner.singleAuctionPhase";
	public static final String EX_NAME_getAssignment      = "PresentAssigner.getAssignment";



	protected static class PresentAssignerTestStub extends PresentAssigner {
		boolean initialAssignmentCalled;
		boolean singleAuctionPhaseCalled;

		public PresentAssignerTestStub(final int[][] values) {
			super(values);
			this.assignment = new int[this.values.length];
		}

		public PresentAssignerTestStub(final int[][] values, final int[] assignment) {
			super(values);
			this.assignment = assignment;
		}

		public PresentAssignerTestStub(final int[][] values, final int[] assignment, final double[] prices) {
			this(values, assignment);
			final double[] thisPrices = this.prices;
			System.arraycopy(prices, 0, thisPrices, 0, prices.length);
		}

		public int[] getCurrentAssignment() {
			return this.assignment;
		}

		@Override
		public Queue<Integer> initialAssignment() {
			this.initialAssignmentCalled = true;
			return super.initialAssignment();
		}

		@Override
		public void singleAuctionPhase(final int person, final Queue<Integer> workQueue) {
			this.singleAuctionPhaseCalled = true;
			super.singleAuctionPhase(person, workQueue);
		}
	}



	private int[][] deepCopy(final int[][] array) {
		final int[][] result = new int[array.length][];
		for (int i = 0; i < array.length; ++i) {
			result[i] = Arrays.copyOf(array[i], array[i].length);
		}
		return result;
	}



	@Test(timeout = 1000)
	public void pubTest__makeQuadratic__alreadyQuadratic() {
		final int[][] input = { { 1, 2 }, { 3, 4 } };
		final int[][] output = PresentAssigner.makeQuadratic(deepCopy(input));
		assertArrayEquals(EX_NAME_makeQuadratic + " changes quadratic matrix",
				input,
				output);
	}



	@Test(timeout = 1000)
	public void pubTest__makeQuadratic__2x3() {
		final int[][] input = { { 1, 2, 3 }, { 4, 5, 6 } };
		final int[][] expected = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 0, 0 } };
		assertArrayEquals(EX_NAME_makeQuadratic + " does not convert 2x3 matrix to 3x3 matrix",
				expected,
				PresentAssigner.makeQuadratic(input));
	}



	@Test(timeout = 1000)
	public void pubTest__initialAssignment__100_001_010_result() {
		final PresentAssigner tester = new PresentAssignerTestStub(new int[][] {
				{ 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } });

		final Queue<Integer> result = tester.initialAssignment();

		assertTrue("Returned Queue does not contain 1", result.contains(1));
		assertTrue("Returned Queue does not contain 2", result.contains(2));
	}



	@Test(timeout = 1000)
	public void pubTest__initialAssignment__100_001_010_assignment() {
		final PresentAssignerTestStub tester = new PresentAssignerTestStub(new int[][] {
				{ 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } });

		tester.initialAssignment();

		assertArrayEquals("Assignments not set to diagonal entries",
				new int[] { 0, 1, 2 }, tester.getCurrentAssignment());
	}



	@Test(timeout = 1000)
	public void pubTest__singleAuctionPhase__swap() {
		final PresentAssignerTestStub tester = new PresentAssignerTestStub(new int[][] { { 1, 2 }, { 1, 1 } },
				new int[] { 0, 1 });

		final Queue<Integer> queue = new LinkedList<>();
		tester.singleAuctionPhase(0, queue);

		assertArrayEquals("Assigment should be swapped",
				new int[] { 1, 0 }, tester.getCurrentAssignment());
	}



	@Test(timeout = 1000)
	public void pubTest__getAssignment__example() {
		final PresentAssigner tester = new PresentAssigner(new int[][] {
			{ 4, 4, 1, 8 },
			{ 5, 4, 7, 0 },
			{ 7, 1, 7, 3 },
			{ 4, 6, 2, 6 }
		});

		final int[] result = tester.getAssignment();

		assertArrayEquals(new int[] { 3, 2, 0, 1 }, result);
	}



	@Test(timeout = 1000)
	public void pubTest__getAssignment__structure() {
		final PresentAssignerTestStub tester = new PresentAssignerTestStub(
				new int[][] { { 1, 2, 1 }, { 1, 1, 3 }, { 1, 2, 2 } },
				null);

		tester.getAssignment();

		assertTrue("No call to initialAssignment() in getAssignment()",
				tester.initialAssignmentCalled);
		assertTrue("No call to singleAuctionPhase() in getAssignment()",
				tester.singleAuctionPhaseCalled);
	}
}

