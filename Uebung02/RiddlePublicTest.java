import static org.junit.Assert.*;
import org.junit.*;

public class RiddlePublicTest {
	// ========== SYSTEM ==========

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__implies() {
		assertTrue(Riddle.implies(true, true));
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest__a0() {
		assertFalse(Riddle.a0(false, true, true));
	}

	@Test(timeout = 666)
	public void pubTest__a1() {
		assertFalse(Riddle.a1(true, false, true));
	}

	@Test(timeout = 666)
	public void pubTest__a2() {
		assertTrue(Riddle.a2(false, false, true));
	}

	@Test(timeout = 666)
	public void pubTest__a3() {
		assertTrue(Riddle.a3(false, false, true));
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest__eval() throws Exception {
		assertTrue(Riddle.eval(false, true, false));
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest__checkRiddle() throws Exception {
		int actual = Riddle.checkRiddle();
		assertEquals(1, actual);
	}
}