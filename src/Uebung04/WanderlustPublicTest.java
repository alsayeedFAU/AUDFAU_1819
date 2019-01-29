import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class WanderlustPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Wanderlust = "Wanderlust";
	protected static final String CLASS_NAME_Wanderlust = "Wanderlust";
	protected static final String METH_NAME_Wanderlust_solve = "solve";
	protected static final String METH_NAME_Wanderlust_solveMem = "solveMem";
	protected static final String METH_NAME_Wanderlust_solveIt = "solveIt";
	// --------------------

	// ========== TEST-DATA ==========
	public static final int VALUES[] = { 3, 5, 10, 5, 13, 23 }; // Schoenheitswerte der Staedte (Stadt 0 hat Schoenheitswert 3)
	public static final int NEXT[][] = { // Nachbarstaedte
			{ 0, 3 }, // von Stadt 0 aus erreicht man die Staedte 0 (erneut) und 3
			{ 3, 5 }, // von Stadt 1 aus erreicht man die Staedte 3 und 5
			{ 0, 1, 2, 3 }, // ...
			{ 3, 4 }, //
			{ 2 }, //
			{ 4, 5 } //
	};
	final static int GOAL = 18; // Zielwert fuer die Schoenheit der gesamten Reiseroute - hier nur als Beispiel!
	final static int STARTCITY = 0; // Beginn der Reiseroute (erste zu besuchende Stadt) - hier nur als Beispiel!
	final static int[] DEFAULTPATH = new int[GOAL]; // Vorlage fuer Reiserouten - anfangs sind alle Eintraege mit -1 vorinitalisiert
	static {
		Arrays.fill(DEFAULTPATH, -1);
	}

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<Wanderlust> clazz = Wanderlust.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " soll keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		assertEquals(clazz + " soll genau einen Konstruktoren (den default cons) haben!", 1, getDeclaredConstructors(clazz).length);
		assertEquals(clazz + " soll genau drei Methoden haben!", 3, getDeclaredMethods(clazz).length);
	}

	// ========== PUBLIC TEST ==========
	// -------------------- solve --------------------
	@Test(timeout = 666)
	public void pubTest__solve() {
		BigBrother bb = new BigBrother();
		ReiseCheck rc = bb;
		int[] values = clone(VALUES);
		int[][] next = clone(NEXT);
		int res = Wanderlust.solve(rc, values, next, GOAL, STARTCITY, clone(DEFAULTPATH), 0);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", VALUES, values);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", NEXT, next);
		assertEquals("Zurueckgegebene Anzahl an Wegen ist falsch.", 2, res);
		assertEquals("Anzahl der gemeldeten Wege ist falsch.", 2, bb.paths.size());
		boolean foundFirst = false, foundSecond = false;
		for (int[] p : bb.paths) {
			if (Arrays.equals(p, new int[] { 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 })) {
				foundFirst = true;
			} else if (Arrays.equals(p, new int[] { 0, 3, 3, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 })) {
				foundSecond = true;
			} else {
				fail("Ein unerwarteter Weg wurde gemeldet: " + Arrays.toString(p));
			}
		}
		assertTrue("Mindestens ein Weg wurde nicht gemeldet.", foundFirst && foundSecond);
		assertEquals("Rekursionstiefe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solve + " ist falsch.", 6, bb.maxRecDepth_solve);
		assertTrue("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solve + " ist falsch.", bb.totalCalls_solve > 90);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveMem + " ist falsch.", 0, bb.totalCalls_solveMem);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveIt + " ist falsch.", 0, bb.totalCalls_solveIt);
	}

	// -------------------- solveMem --------------------
	@Test(timeout = 666)
	public void pubTest__solveMem() {
		BigBrother bb = new BigBrother();
		ReiseCheck rc = bb;
		int[][] mem = new int[VALUES.length][30], memBig = new int[VALUES.length][300];
		for (int i = 0; i < mem.length; i++) {
			Arrays.fill(mem[i], -1);
			Arrays.fill(memBig[i], -1);
		}
		int[] values = clone(VALUES);
		int[][] next = clone(NEXT);
		int res = Wanderlust.solveMem(rc, values, next, GOAL, STARTCITY, mem);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", VALUES, values);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", NEXT, next);
		assertEquals("Zurueckgegebene Anzahl an Wegen ist falsch.", 2, res);
		assertEquals("mem[3][10] enthaelt falschen Wert.", 1, mem[3][10]);
		assertEquals("mem[5][23] enthaelt falschen Wert.", -1, mem[5][23]);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solve + " ist falsch.", 0, bb.totalCalls_solve);
		assertEquals("Rekursionstiefe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveMem + " ist falsch.", 6, bb.maxRecDepth_solveMem);
		assertTrue("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveMem + " ist falsch.", bb.totalCalls_solveMem > 90);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveIt + " ist falsch.", 0, bb.totalCalls_solveIt);
		// ---------- Testen, ob Loesung langsam ist ----------
		Wanderlust.solveMem(rc, clone(VALUES), clone(NEXT), 200, STARTCITY, memBig);
	}

	// -------------------- solveIt --------------------
	@Test(timeout = 666)
	public void pubTest__solveIt() {
		BigBrother bb = new BigBrother();
		ReiseCheck rc = bb;
		int[] values = clone(VALUES);
		int[][] next = clone(NEXT);
		int res = Wanderlust.solveIt(rc, values, next, GOAL, STARTCITY);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", VALUES, values);
		assertArrayEquals("Eingabe wurde faelschlich veraendert!", NEXT, next);
		assertEquals("Zurueckgegebene Anzahl an Wegen ist falsch.", 2, res);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solve + " ist falsch.", 0, bb.totalCalls_solve);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveMem + " ist falsch.", 0, bb.totalCalls_solveMem);
		assertEquals("Rekursionstiefe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveIt + " ist falsch.", 1, bb.maxRecDepth_solveIt);
		assertEquals("Anzahl der Aufrufe von " + WanderlustPublicTest.METH_NAME_Wanderlust_solveIt + " ist falsch.", 1, bb.totalCalls_solveIt);
		// ---------- Testen, ob Loesung langsam ist ----------
		Wanderlust.solveIt(rc, clone(VALUES), clone(NEXT), 200, STARTCITY);
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements ReiseCheck {
		protected long totalCalls_solve = 0, totalCalls_solveMem = 0, totalCalls_solveIt = 0;
		protected long maxRecDepth_solve = 0, maxRecDepth_solveMem = 0, maxRecDepth_solveIt = 0;
		protected LinkedList<int[]> paths = new LinkedList<>();

		@Override
		public void check() {
			long callsTo_solve = 0, callsTo_solveMem = 0, callsTo_solveIt = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(CLASS_NAME_Wanderlust)) {
					if (ste.getMethodName().equals(METH_NAME_Wanderlust_solve)) {
						callsTo_solve++;
						totalCalls_solve++;
					} else if (ste.getMethodName().equals(METH_NAME_Wanderlust_solveMem)) {
						callsTo_solveMem++;
						totalCalls_solveMem++;
					} else if (ste.getMethodName().equals(METH_NAME_Wanderlust_solveIt)) {
						callsTo_solveIt++;
						totalCalls_solveIt++;
					}
				}
			}
			maxRecDepth_solve = callsTo_solve > maxRecDepth_solve ? callsTo_solve : maxRecDepth_solve;
			maxRecDepth_solveMem = callsTo_solveMem > maxRecDepth_solveMem ? callsTo_solveMem : maxRecDepth_solveMem;
			maxRecDepth_solveIt = callsTo_solveIt > maxRecDepth_solveIt ? callsTo_solveIt : maxRecDepth_solveIt;
		}

		@Override
		public void report(int[] path) {
			paths.add(Arrays.copyOf(path, path.length));
		}
	}

	// ========== HELPER ==========
	protected static final int[] clone(int[] in) {
		return in == null ? null : Arrays.copyOf(in, in.length);
	}

	protected static final int[][] clone(int[][] in) {
		if (in == null) {
			return null;
		}
		int[][] out = new int[in.length][];
		for (int i = 0; i < in.length; i++) {
			if (in[i] != null) {
				out[i] = Arrays.copyOf(in[i], in[i].length);
			}
		}
		return out;
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