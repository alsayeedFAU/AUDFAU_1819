import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class KryptogrammPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_extrahiereZeichen = "Kryptogramm.extrahiereZeichen";
	protected static final String EX_NAME_werteAus = "Kryptogramm.werteAus";
	protected static final String EX_NAME_loese = "Kryptogramm.loese";
	protected static final String CLASS_NAME_Kryptogramm = "Kryptogramm";
	protected static final String METH_NAME_Kryptogramm_extrahiereZeichen = "extrahiereZeichen";
	protected static final String METH_NAME_Kryptogramm_werteAus = "werteAus";
	protected static final String METH_NAME_Kryptogramm_loese = "loese";
	// --------------------

	// ========== TEST-DATA ==========
	private static final String[] BEISPIEL1 = { "Send", "More", "Money" };
	private static final String[] BEISPIEL2 = { "ZWEI", "VIER", "SECHS" };
	private static final String[] BEISPIEL3 = { "Weib", "Wein", "Liebe" };
	private static final String[] BEISPIEL4 = { "Base", "Ball", "Games" };
	private static final String[][] BEISPIELE = { BEISPIEL1, BEISPIEL2, BEISPIEL3, BEISPIEL4 };
	private static final char[][] BEISPIEL1LOESUNG = { { 'S', 9 }, { 'e', 5 }, { 'n', 6 }, { 'd', 7 }, { 'M', 1 }, { 'o', 0 }, { 'r', 8 }, { 'y', 2 } };
	private static final char[][] BEISPIEL2LOESUNG = { { 'Z', 8 }, { 'W', 6 }, { 'E', 2 }, { 'I', 4 }, { 'V', 3 }, { 'R', 7 }, { 'S', 1 }, { 'C', 0 }, { 'H', 5 } };
	private static final char[][] BEISPIEL3LOESUNG = { { 'W', 8 }, { 'e', 9 }, { 'i', 7 }, { 'b', 4 }, { 'n', 5 }, { 'L', 1 } };
	private static final char[][] BEISPIEL4LOESUNG = { { 'B', 7 }, { 'a', 4 }, { 's', 8 }, { 'e', 3 }, { 'l', 5 }, { 'G', 1 }, { 'm', 9 } };
	private static final char[][][] BEISPIELLOESUNGEN = { BEISPIEL1LOESUNG, BEISPIEL2LOESUNG, BEISPIEL3LOESUNG, BEISPIEL4LOESUNG };
	private static final long[] BEISPIEL1RECHNUNG = { 9567, 1085, 10652 };
	private static final long[] BEISPIEL2RECHNUNG = { 8624, 3427, 12051 };
	private static final long[] BEISPIEL3RECHNUNG = { 8974, 8975, 17949 };
	private static final long[] BEISPIEL4RECHNUNG = { 7483, 7455, 14938 };
	private static final long[][] BEISPIELRECHNUNGEN = { BEISPIEL1RECHNUNG, BEISPIEL2RECHNUNG, BEISPIEL3RECHNUNG, BEISPIEL4RECHNUNG };
	private static final Random RND = new Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_extrahiereZeichen() {
		for (String[] beispiel : BEISPIELE) {
			KryptogrammPublicTest.check_extrahiereZeichen(beispiel);
		}
		for (int i = 0; i < 42; i++) {
			KryptogrammPublicTest.check_extrahiereZeichen(KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm().kryptogramm);
		}
	}

	@Test(timeout = 666)
	public void pubTest_werteAus() {
		for (int i = 0; i < BEISPIELE.length; i++) {
			for (int j = 0; j < BEISPIELE[i].length; j++) {
				KryptogrammPublicTest.check_werteAus(BEISPIELE[i][j], BEISPIELLOESUNGEN[i], BEISPIELRECHNUNGEN[i][j]);
			}
		}
		for (int i = 0; i < 5; i++) {
			KryptogrammPublicTest.ZufaelligesKryptogramm zk = KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm();
			for (int j = 0; j < zk.kryptogramm.length; j++) {
				KryptogrammPublicTest.check_werteAus(zk.kryptogramm[j], zk.loesung, zk.rechnung[j]);
			}
		}
	}

	@Test(timeout = 16666)
	public void pubTest_loese() {
		for (String[] beispiel : BEISPIELE) {
			KryptogrammPublicTest.check_loese(beispiel, true);
		}
		for (int i = 0; i < 5; i++) {
			KryptogrammPublicTest.check_loese(KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm().kryptogramm, true);
		}
	}

	// ========== HELPER ==========
	protected static final class ZufaelligesKryptogramm {
		protected long[] rechnung;
		protected String[] kryptogramm;
		protected char[][] loesung;

		private ZufaelligesKryptogramm(long[] rechnung, String[] kryptogramm, char[][] loesung) {
			this.rechnung = rechnung;
			this.kryptogramm = kryptogramm;
			this.loesung = loesung;
		}
	}

	protected static final ZufaelligesKryptogramm generiere_zufaelliges_Kryptogramm() {
		long[] rechnung = new long[3 + RND.nextInt(8)];
		long summe = 0;
		for (int i = 0; i < rechnung.length - 1; i++) {
			rechnung[i] = 1 + RND.nextInt(999_999);
			summe += rechnung[i];
		}
		rechnung[rechnung.length - 1] = summe;
		ArrayList<Character> chars = new ArrayList<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			chars.add(c);
		}
		for (char c = 'a'; c <= 'z'; c++) {
			chars.add(c);
		}
		Collections.shuffle(chars);
		String[] kryptogramm = new String[rechnung.length];
		HashMap<Character, Character> loesungHM = new HashMap<>();
		for (int i = 0; i < rechnung.length; i++) {
			long operand = rechnung[i];
			kryptogramm[i] = "";
			while (operand > 0) {
				loesungHM.put(chars.get((int) (operand % 10)), (char) (operand % 10));
				kryptogramm[i] = chars.get((int) (operand % 10)) + kryptogramm[i];
				operand = operand / 10;
			}
		}
		char[][] loesung = new char[loesungHM.size()][2];
		int pos = 0;
		for (char c : loesungHM.keySet()) {
			loesung[pos][0] = c;
			loesung[pos++][1] = loesungHM.get(c);
		}
		return new ZufaelligesKryptogramm(rechnung, kryptogramm, loesung);
	}

	protected static final void check_extrahiereZeichen(String[] operandenOriginal) {
		String msg = METH_NAME_Kryptogramm_extrahiereZeichen + "(" + Arrays.toString(operandenOriginal) + "): ";
		String[] operanden = Arrays.copyOf(operandenOriginal, operandenOriginal.length);
		char[] actual = Kryptogramm.extrahiereZeichen(operanden);
		assertArrayEquals("BITTE NICHT DIE EINGABEFELDER MODIFIZIEREN!", operandenOriginal, operanden);
		assertNotNull("Rueckgabe darf nicht null sein!", actual);
		HashSet<Character> expectedHS = new HashSet<>();
		for (String s : operandenOriginal) {
			for (char c : s.toCharArray()) {
				expectedHS.add(c);
			}
		}
		HashSet<Character> actualHS = new HashSet<>();
		for (char c : actual) {
			assertTrue(msg + "Ich habe das Zeichen <" + c + "> mehrfach in der Rueckgabe gefunden: " + Arrays.toString(actual), actualHS.add(c));
		}
		for (char c : expectedHS) {
			assertTrue(msg + "Ich vermisse das Zeichen <" + c + "> in der Rueckgabe: " + Arrays.toString(actual), actualHS.contains(c));
		}
		for (char c : actualHS) {
			assertTrue(msg + "Ich habe das Zeichen <" + c + "> nicht in der Rueckgabe erwartet: " + Arrays.toString(actual), expectedHS.contains(c));
		}
	}

	protected static final void check_werteAus(String operand, char[][] zuordnungOriginal, long expected) {
		String msg = METH_NAME_Kryptogramm_werteAus + "(\"" + operand + "\", " + zuordnungAlsString(zuordnungOriginal) + "): ";
		char[][] zuordnung = new char[zuordnungOriginal.length][];
		for (int i = 0; i < zuordnungOriginal.length; i++) {
			zuordnung[i] = Arrays.copyOf(zuordnungOriginal[i], zuordnungOriginal[i].length);
		}
		long actual = Kryptogramm.werteAus(operand, zuordnung);
		assertArrayEquals("BITTE NICHT DIE EINGABEFELDER MODIFIZIEREN!", zuordnungOriginal, zuordnung);
		assertEquals(msg + "Ergebnis ist falsch.", expected, actual);
	}

	protected static final void check_loese(String[] operandenOriginal, boolean hatLoesung) {
		String msg = METH_NAME_Kryptogramm_loese + "(" + Arrays.toString(operandenOriginal) + "): ";
		String[] operanden = Arrays.copyOf(operandenOriginal, operandenOriginal.length);
		char[][] actual = Kryptogramm.loese(operanden);
		assertArrayEquals("BITTE NICHT DIE EINGABEFELDER MODIFIZIEREN!", operandenOriginal, operanden);
		if (hatLoesung) {
			assertNotNull("Rueckgabe darf nicht null sein!", actual);
			HashSet<Character> expectedHS = new HashSet<>();
			for (String s : operandenOriginal) {
				for (char c : s.toCharArray()) {
					expectedHS.add(c);
				}
			}
			HashSet<Character> actualHS = new HashSet<>(), actualValuesHS = new HashSet<>();
			for (char[] cv : actual) {
				assertNotNull("Rueckgabe darf nicht null enthalten!", cv);
				assertEquals("Rueckgabe muss WERTEPAARE enthalten!", 2, cv.length);
				assertTrue(msg + "Ich habe das Zeichen <" + cv[0] + "> mehrfach in der Rueckgabe gefunden: " + zuordnungAlsString(actual), actualHS.add(cv[0]));
				assertTrue(msg + "Ich habe <" + (int) cv[1] + "> (?) statt einer Ziffer (0-9) in der Rueckgabe gefunden: " + zuordnungAlsString(actual), 0 <= cv[1] && cv[1] <= 9);
				assertTrue(msg + "Ich habe die Ziffer <" + (int) cv[1] + "> mehrfach in der Rueckgabe gefunden: " + zuordnungAlsString(actual), actualValuesHS.add(cv[1]));
			}
			for (char c : expectedHS) {
				assertTrue(msg + "Ich vermisse das Zeichen <" + c + "> in der Rueckgabe: " + zuordnungAlsString(actual), actualHS.contains(c));
			}
			for (char c : actualHS) {
				assertTrue(msg + "Ich habe das Zeichen <" + c + "> nicht in der Rueckgabe erwartet: " + zuordnungAlsString(actual), expectedHS.contains(c));
			}
			long[] rechnung = new long[operandenOriginal.length];
			for (int i = 0; i < operandenOriginal.length; i++) {
				String op = operandenOriginal[i];
				for (char[] cv : actual) {
					op = op.replaceAll("" + cv[0], "" + (int) cv[1]);
				}
				try {
					rechnung[i] = Long.parseLong(op);
				} catch (Throwable t) {
					fail("Die zurueckgebene Loesung ist unbrauchbar.");
				}
			}
			long summe = 0;
			for (int i = 0; i < rechnung.length - 1; i++) {
				summe += rechnung[i];
			}
			assertEquals(msg + "Die Rechnung geht nicht auf.", rechnung[rechnung.length - 1], summe);
		} else {
			assertNull("Rueckgabe muss null sein!", actual);
		}
	}

	private static String zuordnungAlsString(char[][] zuordnungOriginal) {
		String res = "[";
		for (char[] cv : zuordnungOriginal) {
			res += "(" + cv[0] + "=" + (int) cv[1] + ")";
		}
		return res + "]";
	}
}