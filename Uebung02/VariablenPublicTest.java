import static org.junit.Assert.*;
import org.junit.*;

public class VariablenPublicTest {
	// ========== SYSTEM ==========

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__FOO_BAR() {
		assertNotEquals(0, Variablen.FOO_BAR);
	}

	@Test(timeout = 666)
	public void pubTest__Months__10_OCTOBER() {
		assertNotNull(Variablen.Months.OCTOBER);
	}

	@Test(timeout = 666)
	public void pubTest__someCharacters() {
		assertEquals('D', Variablen.someCharacters()[3]);
	}

	@Test(timeout = 666)
	public void pubTest__someMoreCharacters() {
		assertEquals(1, Variablen.someMoreCharacters()[0][1]);
		assertEquals(66, Variablen.someMoreCharacters()[1][1]);
		assertEquals(0x31, Variablen.someMoreCharacters()[2][1]);
		assertEquals(0142, Variablen.someMoreCharacters()[3][1]);
	}

	@Test(timeout = 666)
	public void pubTest__theCube() {
		assertEquals(321, Variablen.theCube()[2][1][0]);
	}
}