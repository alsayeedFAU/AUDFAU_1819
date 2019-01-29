import static org.junit.Assert.*;
import org.junit.*;

public class AlgebraPublicTest {

	@Test(timeout = 666)
	public void pubTest__primfaktorzerlegung__1() {
		long[][] expectedPFZ = { {}, {} };
		long[][] actualPFZ = Algebra.primfaktorzerlegung(1L);
		assertNotNull("Ergebnis darf nicht null sein.", actualPFZ);
		assertEquals("Ergebnis muss genau " + expectedPFZ.length + " \"Zeilen\" haben.", expectedPFZ.length, actualPFZ.length);
		for (int i = 0; i < expectedPFZ.length; i++) {
			assertNotNull("Ergebnis darf nicht null enthalten.", actualPFZ[i]);
			assertEquals("Ergebnis muss in Zeile " + i + " genau " + expectedPFZ[i].length + " \"Spalten\" haben.", expectedPFZ[i].length, actualPFZ[i].length);
		}
		for (int i = 0; i < expectedPFZ[0].length; i++) {
			assertEquals("Primzahl an Stelle " + i + " ist falsch.", expectedPFZ[0][i], actualPFZ[0][i]);
			assertEquals("Potenz an Stelle " + i + " ist falsch.", expectedPFZ[1][i], actualPFZ[1][i]);
		}
	}

	@Test(timeout = 666)
	public void pubTest__primfaktorzerlegung__24215358167__eq__7p2_x_11p3_x_13p5() {
		long[][] expectedPFZ = { { 7, 11, 13 }, { 2, 3, 5 } };
		long[][] actualPFZ = Algebra.primfaktorzerlegung(7L * 7 * 11 * 11 * 11 * 13 * 13 * 13 * 13 * 13);
		assertNotNull("Ergebnis darf nicht null sein.", actualPFZ);
		assertEquals("Ergebnis muss genau " + expectedPFZ.length + " \"Zeilen\" haben.", expectedPFZ.length, actualPFZ.length);
		for (int i = 0; i < expectedPFZ.length; i++) {
			assertNotNull("Ergebnis darf nicht null enthalten.", actualPFZ[i]);
			assertEquals("Ergebnis muss in Zeile " + i + " genau " + expectedPFZ[i].length + " \"Spalten\" haben.", expectedPFZ[i].length, actualPFZ[i].length);
		}
		for (int i = 0; i < expectedPFZ[0].length; i++) {
			assertEquals("Primzahl an Stelle " + i + " ist falsch.", expectedPFZ[0][i], actualPFZ[0][i]);
			assertEquals("Potenz an Stelle " + i + " ist falsch.", expectedPFZ[1][i], actualPFZ[1][i]);
		}
	}

	// ================================================================================

	@Test(timeout = 666)
	public void pubTest__ggT__3528_3780() {
		// siehe https://de.wikipedia.org/wiki/Gr%C3%B6%C3%9Fter_gemeinsamer_Teiler#Berechnung_.C3.BCber_die_Primfaktorzerlegung
		long[][] aPFZ = { { 2, 3, 7 }, { 3, 2, 2 } };
		long[][] bPFZ = { { 2, 3, 5, 7 }, { 2, 3, 1, 1 } };
		long expected = 252L;
		long actual = Algebra.ggT(aPFZ, bPFZ);
		assertEquals("ggT(" + java.util.Arrays.deepToString(aPFZ) + ", " + java.util.Arrays.deepToString(bPFZ) + ") ist falsch.", expected, actual);
	}

	// ================================================================================

	@Test(timeout = 666)
	public void pubTest__kgV__6_9() {
		long expected = 18L;
		long actual = Algebra.kgV(6, 9);
		assertEquals("kgV(6, 9) ist falsch.", expected, actual);
	}
}