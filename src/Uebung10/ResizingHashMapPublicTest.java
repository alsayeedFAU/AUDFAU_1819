import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

public class ResizingHashMapPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_insertMapping = "insertMapping";
	protected static final String EX_NAME_getBucketIndex = "getBucketIndex";
	protected static final String EX_NAME_getMapping = "getMapping";
	protected static final String EX_NAME_get = "get";
	protected static final String EX_NAME_containsKey = "containsKey";
	protected static final String EX_NAME_put = "put";
	protected static final String EX_NAME_remove = "remove";
	protected static final String EX_NAME_resize = "resize";
	// --------------------
	protected static final String METH_NAME_size = "ResizingHashMap.size";
	protected static final String METH_NAME_insertMapping = "ResizingHashMap.insertMapping";
	protected static final String METH_NAME_getBucketIndex = "ResizingHashMap.getBucketIndex";
	protected static final String METH_NAME_getMapping = "ResizingHashMap.getMapping";
	protected static final String METH_NAME_get = "ResizingHashMap.get";
	protected static final String METH_NAME_containsKey = "ResizingHashMap.containsKey";
	protected static final String METH_NAME_put = "ResizingHashMap.put";
	protected static final String METH_NAME_remove = "ResizingHashMap.remove";
	protected static final String METH_NAME_resize = "ResizingHashMap.resize";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	protected static final void check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		@SuppressWarnings("rawtypes")
		Class<ResizingHashMap> clazz = ResizingHashMap.class;
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", AbstractHashMap.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " soll keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, getDeclaredConstructors(clazz).length);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", getDeclaredMethods(AbstractHashMap.class).length - 1, getDeclaredMethods(clazz).length); // abgzueglich "final void initialize"
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__insertMapping__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__getBucketIndex__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__getMapping__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__get__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__containsKey__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__put__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__remove__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__resize__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__size_insertMapping() throws Exception {
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(6 + RND.nextInt(7));
		assertEquals("Initial size wrong.", 0, map.size());
		// --------------------
		Mapping<Integer, String> m11 = new Mapping<>(1, genRndStr(), null), m12 = new Mapping<>(1, genRndStr(), null), m51 = new Mapping<>(5, genRndStr(), null);
		map.insertMapping(1, m11);
		assertEquals("Size wrong.", 1, map.size());
		map.insertMapping(1, m12);
		assertEquals("Size wrong.", 2, map.size());
		map.insertMapping(5, m51);
		assertEquals("Size wrong.", 3, map.size());
		assertSame("Mapping not found in expected bucket.", m11, map.buckets[1]);
		assertSame("Mapping not found in expected bucket.", m12, map.buckets[1].next);
		assertSame("Mapping not found in expected bucket.", m51, map.buckets[5]);
	}

	@Test(timeout = 666)
	public void pubTest__getBucketIndex() throws Exception {
		int cEasy = 6 + RND.nextInt(7);
		AbstractHashMap<Key, Object> mapEasy = new ResizingHashMap<Key, Object>(cEasy);
		for (int hash = 0; hash < cEasy; hash++) {
			assertEquals(ResizingHashMapPublicTest.METH_NAME_getBucketIndex + " returned wrong value.", hash, mapEasy.getBucketIndex(new Key(hash, "")));
		}
		// --------------------
		int cTricky = 6 + RND.nextInt(7);
		AbstractHashMap<Long, String> mapTricky = new ResizingHashMap<Long, String>(cTricky);
		for (Long l = -42L; l <= 42; l++) {
			int h = (((int) (l ^ (l >>> 32)) % cTricky + ((int) (l ^ (l >>> 32)) < 0 ? cTricky : 0)) % cTricky);
			assertEquals(ResizingHashMapPublicTest.METH_NAME_getBucketIndex + " returned wrong value.", h, mapTricky.getBucketIndex(l));
		}
	}

	@Test(timeout = 666)
	public void pubTest__getMapping() throws Exception {
		int c = 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		Mapping<Integer, String> m31 = new Mapping<Integer, String>(3, genRndStr(), null);
		map.insertMapping(3, m31);
		Mapping<Integer, String> m31a = map.getMapping(3);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m31, m31a);
		// --------------------
		Mapping<Integer, String> m32 = new Mapping<Integer, String>(c + 3, genRndStr(), null);
		map.insertMapping(3, m32);
		Mapping<Integer, String> m32a = map.getMapping(c + 3);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m31, m31a);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m32, m32a);
		// --------------------
		Mapping<Integer, String> m666 = new Mapping<Integer, String>(666, genRndStr(), null);
		map.insertMapping(666 % c, m666);
		Mapping<Integer, String> m666a = map.getMapping(666);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m31, m31a);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m32, m32a);
		assertSame(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", m666, m666a);
		// --------------------
		for (Long l = -42L; l <= 42; l++) {
			int h = (((int) (l ^ (l >>> 32)) % c + ((int) (l ^ (l >>> 32)) < 0 ? c : 0)) % c);
			if (h != 3 && h != 666 % c) {
				assertNull(ResizingHashMapPublicTest.METH_NAME_getMapping + " returned unexpected mapping.", map.getMapping(h));
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__get() throws Exception {
		int c = 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		String[] vs = new String[42];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = genRndStr();
			map.insertMapping(i % c, new Mapping<Integer, String>(i, vs[i], null));
		}
		for (int i = 0; i < 2 * vs.length; i++) {
			String va = map.get(i);
			if (i < vs.length) {
				assertEquals(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value.", vs[i], va);
			} else {
				assertNull(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value.", va);
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__containsKey() throws Exception {
		int c = 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		String[] vs = new String[42];
		boolean[] vIsIn = new boolean[vs.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = genRndStr();
			if (vIsIn[i] = RND.nextBoolean()) {
				map.insertMapping(i % c, new Mapping<Integer, String>(i, vs[i], null));
			}
		}
		for (int i = 0; i < 2 * vs.length; i++) {
			assertEquals(ResizingHashMapPublicTest.METH_NAME_containsKey + " returned wrong value.", i < vs.length && vIsIn[i], map.containsKey(i));
		}
	}

	@Test(timeout = 666)
	public void pubTest__put() throws Exception {
		int c = 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		String[] vs = new String[42];
		for (int i = 0; i < vs.length / 2; i++) {
			vs[i] = genRndStr();
			map.insertMapping(i % c, new Mapping<Integer, String>(i, vs[i], null));
		}
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_insertMapping, vs.length / 2, map.size());
		for (int i = 0; i < vs.length; i++) {
			if (RND.nextBoolean() || i >= vs.length / 2) {
				vs[i] = genRndStr();
				map.put(i, vs[i]);
			}
		}
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_put, vs.length, map.size());
		for (int i = 0; i < vs.length; i++) {
			String va = map.get(i);
			assertEquals(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value.", vs[i], va);
		}
	}

	@Test(timeout = 666)
	public void pubTest__remove() throws Exception {
		int c = 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		String[] vs = new String[42];
		boolean[] vIsIn = new boolean[vs.length];
		int del = 0;
		for (int i = 0; i < vs.length; i++) {
			vs[i] = genRndStr();
			map.insertMapping(i % c, new Mapping<Integer, String>(i, vs[i], null));
		}
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_insertMapping, vs.length, map.size());
		for (int i = 0; i < vs.length; i++) {
			if (!(vIsIn[i] = RND.nextBoolean())) {
				map.remove(i);
				del++;
			}
		}
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_remove, vs.length - del, map.size());
		for (int i = 0; i < vs.length; i++) {
			if (vIsIn[i]) {
				assertEquals(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value after " + ResizingHashMapPublicTest.METH_NAME_remove, vs[i], map.get(i));
			} else {
				assertNull(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value after " + ResizingHashMapPublicTest.METH_NAME_remove, map.get(i));
			}
		}
	}

	@Test(timeout = 6666)
	public void pubTest__resize() throws Exception {
		int c = 6 + RND.nextInt(7), cResized = c + 6 + RND.nextInt(7);
		AbstractHashMap<Integer, String> map = new ResizingHashMap<Integer, String>(c);
		String[] vs = new String[42];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = genRndStr();
			assertEquals(ResizingHashMapPublicTest.METH_NAME_getBucketIndex + " returned wrong value before " + ResizingHashMapPublicTest.METH_NAME_resize, i % c, map.getBucketIndex(i));
			map.insertMapping(i % c, new Mapping<Integer, String>(i, vs[i], null));
		}
		assertEquals("Wrong number of buckets before " + ResizingHashMapPublicTest.METH_NAME_resize, c, map.buckets.length);
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_insertMapping, vs.length, map.size());
		map.resize(cResized);
		assertEquals("Wrong number of buckets after " + ResizingHashMapPublicTest.METH_NAME_resize, cResized, map.buckets.length);
		assertEquals(ResizingHashMapPublicTest.METH_NAME_size + " wrong after " + ResizingHashMapPublicTest.METH_NAME_resize, vs.length, map.size());
		for (int i = 0; i < vs.length; i++) {
			assertEquals(ResizingHashMapPublicTest.METH_NAME_getBucketIndex + " returned wrong value after " + ResizingHashMapPublicTest.METH_NAME_resize, i % cResized, map.getBucketIndex(i));
			assertEquals(ResizingHashMapPublicTest.METH_NAME_get + " returned wrong value after " + ResizingHashMapPublicTest.METH_NAME_resize, vs[i], map.get(i));
		}
	}

	// ========== HELPER ==========
	protected static final class Key {
		protected int hash;
		protected String key;

		public Key(int hash, String key) {
			this.hash = hash;
			this.key = key;
		}

		@Override
		public int hashCode() {
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}
	}

	protected static final String genRndStr() {
		String s = "";
		for (int i = 0; i < 5; i++) {
			s += Character.toString((char) (RND.nextBoolean() ? 'a' + RND.nextInt('z' - 'a') : 'A' + RND.nextInt('Z' - 'A')));
		}
		return s;
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