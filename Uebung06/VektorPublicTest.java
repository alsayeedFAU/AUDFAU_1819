import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class VektorPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Vektor = "Vektor";

	// ========== TEST-DATA ==========
	protected static final double EPS = 1e-5;
	protected static final Random RND = new Random();

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		String WARNING = "========================================\n" //
				+ "ACHTUNG: Der Name des Parameters konnte nicht identifiziert werden oder ist falsch.\n" //
				+ "Rufen Sie javac mit dem zusaetzlichen Parameter \"-parameters\" auf bzw. konfigurieren Sie Eclipse wie folgt:\n" //
				+ "Menue Project => Properties => Java Compiler => Haken bei \"[X] Store information about method parameters (usable via reflection)\".\n" //
				+ "Hinweis: Der geheime Testfall wird das EXAKT pruefen und ggf. Punkte abziehen.\n" //
				+ "========================================";
		Class<Vektor> clazz = Vektor.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 1, fields.length);
		assertTrue(clazz + " soll genau die vorgegebenen Attribute haben!", Modifier.isProtected(fields[0].getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Attribute haben!", !Modifier.isStatic(fields[0].getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Attribute haben!", !Modifier.isFinal(fields[0].getModifiers()));
		assertEquals(clazz + " soll genau die vorgegebenen Attribute haben!", "komponenten", fields[0].getName());
		assertTrue(clazz + " soll genau die vorgegebenen Attribute haben!", fields[0].getType().isArray());
		assertSame(clazz + " soll genau die vorgegebenen Attribute haben!", double.class, fields[0].getType().getComponentType());
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 2, cons.length);
		for (Constructor<?> c : cons) {
			if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == int.class) {
				if (!c.getParameters()[0].getName().equals("dimension")) {
					System.err.println(c.toString() + ": " + WARNING);
				}
			} else if (c.getParameterCount() == 1 && c.getParameterTypes()[0].isArray() && c.getParameterTypes()[0].getComponentType() == double.class) {
				if (!c.getParameters()[0].getName().equals("komponenten")) {
					System.err.println(c + ": " + WARNING);
				}
			} else {
				fail(clazz + " soll genau die vorgegebenen Konstruktoren (ggf. inkl. default cons) haben! Falsch erscheint mir: " + c.toString());
			}
		}
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 10, meths.length);
		HashSet<String> expectedMethods = new HashSet<>();
		expectedMethods.addAll(Arrays.asList("dimension", "komponente", "skalarMultiplikation", "skalarProdukt", "addition", "norm", "normiere"));
		for (Method m : meths) {
			if (m.getName().equals("initialisiere") && m.getParameterCount() == 1 && m.getParameterTypes()[0].isArray() && m.getParameterTypes()[0].getComponentType() == double.class) {
				if (!m.getParameters()[0].getName().equals("komponenten")) {
					System.err.println(m.toString() + ": " + WARNING);
				}
			} else if (expectedMethods.contains(m.getName())) {
				// so weit, so gut...
			} else {
				fail(clazz + " soll genau die vorgegebenen Methoden haben! Falsch erscheint mir: " + m.toString());
			}
		}
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__ctor__dimension() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = new double[1 + RND.nextInt(666)], k2 = new double[1 + RND.nextInt(666)];
			Vektor v1 = new Vektor(k1.length), v2 = new Vektor(k2.length);
			assertArrayEquals(k1, v1.komponenten, EPS);
			assertArrayEquals(k2, v2.komponenten, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__ctor__komponenten() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector();
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__dimension() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = new double[1 + RND.nextInt(666)], k2 = new double[1 + RND.nextInt(666)];
			Vektor v1 = new Vektor(k1.length), v2 = new Vektor(k2.length);
			int act1 = v1.dimension(), act2 = v2.dimension();
			assertEquals(k1.length, act1);
			assertEquals(k2.length, act2);
		}
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector();
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			int act1 = v1.dimension(), act2 = v2.dimension();
			assertEquals(k1c.length, act1);
			assertEquals(k2c.length, act2);
		}
	}

	@Test(timeout = 666)
	public void pubTest__komponente() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = new double[1 + RND.nextInt(666)], k2 = new double[1 + RND.nextInt(666)];
			Vektor v1 = new Vektor(k1.length), v2 = new Vektor(k2.length);
			for (int subPass = 0; subPass < 10; subPass++) {
				int i1 = RND.nextInt(k1.length), i2 = RND.nextInt(k2.length);
				assertEquals(0.0d, v1.komponente(i1), EPS);
				assertEquals(0.0d, v2.komponente(i2), EPS);
			}
		}
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector();
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			for (int subPass = 0; subPass < 10; subPass++) {
				int i1 = RND.nextInt(k1c.length), i2 = RND.nextInt(k2c.length);
				assertEquals(k1c[i1], v1.komponente(i1), EPS);
				assertEquals(k2c[i2], v2.komponente(i2), EPS);
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__initialisiere() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector();
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1.length), v2 = new Vektor(k2.length);
			v1.initialisiere(k1);
			v2.initialisiere(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__skalarMultiplikation() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector();
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			double m1 = 4711 * RND.nextDouble() - 666, m2 = 4711 * RND.nextDouble() - 666;
			v1.skalarMultiplikation(m1);
			v2.skalarMultiplikation(m2);
			for (int i = 0; i < k1c.length; i++) {
				assertEquals(k1c[i] * m1, v1.komponenten[i], EPS);
			}
			for (int i = 0; i < k2c.length; i++) {
				assertEquals(k2c[i] * m2, v2.komponenten[i], EPS);
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__skalarProdukt__A() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector(k1.length);
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			double expected = 0;
			for (int i = 0; i < k1c.length; i++) {
				expected += k1c[i] * k2c[i];
			}
			double actual = v1.skalarProdukt(v2);
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
			assertEquals(expected, actual, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__skalarProdukt__B() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector(k1.length);
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			double expected = 0;
			for (int i = 0; i < k1c.length; i++) {
				expected += k1c[i] * k2c[i];
			}
			double actual = Vektor.skalarProdukt(v1, v2);
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
			assertEquals(expected, actual, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__addition__A() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector(k1.length);
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			v1.addition(v2);
			for (int i = 0; i < k1c.length; i++) {
				assertEquals(k1c[i] + k2c[i], v1.komponenten[i], EPS);
			}
			assertArrayEquals(k2c, v2.komponenten, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__addition__B() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = generateRandomVector(), k2 = generateRandomVector(k1.length);
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			Vektor v = Vektor.addition(v1, v2);
			for (int i = 0; i < k1c.length; i++) {
				assertEquals(k1c[i] + k2c[i], v.komponenten[i], EPS);
			}
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
		}
	}

	@Test(timeout = 666)
	public void pubTest__norm() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = new double[1 + RND.nextInt(666)], k2 = new double[1 + RND.nextInt(666)];
			for (int i = 0; i < k1.length; i++) {
				k1[i] = Math.sqrt(2 * i + 1);
			}
			for (int i = 0; i < k2.length; i++) {
				k2[i] = Math.sqrt(2 * i + 1);
			}
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			double actual1 = v1.norm(), actual2 = v2.norm();
			assertArrayEquals(k1c, v1.komponenten, EPS);
			assertArrayEquals(k2c, v2.komponenten, EPS);
			assertEquals(k1.length, actual1, EPS);
			assertEquals(k2.length, actual2, EPS);
		}
		Vektor vec = new Vektor(new double[] { 3.0, 4.0 });
		assertEquals(5.0, vec.norm(), EPS);
	}

	@Test(timeout = 666)
	public void pubTest__normiere() {
		for (int pass = 0; pass < 10; pass++) {
			double[] k1 = new double[1 + RND.nextInt(666)], k2 = new double[1 + RND.nextInt(666)];
			for (int i = 0; i < k1.length; i++) {
				k1[i] = Math.sqrt(2 * i + 1);
			}
			for (int i = 0; i < k2.length; i++) {
				k2[i] = Math.sqrt(2 * i + 1);
			}
			double[] k1c = Arrays.copyOf(k1, k1.length), k2c = Arrays.copyOf(k2, k2.length);
			Vektor v1 = new Vektor(k1), v2 = new Vektor(k2);
			randomlyModifyVector(k1);
			randomlyModifyVector(k2);
			v1.normiere();
			v2.normiere();
			for (int i = 0; i < k1c.length; i++) {
				assertEquals(k1c[i] / k1c.length, v1.komponenten[i], EPS);
			}
			for (int i = 0; i < k2c.length; i++) {
				assertEquals(k2c[i] / k2c.length, v2.komponenten[i], EPS);
			}
		}
		Vektor vec = new Vektor(new double[] { 3.0, 3.0, 3.0 });
		vec.normiere();
		for (double k : vec.komponenten) {
			assertEquals(0.5773502691896257, k, EPS);
		}
	}

	// ========== HELPER ==========
	protected static final double[] generateRandomVector(int size) {
		double[] k = new double[size];
		for (int i = 0; i < k.length; i++) {
			k[i] = 4711 * RND.nextDouble() - 666;
		}
		return k;
	}

	protected static final double[] generateRandomVector() {
		return generateRandomVector(1 + RND.nextInt(666));
	}

	protected static final void randomlyModifyVector(double[] k) {
		for (int i = 0; i < k.length; i++) {
			k[i] = 4711 * RND.nextDouble() - 666;
		}
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	protected static final Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	protected static final Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	protected static final Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	protected static final Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}
}