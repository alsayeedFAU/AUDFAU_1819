import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class MergePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Merge = "Merge";
	protected static final String CLASS_NAME_Merge = "Merge";
	protected static final String METHOD_NAME_Merge_merge = "merge";

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(666_0815_471l);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Intestines();
	}

	@Test(timeout = 2666)
	public void pubTest__example_from_sheet_simple__and__random() {
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 1, new long[] { 4, 25, 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, -3, new long[] { 3, 4, 25, 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, -2, new long[] { 3, 4, 25, 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, -1, new long[] { 3, 4, 25, 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 0, new long[] { 3, 4, 25, 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 4, new long[] { 6 });
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 5, new long[] {});
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 6, new long[] {});
		check_Merge(new long[] { 3, 4, 5, 5, 6 }, 7, new long[] {});
		for (int pass = 0; pass < 42; pass++) {
			randomTest();
		}
	}

	@Test(timeout = 2666)
	public void pubTest__example_from_sheet_result_consecutive__and__random() {
		check_Merge(new long[] { 2, 2, 4, 6, 8 }, 0, new long[] { 4, 4, 6, 8 });
		for (int pass = 0; pass < 42; pass++) {
			randomTest();
		}
	}

	@Test(timeout = 2666)
	public void pubTest__example_from_sheet_input_several_consecutive__and__random() {
		check_Merge(new long[] { 5, 5, 5, 6 }, 0, new long[] { 125, 6 });
		for (int pass = 0; pass < 42; pass++) {
			randomTest();
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements Merger {
		private long maxRecDepth = 0;
		private long recTotal = 0;

		@Override
		public void merge() {
			long recDepth = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(CLASS_NAME_Merge)) {
					if (ste.getMethodName().equals(METHOD_NAME_Merge_merge)) {
						recDepth++;
					}
				}
			}
			if (maxRecDepth < recDepth) {
				maxRecDepth = recDepth;
			}
			recTotal++;
		}

		@Override
		public long[] append(long[] old, long newLast) {
			long[] result = Arrays.copyOf(old, old.length + 1);
			result[old.length] = newLast;
			return result;
		}

		@Override
		public long[] prepend(long newFirst, long[] old) {
			long[] result = new long[old.length + 1];
			System.arraycopy(old, 0, result, 1, old.length);
			result[0] = newFirst;
			return result;
		}
	}

	// ========== HELPER ==========
	protected static final void check_Merge(long[] ns, int i, long[] expectedSolution) {
		BigBrother bb = new BigBrother();
		Merger m = bb; // just to please the Eclipse "type inference" of the automatic skeleton generation or auto-completion
		String call = CLASS_NAME_Merge + "." + METHOD_NAME_Merge_merge + "(" + Arrays.toString(ns) + ", " + i + ", " + bb.getClass().getInterfaces()[0] + ")";
		int expectedCalls = i < 0 ? ns.length + 2 : i >= ns.length ? 1 : ns.length - i + 1;
		long[] actualSolution = Merge.merge(ns, i, m);
		assertEquals("Recursion depth is wrong (wrong base cases? illegally unrolled recursion?) for " + call, expectedCalls, bb.maxRecDepth);
		assertEquals("Total number of recursive calls is wrong (wrong recursion width? illegally unrolled recursion?) for " + call, expectedCalls, bb.recTotal);
		if (expectedSolution != null) {
			assertArrayEquals("Return value is wrong for " + call, expectedSolution, actualSolution);
		}
	}

	protected static final void randomTest() {
		long[] ns = new long[2 + RND.nextInt(128)];
		ns[0] = RND.nextInt(42) - 21;
		ArrayList<Long> expectedSolutionArrayList = new ArrayList<>();
		long last = ns[0];
		for (int i = 1; i < ns.length; i++) {
			if (RND.nextBoolean()) { // same again
				ns[i] = ns[i - 1];
				last *= ns[i];
			} else { // less or greater
				expectedSolutionArrayList.add(last);
				ns[i] = RND.nextBoolean() ? ns[i - 1] - 1 - RND.nextInt(42) : ns[i - 1] + 1 + RND.nextInt(42);
				last = ns[i];
			}
			if (i == ns.length - 1) {
				expectedSolutionArrayList.add(last);
			}
		}
		System.out.println(Arrays.toString(ns));
		long[] expectedSolution = new long[expectedSolutionArrayList.size()];
		for (int i = 0; i < expectedSolution.length; i++) {
			expectedSolution[i] = expectedSolutionArrayList.get(i);
		}
		//check_Merge(ns, -RND.nextInt(42), expectedSolution);
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
		Class<?> clazz = Merge.class;
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