import static org.junit.Assert.*;
import org.junit.*;

public class KenKenPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_KenKen_checkIntegrity = "KenKen.checkIntegrity";
	protected static final String EX_NAME_KenKen_checkValidity = "KenKen.checkValidity";
	protected static final String EX_NAME_KenKen_solve = "KenKen.solve";
	protected static final String METHOD_NAME_KenKen_checkIntegrity = "KenKen.checkIntegrity";
	protected static final String METHOD_NAME_KenKen_checkValidity = "KenKen.checkValidity";
	protected static final String METHOD_NAME_KenKen_solve = "KenKen.solve";

	// ========== TEST-DATA ==========
	protected static final int[][][] kenkenWikipediaOriginal = { //
			{ { 11, '+' }, { 0, 0 }, { 1, 0 } }, //
			{ { 2, '/' }, { 0, 1 }, { 0, 2 } }, //
			{ { 20, '*' }, { 0, 3 }, { 1, 3 } }, //
			{ { 6, '*' }, { 0, 4 }, { 0, 5 }, { 1, 5 }, { 2, 5 } }, //
			{ { 3, '-' }, { 1, 1 }, { 1, 2 } }, //
			{ { 3, '/' }, { 1, 4 }, { 2, 4 } }, //
			{ { 240, '*' }, { 2, 0 }, { 2, 1 }, { 3, 0 }, { 3, 1 } }, //
			{ { 6, '*' }, { 2, 2 }, { 2, 3 } }, //
			{ { 6, '*' }, { 3, 2 }, { 4, 2 } }, //
			{ { 7, '+' }, { 3, 3 }, { 4, 3 }, { 4, 4 } }, //
			{ { 30, '*' }, { 3, 4 }, { 3, 5 } }, //
			{ { 6, '*' }, { 4, 0 }, { 4, 1 } }, //
			{ { 9, '+' }, { 4, 5 }, { 5, 5 } }, //
			{ { 8, '+' }, { 5, 0 }, { 5, 1 }, { 5, 2 } }, //
			{ { 2, '/' }, { 5, 3 }, { 5, 4 } } //
	};

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_checkIntegrity() {
		assertEquals("empty: " + METHOD_NAME_KenKen_checkIntegrity + " failed.", 1, KenKen.checkIntegrity(new int[0][1][2]));
		// ----------
		int[][][] testInput = deepCloneKenken(kenkenWikipediaOriginal);
		int actual = KenKen.checkIntegrity(testInput);
		assertArrayEquals("kenkenWikipediaOriginal: " + METHOD_NAME_KenKen_checkIntegrity + " MODIFIED THE INPUT KenKen!", kenkenWikipediaOriginal, testInput);
		assertEquals("kenkenWikipediaOriginal: " + METHOD_NAME_KenKen_checkIntegrity + " failed.", 0, actual);
	}

	@Test(timeout = 666)
	public void pubTest_checkValidity() {
		assertEquals("empty: " + METHOD_NAME_KenKen_checkValidity + " failed.", 1, KenKen.checkValidity(new int[0][1][2]));
		// ----------
		int[][][] testInput = deepCloneKenken(kenkenWikipediaOriginal);
		int actual = KenKen.checkValidity(testInput);
		assertArrayEquals("kenkenWikipediaOriginal: " + METHOD_NAME_KenKen_checkValidity + " MODIFIED THE INPUT KenKen!", kenkenWikipediaOriginal, testInput);
		assertEquals("kenkenWikipediaOriginal: " + METHOD_NAME_KenKen_checkValidity + " failed.", 0, actual);
	}

	@Test(timeout = 6666)
	public void pubTest_solve() {
		assertNull("empty: " + METHOD_NAME_KenKen_solve + " failed.", KenKen.solve(new int[0][1][2]));
		// ----------
		int[][][] testInput = deepCloneKenken(kenkenWikipediaOriginal);
		int[][] expectedSolution = { //
				{ 5, 6, 3, 4, 1, 2 }, //
				{ 6, 1, 4, 5, 2, 3 }, //
				{ 4, 5, 2, 3, 6, 1 }, //
				{ 3, 4, 1, 2, 5, 6 }, //
				{ 2, 3, 6, 1, 4, 5 }, //
				{ 1, 2, 5, 6, 3, 4 } //
		};
		int[][] studentSolution = KenKen.solve(testInput);
		assertArrayEquals("kenkenWikipediaOriginal: " + METHOD_NAME_KenKen_solve + " MODIFIED THE INPUT KenKen!", kenkenWikipediaOriginal, testInput);
		assertNotNull("kenkenWikipediaOriginal: has a solution, but " + METHOD_NAME_KenKen_solve + " didn't find it.", studentSolution);
		assertArrayEquals("kenkenWikipediaOriginal: solution returned by " + METHOD_NAME_KenKen_solve + " is wrong.", expectedSolution, studentSolution);
	}

	// ========== HELPER ==========
	protected static final int[][][] deepCloneKenken(int[][][] kenken) {
		int[][][] copy = null;
		if (kenken != null) {
			copy = new int[kenken.length][][];
			for (int partition = 0; partition < kenken.length; partition++) {
				if (kenken[partition] != null) {
					copy[partition] = new int[kenken[partition].length][];
					for (int subfield = 0; subfield < kenken[partition].length; subfield++) {
						if (kenken[partition][subfield] != null) {
							copy[partition][subfield] = new int[kenken[partition][subfield].length];
							for (int subsubfield = 0; subsubfield < kenken[partition][subfield].length; subsubfield++) {
								copy[partition][subfield][subsubfield] = kenken[partition][subfield][subsubfield];
							}
						}
					}
				}
			}
		}
		return copy;
	}
}