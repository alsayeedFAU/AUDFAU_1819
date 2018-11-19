import static org.junit.Assert.*;
import org.junit.*;

public class VeggieWahnPublicTest {
	// ========== SYSTEM ==========

	// ========== TEST-DATA ==========
	private static final long[][] TEST_DATA = { { 2, 2, 2 }, { 3, 4, 12 }, { 98, 99, 9702 }, { 15, 26, 180898060382208000L }, { 106, 111, 110582583588104364L } };

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_0() {
		test(0);
	}

	@Test(timeout = 666)
	public void pubTest_1() {
		test(1);
	}

	@Test(timeout = 666)
	public void pubTest_2() {
		test(2);
	}

	@Test(timeout = 666)
	public void pubTest_3() {
		test(3);
	}

	@Test(timeout = 666)
	public void pubTest_4() {
		test(4);
	}

	@Test(timeout = 1000)
	public void pubTest_Zeitmessung_vs_DynProg__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int i = 0; i <= 1000; i++) {
			test(4);
		}
	}

	// ========== HELPER ==========
	protected static final void test(int n) {
		test((int) TEST_DATA[n][0], (int) TEST_DATA[n][1], TEST_DATA[n][2]);
	}

	protected static final void test(int v, int g, long expected) {
		long actual = VeggieWahn.essen(v, g);
		assertEquals("Test mit: " + v + " veganen Grundgerichten und " + g + " Gewuerzen.", expected, actual);
	}
}