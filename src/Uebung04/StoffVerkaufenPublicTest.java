import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class StoffVerkaufenPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_naive = "StoffVerkaufen.verkaufenNaive";
	protected static final String EX_NAME_dp = "StoffVerkaufen.verkaufenDP";
	protected static final String CLASS_NAME_StoffVerkaufen = "StoffVerkaufen";
	protected static final String METH_NAME_StoffVerkaufen_verkaufenNaive = "verkaufenNaive";
	protected static final String METH_NAME_StoffVerkaufen_verkaufenDP = "verkaufenDP";
	protected static final String METH_NAME_StoffVerkaufen_verkaufenIt = "verkaufenIt";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);
	private static final long[] PREISLISTE = { 1, 5, 8, 9, 10, 17, 17, 20 };
	private static final int[][] XXX = { { 6, 17, 256 }, { 7, 18, 576 }, { 8, 22, 1280 }, { 9, 25, 2814 }, { 10, 27, 6134 }, { 11, 30, 13276 }, { 12, 34, 28560 }, { 13, 35, 61120 }, { 14, 39, 130208 }, { 15, 42, 276288 }, { 16, 44, 584192 } };
	private static final long[] A007422 = { 1, 6, 8, 10, 14, 15, 21, 22, 26, 27, 33, 34, 35, 38, 39, 46, 51, 55, 57, 58, 62, 65, 69, 74, 77, 82, 85, 86, 87, 91, 93, 94, 95, 106, 111, 115, 118, 119, 122, 123, 125, 129, 133, 134, 141, 142, 143, 145, 146, 155, 158, 159, 161, 166, 177, 178, 183, 185, 187 };

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__naive__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<StoffVerkaufen> clazz = StoffVerkaufen.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " soll keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, getDeclaredConstructors(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 2, getDeclaredMethods(clazz).length);
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__dp__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		pubTest__Intestines__naive__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 16666)
	public void pubTest__verkaufenNaive() {
		for (int[] xXx : XXX) {
			check_naive("PREISLISTE", xXx[0], PREISLISTE, xXx[1], xXx[2]);
		}
		// --------------------
		long[] preisePreisliste_0_3 = Arrays.copyOf(PREISLISTE, PREISLISTE.length);
		preisePreisliste_0_3[0] = 3;
		check_naive("Modifizierte PREISLISTE", PREISLISTE.length, preisePreisliste_0_3, 24, (PREISLISTE.length + 2) * (1 << (PREISLISTE.length - 1)));
		// --------------------
		long[] preisePreisliste_gekuerzt = Arrays.copyOf(PREISLISTE, PREISLISTE.length - 1 - RND.nextInt(1));
		check_naive("Verkuerzte PREISLISTE", PREISLISTE.length, preisePreisliste_gekuerzt, 22, preisePreisliste_gekuerzt.length == 7 ? 1278 : 1270);
		// --------------------
		long[] preiseA007422 = Arrays.copyOf(A007422, 16);
		check_naive("Anfang von A007422", preiseA007422.length, preiseA007422, 48, 589_824);
	}

	@Test(timeout = 666)
	public void pubTest__verkaufenDP() {
		for (int[] xXx : XXX) {
			check_dp("PREISLISTE", xXx[0], PREISLISTE, xXx[1]);
		}
		// --------------------
		long[] preisePreisliste_0_3 = Arrays.copyOf(PREISLISTE, PREISLISTE.length);
		preisePreisliste_0_3[0] = 3;
		check_dp("Modifizierte PREISLISTE", PREISLISTE.length, preisePreisliste_0_3, 24);
		// --------------------
		long[] preisePreisliste_gekuerzt = Arrays.copyOf(PREISLISTE, PREISLISTE.length - 1 - RND.nextInt(1));
		check_dp("Verkuerzte PREISLISTE", PREISLISTE.length, preisePreisliste_gekuerzt, 22);
		// --------------------
		long[] preiseA007422 = Arrays.copyOf(A007422, 16);
		check_dp("Anfang von A007422", preiseA007422.length, preiseA007422, 48);
		// --------------------
		for (int[] xXx : new int[][] { { -5, 170 }, { -4, 177 }, { -3, 178 }, { -2, 183 }, { -1, 185 }, { 1, 191 }, { 2, 195 }, { 3, 198 }, { 4, 201 }, { 5, 204 } }) {
			check_dp("Modifizierter Stoff, A007422", A007422.length + xXx[0], A007422, xXx[1]);
		}
	}

	// ========== HELPER ==========
	protected static final void check_naive(String message, int stoff, long[] preiseOrig, long expected, long expectedTotalCalls) {
		String preiseString = Arrays.toString(preiseOrig);
		preiseString = preiseString.length() > 25 ? preiseString.substring(0, 25) + "..." + preiseString.substring(preiseString.length() - 1, preiseString.length()) : preiseString;
		message += " {stoff = " + stoff + ", preise = " + preiseString + " (#preise=" + preiseOrig.length + ")}: ";
		BigBrother bb = new BigBrother();
		Polizei p = bb;
		long[] preise = Arrays.copyOf(preiseOrig, preiseOrig.length);
		long actual = StoffVerkaufen.verkaufenNaive(p, stoff, preise);
		assertArrayEquals(message + "VERAENDERE NICHT DIE UEBERGEBENEN FELDER!", preiseOrig, preise);
		assertEquals(message + "Das zurueckgegebene Ergebnis ist falsch.", expected, actual);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_StoffVerkaufen_verkaufenNaive + " ist falsch (evtl. Rekursion abgerollt?).", (stoff < 0 ? 0 : stoff) + 1, bb.maxRecDepth_naive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionsbreite von " + METH_NAME_StoffVerkaufen_verkaufenNaive + " ist falsch (evtl. Rekursion abgerollt?).", expectedTotalCalls, bb.totalCalls_naive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_StoffVerkaufen_verkaufenDP + " ist falsch.", 0, bb.maxRecDepth_DP);
	}

	protected static final void check_dp(String message, int stoff, long[] preiseOrig, long expected) {
		String preiseString = Arrays.toString(preiseOrig);
		preiseString = preiseString.length() > 25 ? preiseString.substring(0, 25) + "..." + preiseString.substring(preiseString.length() - 1, preiseString.length()) : preiseString;
		message += " {stoff = " + stoff + ", preise = " + preiseString + " (#preise=" + preiseOrig.length + ")}: ";
		BigBrother bb = new BigBrother();
		Polizei p = bb;
		long[] preise = Arrays.copyOf(preiseOrig, preiseOrig.length);
		long actual = StoffVerkaufen.verkaufenDP(p, stoff, preise);
		assertArrayEquals(message + "VERAENDERE NICHT DIE UEBERGEBENEN FELDER!", preiseOrig, preise);
		assertEquals(message + "Das zurueckgegebene Ergebnis ist falsch.", expected, actual);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_StoffVerkaufen_verkaufenNaive + " ist falsch.", 0, bb.maxRecDepth_naive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_StoffVerkaufen_verkaufenDP + " ist falsch (evtl. Rekursion abgerollt?).", 1, bb.maxRecDepth_DP);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionsbreite von " + METH_NAME_StoffVerkaufen_verkaufenDP + " ist falsch (evtl. Rekursion abgerollt?).", 1, bb.totalCalls_DP);
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements Polizei {
		protected long totalCalls_naive = 0, totalCalls_DP = 0;
		protected long maxRecDepth_naive = 0, maxRecDepth_DP = 0;

		@Override
		public void kontrolle() {
			long callsTo_naive = 0, callsTo_DP = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().startsWith(CLASS_NAME_StoffVerkaufen)) {
					if (ste.getMethodName().equals(METH_NAME_StoffVerkaufen_verkaufenNaive)) {
						callsTo_naive++;
						totalCalls_naive++;
					} else if (ste.getMethodName().equals(METH_NAME_StoffVerkaufen_verkaufenDP)) {
						callsTo_DP++;
						totalCalls_DP++;
					}
				}
			}
			maxRecDepth_naive = callsTo_naive > maxRecDepth_naive ? callsTo_naive : maxRecDepth_naive;
			maxRecDepth_DP = callsTo_DP > maxRecDepth_DP ? callsTo_DP : maxRecDepth_DP;
		}
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
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