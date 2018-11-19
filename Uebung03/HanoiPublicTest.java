import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class HanoiPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Hanoi = "Hanoi";
	protected static final String CLASS_NAME_Hanoi = "Hanoi";
	protected static final String METHOD_NAME_Hanoi_solve = "solve";

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(666_0815_471l);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Intestines();
	}

	@Test(timeout = 16666)
	public void pubTest__Hanoi_solve__n_is_3__and__n_is_5__and__n_is_random_value() {
		for (int pass = 0; pass < 42; pass++) {
			check_Hanoi(3, "A", "B", "C", "A->C A->B C->B A->C B->A B->C A->C");
			check_Hanoi(5, "A", "B", "C", "A->C A->B C->B A->C B->A B->C A->C A->B C->B C->A B->A C->B A->C A->B C->B A->C B->A B->C A->C B->A C->B C->A B->A B->C A->C A->B C->B A->C B->A B->C A->C");
			// -----
			String[] s = generateRandomStrings();
			check_Hanoi(3, s[0], s[1], s[2], "A->C A->B C->B A->C B->A B->C A->C".replace("A", "0").replace("B", "1").replace("C", "2").replace("0", s[0]).replace("1", s[1]).replace("2", s[2]));
			check_Hanoi(5, s[0], s[1], s[2], "A->C A->B C->B A->C B->A B->C A->C A->B C->B C->A B->A C->B A->C A->B C->B A->C B->A B->C A->C B->A C->B C->A B->A B->C A->C A->B C->B A->C B->A B->C A->C".replace("A", "0").replace("B", "1").replace("C", "2").replace("0", s[0]).replace("1", s[1]).replace("2", s[2]));
			// -----
			check_Hanoi(2 + RND.nextInt(10), s[0], s[1], s[2], null);
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements HanoiRecCheck {
		private long maxRecDepth = 0;
		private long recTotal = 0;

		@Override
		public void checkRecHanoi() {
			long recDepth = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(CLASS_NAME_Hanoi)) {
					if (ste.getMethodName().equals(METHOD_NAME_Hanoi_solve)) {
						recDepth++;
					}
				}
			}
			if (maxRecDepth < recDepth) {
				maxRecDepth = recDepth;
			}
			recTotal++;
		}
	}

	// ========== HELPER ==========
	protected static final void check_Hanoi(int n, String start, String spare, String target, String expectedSolution) {
		BigBrother bb = new BigBrother();
		HanoiRecCheck hrc = bb; // just to please the Eclipse "type inference" of the automatic skeleton generation or auto-completion
		String call = CLASS_NAME_Hanoi + "." + METHOD_NAME_Hanoi_solve + "(" + n + ", " + start + ", " + spare + ", " + target + "," + bb.getClass().getInterfaces()[0] + ")";
		String actualSolution = Hanoi.solve(n, start, spare, target, hrc);
		assertEquals("Recursion depth is wrong for " + call, n <= 0 ? 1 : n + 1, bb.maxRecDepth);
		assertEquals("Total number of recursive calls is wrong (wrong recursion width?) for " + call, n <= 0 ? 1 : (1 << (n + 1)) - 1, bb.recTotal);
		if (expectedSolution != null) {
			System.out.println(actualSolution);
			System.out.println(expectedSolution);
			assertEquals("Return value is wrong for " + call, expectedSolution.replaceAll("\\s+", " ").trim(), actualSolution.replaceAll("\\s+", " ").trim());
		}
	}

	protected static final String[] generateRandomStrings() {
		String a = getRandomString();
		String b = getRandomString();
		b = b.equals(a) ? b + getRandomChar() : b;
		String c = getRandomString();
		c = c.equals(a) ? c + getRandomChar() : c;
		c = c.equals(b) ? c + getRandomChar() : c;
		c = c.equals(a) ? c + getRandomChar() : c;
		return new String[] { a, b, c };
	}

	private static final String getRandomString() {
		String s = "";
		int len = 3 + RND.nextInt(3);
		for (int i = 0; i < len; i++) {
			s += getRandomChar();
		}
		return s;
	}

	private static final char getRandomChar() {
		if (RND.nextBoolean()) {
			return (char) ('A' + RND.nextInt('Z' - 'A' + 1));
		} else {
			return (char) ('a' + RND.nextInt('z' - 'a' + 1));
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

	private void check_Intestines() {
		Class<?> clazz = Hanoi.class;
		assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals("Du sollst keine Schnittstelle implementieren!", 0, clazz.getInterfaces().length);
		assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals("Du sollst keine Attribute haben!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals("Du sollst genau einen (default) Konstruktur haben!", 1, constructors.length);
		assertEquals("Du sollst genau einen (default) Konstruktur haben!", 0, constructors[0].getParameterTypes().length);
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals("Du sollst genau die eine erwartete Methode haben!", 1, methods.length);
	}
}