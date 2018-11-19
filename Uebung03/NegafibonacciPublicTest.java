import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.*;

public class NegafibonacciPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Negafibonacci = "Negafibonacci";
	protected static final String CLASS_NAME_Negafibonacci = "Negafibonacci";
	protected static final String METHOD_NAME_Negafibonacci_negaFibo = "negaFibo";

	// ========== TEST-DATA ==========
	private static final int[][] FIB = new int[3][666];
	static {
		FIB[0][0] = FIB[1][0] = 0;
		FIB[0][1] = FIB[1][1] = 1;
		FIB[2][0] = FIB[2][1] = 1;
		for (int n = 2; n < FIB[0].length; n++) {
			FIB[0][n] = FIB[0][n - 1] + FIB[0][n - 2];
			FIB[1][n] = n % 2 == 0 ? -FIB[0][n] : FIB[0][n];
			FIB[2][n] = FIB[2][n - 1] + FIB[2][n - 2] + 1;
		}
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Intestines();
	}

	@Test(timeout = 6666)
	public void pubTest_Negafibonacci_negaFibo_pos() {
		for (int n = 0; n < 20; n++) {
			check_Negafibonacci(n);
		}
	}

	@Test(timeout = 6666)
	public void pubTest_Negafibonacci_negaFibo_neg() {
		for (int n = 0; n < 20; n++) {
			check_Negafibonacci(-n);
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements NegaFiboRecCheck {
		private long maxRecDepth = 0;
		private long recTotal = 0;

		@Override
		public void nfrc() {
			long recDepth = 0;
			for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(CLASS_NAME_Negafibonacci)) {
					if (ste.getMethodName().equals(METHOD_NAME_Negafibonacci_negaFibo)) {
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
	protected static final void check_Negafibonacci(int n) {
		BigBrother bb = new BigBrother();
		NegaFiboRecCheck nfrc = bb; // just to please the Eclipse "type inference" of the automatic skeleton generation or auto-completion
		String call = CLASS_NAME_Negafibonacci + "." + METHOD_NAME_Negafibonacci_negaFibo + "(" + n + ", " + bb.getClass().getInterfaces()[0] + ")";
		int actualValue = Negafibonacci.negaFibo(n, nfrc);
		assertEquals("Recursion depth is wrong for " + call, n == 0 ? 1 : n < 0 ? 1 - n : n, bb.maxRecDepth);
		assertEquals("Total number of recursive calls is wrong (wrong recursion width?) for " + call, (n == 0 || n == 1) ? 1 : n < 0 ? FIB[2][-n] + 1 : FIB[2][n], bb.recTotal);
		assertEquals("Return value is wrong for " + call, n < 0 ? FIB[1][-n] : FIB[0][n], actualValue);
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
		Class<?> clazz = Negafibonacci.class;
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