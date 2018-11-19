import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.*;

public class TribonacciPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_naive = "Tribonacci.delannoyNaive";
	protected static final String EX_NAME_mem = "Tribonacci.delannoyMem";
	protected static final String EX_NAME_dp = "Tribonacci.delannoyDP";
	protected static final String CLASS_NAME_Tribonacci = "Tribonacci";
	protected static final String METH_NAME_Tribonacci_delannoyNaive = "delannoyNaive";
	protected static final String METH_NAME_Tribonacci_delannoyMem = "delannoyMem";
	protected static final String METH_NAME_Tribonacci_delannoyDP = "delannoyDP";
	// --------------------

	// ========== TEST-DATA ==========
	private static final int[][] D = { { 4, 5, 681 }, { 6, 4, 1289 }, { 16, 8, 39490049 }, { 8, 16, 39490049 } };

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__naive__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<Tribonacci> clazz = Tribonacci.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " soll keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, getDeclaredConstructors(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 3, getDeclaredMethods(clazz).length);
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__mem__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		pubTest__Intestines__naive__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__dp__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		pubTest__Intestines__naive__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 6666)
	public void pubTest_delannoyNaive() {
		for (int n = 0; n <= 3; n++) {
			for (int k = 0; k <= 16; k++) {
				check_delannoyNaive(n, k, expected_small_delannoy(n, k));
			}
		}
		for (int[] d : D) {
			if (d[0] < 15 && d[1] < 15) {
				check_delannoyNaive(d[0], d[1], d[2]);
			}
		}
	}

	@Test(timeout = 6666)
	public void pubTest_delannoyMem() {
		for (int n = 0; n <= 3; n++) {
			for (int k = 0; k <= 64; k++) {
				check_delannoyMem(n, k, expected_small_delannoy(n, k));
			}
		}
		for (int[] d : D) {
			check_delannoyMem(d[0], d[1], d[2]);
		}
	}

	@Test(timeout = 666)
	public void pubTest_delannoyDP() {
		for (int n = 0; n <= 3; n++) {
			for (int k = 0; k <= 512; k++) {
				check_delannoyDP(n, k, expected_small_delannoy(n, k));
			}
		}
		for (int[] d : D) {
			check_delannoyDP(d[0], d[1], d[2]);
		}
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements TriboCheck {
		protected long totalCallsNaive = 0, totalCallsMem = 0, totalCallsDP = 0;
		protected long maxRecDepthNaive = 0, maxRecDepthMem = 0, maxRecDepthDP = 0;

		@Override
		public void checkTribo() {
			long callsToNaive = 0, callsToMem = 0, callsToDP = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().startsWith(CLASS_NAME_Tribonacci)) {
					if (ste.getMethodName().equals(METH_NAME_Tribonacci_delannoyNaive)) {
						callsToNaive++;
						totalCallsNaive++;
					} else if (ste.getMethodName().equals(METH_NAME_Tribonacci_delannoyMem)) {
						callsToMem++;
						totalCallsMem++;
					} else if (ste.getMethodName().equals(METH_NAME_Tribonacci_delannoyDP)) {
						callsToDP++;
						totalCallsDP++;
					}
				}
			}
			maxRecDepthNaive = callsToNaive > maxRecDepthNaive ? callsToNaive : maxRecDepthNaive;
			maxRecDepthMem = callsToMem > maxRecDepthMem ? callsToMem : maxRecDepthMem;
			maxRecDepthDP = callsToDP > maxRecDepthDP ? callsToDP : maxRecDepthDP;
		}
	}

	// ========== HELPER ==========
	private static final long expected_small_delannoy(int n, int k) {
		return n == 0 ? 1 : n == 1 ? 2 * k + 1 : n == 2 ? 2 * k * (k + 1) + 1 : n == 3 ? (2 * k + 1) * (2 * k * k + 2 * k + 3) / 3 : n == 3 ? (2 * k * k * k * k + 4 * k * k * k + 10 * k * k + 8 * k + 3) / 3 : 1;
	}

	protected static final void check_delannoyNaive(int n, int k, long expected) {
		BigBrother bb = new BigBrother();
		TriboCheck tc = bb;
		long actual = Tribonacci.delannoyNaive(tc, n, k);
		String message = METH_NAME_Tribonacci_delannoyNaive + "(" + n + "," + k + "):";
		assertEquals(message + "Ergebnis ist falsch.", expected, actual);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyNaive + " ist falsch (evtl. Rekursion abgerollt?).", (n == 0 || k == 0) ? 1 : n + k, bb.maxRecDepthNaive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionsbreite von " + METH_NAME_Tribonacci_delannoyNaive + " ist falsch (evtl. Rekursion abgerollt?).", (n == 0 || k == 0) ? 1 : computeRecDepthNaive(1, n, k), bb.totalCallsNaive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyMem + " ist falsch.", 0, bb.totalCallsMem);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyDP + " ist falsch.", 0, bb.totalCallsDP);
	}

	protected static final void check_delannoyMem(int n, int k, long expected) {
		BigBrother bb = new BigBrother();
		TriboCheck tc = bb;
		long actual = Tribonacci.delannoyMem(tc, n, k, null);
		String message = METH_NAME_Tribonacci_delannoyMem + "(" + n + "," + k + "):";
		assertEquals(message + "Ergebnis ist falsch.", expected, actual);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyNaive + " ist falsch.", 0, bb.totalCallsNaive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyMem + " ist falsch (evtl. Rekursion abgerollt?).", (n == 0 || k == 0) ? 2 : 1 + n + k, bb.maxRecDepthMem);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionsbreite von " + METH_NAME_Tribonacci_delannoyMem + " ist falsch (evtl. Rekursion abgerollt?).", (n == 0 || k == 0) ? 3 : 1 + computeRecDepthDP(2, n, k, new java.util.HashSet<String>()), bb.totalCallsMem);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyDP + " ist falsch.", 0, bb.totalCallsDP);
	}

	protected static final void check_delannoyDP(int n, int k, long expected) {
		BigBrother bb = new BigBrother();
		TriboCheck tc = bb;
		long actual = Tribonacci.delannoyDP(tc, n, k);
		String message = METH_NAME_Tribonacci_delannoyDP + "(" + n + "," + k + "):";
		assertEquals(message + "Ergebnis ist falsch.", expected, actual);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyNaive + " ist falsch.", 0, bb.totalCallsNaive);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyMem + " ist falsch.", 0, bb.totalCallsMem);
		assertEquals(message + "Das Ergebnis ist zwar richtig, aber die Rekursionstiefe von " + METH_NAME_Tribonacci_delannoyDP + " ist falsch.", 1, bb.totalCallsDP);
	}

	protected static int computeRecDepthNaive(int r, int n, int k) {
		return (n == 0 || k == 0) ? r : r + computeRecDepthNaive(r + 1, n - 1, k) + computeRecDepthNaive(r + 1, n - 1, k - 1) + computeRecDepthNaive(r + 1, n, k - 1);
	}

	protected static int computeRecDepthDP(int r, int n, int k, java.util.HashSet<String> seen) {
		return r + (n == 0 || k == 0 || !seen.add("#" + n + "," + k) ? 0 : computeRecDepthDP(r + 1, n - 1, k, seen) + computeRecDepthDP(r + 1, n - 1, k - 1, seen) + computeRecDepthDP(r + 1, n, k - 1, seen));
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