import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.*;

public class LangtonSystemDatatypePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_LSD = "LSD";

	// ========== TEST-DATA ==========
	protected static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Colour__INTESTINES__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<Colour> clazz = Colour.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isPublic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isStatic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isFinal(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertTrue(clazz + " soll eine Aufzaehlung sein!", clazz.isEnum());
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Enum.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 2, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isPublic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isStatic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isFinal(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, "WHITE".equals(f.getName()) || "BLACK".equals(f.getName()));
			assertSame(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Colour.class, f.getType());
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, cons.length);
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 3, meths.length);
		for (Method m : meths) {
			if ("flip".equals(m.getName())) {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Modifier.isPublic(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isStatic(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isFinal(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isAbstract(m.getModifiers()));
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Colour.class, m.getReturnType());
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, 0, m.getParameterCount());
			} else {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, "valueOf".equals(m.getName()) || "values".equals(m.getName()));
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__Direction__INTESTINES__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<Direction> clazz = Direction.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isPublic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isStatic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isFinal(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertTrue(clazz + " soll eine Aufzaehlung sein!", clazz.isEnum());
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Enum.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 4, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isPublic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isStatic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isFinal(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, "E".equals(f.getName()) || "N".equals(f.getName()) || "W".equals(f.getName()) || "S".equals(f.getName()));
			assertSame(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Direction.class, f.getType());
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, cons.length);
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 4, meths.length);
		for (Method m : meths) {
			if ("turnCounterCW".equals(m.getName()) || "turnCW".equals(m.getName())) {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Modifier.isPublic(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isStatic(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isFinal(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isAbstract(m.getModifiers()));
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Direction.class, m.getReturnType());
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, 0, m.getParameterCount());
			} else {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, "valueOf".equals(m.getName()) || "values".equals(m.getName()));
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__LSD__INTESTINES__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<LSD> clazz = LSD.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isPublic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isStatic(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isFinal(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertTrue(clazz + " soll keine Aufzaehlung sein!", !clazz.isEnum());
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 2, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isPrivate(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, !Modifier.isStatic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isFinal(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, f.getType() == LSD.class || f.getType() == Direction.class);
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 2, cons.length);
		for (Constructor<?> c : cons) {

			assertSame(clazz + " soll genau die vorgegebenen Konstruktoren haben! Falsch ist: " + c, 1, c.getParameterCount());
		}
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 5, meths.length);
		for (Method m : meths) {
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Modifier.isPublic(m.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isStatic(m.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isFinal(m.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isAbstract(m.getModifiers()));
			switch (m.getName()) {
			case "step":
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, LSD.class, m.getReturnType());
				break;
			case "getCol":
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Colour.class, m.getReturnType());
				break;
			case "getDir":
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Direction.class, m.getReturnType());
				break;
			case "getX":
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Long.class, m.getReturnType());
				break;
			case "getY":
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Long.class, m.getReturnType());
				break;
			default:
				fail(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m);
			}
			if ("getCol".equals(m.getName())) {
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, 2, m.getParameterCount());
			} else {
				assertSame(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, 0, m.getParameterCount());
			}
		}
	}

	// ==================== PUBLIC TEST ====================
	@Test(timeout = 666)
	public void pubTest__Direction__Colour() {
		Colour[] cs = { Colour.BLACK, Colour.WHITE };
		for (int i = 0; i < cs.length; i++) {
			Colour actual = cs[i].flip();
			assertSame(cs[(i + 1) % cs.length], actual);
		}
		// ----------
		Direction[] ds = { Direction.E, Direction.N, Direction.W, Direction.S };
		for (int i = 0; i < ds.length; i++) {
			Direction actualCW = ds[i].turnCounterCW();
			assertSame(ds[(i + 1) % ds.length], actualCW);
			Direction actualCCW = ds[i].turnCW();
			assertSame(ds[(i + ds.length - 1) % ds.length], actualCCW);
		}
	}

	@Test(timeout = 666)
	public void pubTest__LSD__sheet() {
		Direction[] ds = { Direction.E, Direction.N, Direction.W, Direction.S };
		int[][] dsd = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		for (int pass = 0; pass < 20; pass++) {
			// ---------- START ----------
			int d0 = RND.nextInt(ds.length);
			long x0 = 0, y0 = 0;
			LSD l0 = new LSD(ds[d0]), l1 = l0.step(), l2 = l1.step(), l3 = l2.step(), l4 = l3.step(), l5 = l4.step();
			
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					Colour c0_actual = l0.getCol(Long.valueOf(x), Long.valueOf(y));
					assertSame(Colour.WHITE, c0_actual);
				}
			}
			Direction d0_actual = l0.getDir();
			assertSame(ds[d0], d0_actual);
			Long x0_actual = l0.getX(), y0_actual = l0.getY();
			assertEquals(Long.valueOf(0), x0_actual);
			assertEquals(Long.valueOf(0), y0_actual);
			// ---------- 1x STEP ----------
			int d1 = (d0 + ds.length - 1) % ds.length;
			long x1 = x0 + dsd[d1][0], y1 = y0 + dsd[d1][1];
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					//System.out.println("L1: x|y " + x + "|" + y);
					assertSame(x == x0 && y == y0 ? Colour.BLACK : Colour.WHITE, l1.getCol(Long.valueOf(x), Long.valueOf(y)));
				}
			}
			assertSame(ds[d1], l1.getDir());
			assertEquals(Long.valueOf(x1), l1.getX());
			assertEquals(Long.valueOf(y1), l1.getY());
			// ---------- 2x STEP ----------
			int d2 = (d1 + ds.length - 1) % ds.length;
			long x2 = x1 + dsd[d2][0], y2 = y1 + dsd[d2][1];
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					assertSame(x == x0 && y == y0 || x == x1 && y == y1 ? Colour.BLACK : Colour.WHITE, l2.getCol(Long.valueOf(x), Long.valueOf(y)));
				}
			}
			assertSame(ds[d2], l2.getDir());
			assertEquals(Long.valueOf(x2), l2.getX());
			assertEquals(Long.valueOf(y2), l2.getY());
			// ---------- 3x STEP ----------
			int d3 = (d2 + ds.length - 1) % ds.length;
			long x3 = x2 + dsd[d3][0], y3 = y2 + dsd[d3][1];
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					assertSame(x == x0 && y == y0 || x == x1 && y == y1 || x == x2 && y == y2 ? Colour.BLACK : Colour.WHITE, l3.getCol(Long.valueOf(x), Long.valueOf(y)));
				}
			}
			assertSame(ds[d3], l3.getDir());
			assertEquals(Long.valueOf(x3), l3.getX());
			assertEquals(Long.valueOf(y3), l3.getY());
			// ---------- 4x STEP ----------
			int d4 = (d3 + ds.length - 1) % ds.length;
			long x4 = x3 + dsd[d4][0], y4 = y3 + dsd[d4][1];
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					assertSame(x == x0 && y == y0 || x == x1 && y == y1 || x == x2 && y == y2 || x == x3 && y == y3 ? Colour.BLACK : Colour.WHITE, l4.getCol(Long.valueOf(x), Long.valueOf(y)));
				}
			}
			assertSame(ds[d4], l4.getDir());
			assertEquals(Long.valueOf(x4), l4.getX());
			assertEquals(Long.valueOf(y4), l4.getY());
			// ---------- 4x STEP == at START again... ----------
			assertSame(Colour.BLACK, l4.getCol(Long.valueOf(0), Long.valueOf(0)));
			assertSame(ds[d0], l4.getDir());
			assertEquals(Long.valueOf(0), l4.getX());
			assertEquals(Long.valueOf(0), l4.getY());
			// ---------- 5x STEP ----------
			int d5 = (d4 + 1) % ds.length;
			long x5 = x4 + dsd[d5][0], y5 = y4 + dsd[d5][1];
			for (long x = -3; x <= 3; x++) {
				for (long y = -3; y <= 3; y++) {
					assertSame(x == x1 && y == y1 || x == x2 && y == y2 || x == x3 && y == y3 ? Colour.BLACK : Colour.WHITE, l5.getCol(Long.valueOf(x), Long.valueOf(y)));
				}
			}
			assertSame(ds[d5], l5.getDir());
			assertEquals(Long.valueOf(x5), l5.getX());
			assertEquals(Long.valueOf(y5), l5.getY());
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