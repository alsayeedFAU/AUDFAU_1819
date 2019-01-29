import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class CatHotelPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_CatHotel = "CatHotel";

	// ========== TEST-DATA ==========
	protected static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__CatBase__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<CatBase> clazz = CatBase.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isFinal(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 2, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isProtected(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, !Modifier.isStatic(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isFinal(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, "height".equals(f.getName()) || "width".equals(f.getName()));
			assertSame(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, int.class, f.getType());
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, cons.length);
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 2, meths.length);
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__CatHouse__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<CatHouse> clazz = CatHouse.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", CatBase.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 1, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isPrivate(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, !Modifier.isStatic(f.getModifiers()));
			assertEquals(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, "crs", f.getName());
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, f.getType().isArray());
			Class<?> t = f.getType();
			while (t.isArray()) {
				t = t.getComponentType();
			}
			assertSame(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, CatRoom.class, t);
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, cons.length);
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 5, meths.length);
		for (Method m : meths) {
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Modifier.isPublic(m.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isStatic(m.getModifiers()));
		}
	}

	@Test(timeout = 666)
	public void pubTest__Intestines__CatRoom__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<CatRoom> clazz = CatRoom.class;
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz + " soll genau die vorgegebenen Modifikatoren haben!", !Modifier.isInterface(clazz.getModifiers()));
		assertSame(clazz + " soll genau eine bestimmte Super-Klasse haben!", CatBase.class, clazz.getSuperclass());
		assertEquals(clazz + " soll keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " soll keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals(clazz + " soll keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Attribute haben!", 2, fields.length);
		for (Field f : fields) {
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, Modifier.isPrivate(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, !Modifier.isFinal(f.getModifiers()));
			assertTrue(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, !Modifier.isStatic(f.getModifiers()));
			if ("x".equals(f.getName()) || "y".equals(f.getName())) {
				assertSame(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f, int.class, f.getType());
			} else {
				fail(clazz + " soll genau die vorgegebenen Attribute haben! Falsch ist: " + f);
			}
		}
		Constructor<?>[] cons = getDeclaredConstructors(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Konstruktoren (ggf. inkl. default cons) haben!", 1, cons.length);
		Method[] meths = getDeclaredMethods(clazz);
		assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben!", 4, meths.length);
		for (Method m : meths) {
			if ("setXY".equals(m.getName())) {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isPublic(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isProtected(m.getModifiers()));
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isPrivate(m.getModifiers()));
			} else {
				assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, Modifier.isPublic(m.getModifiers()));
			}
			assertTrue(clazz + " soll genau die vorgegebenen Methoden haben! Falsch ist: " + m, !Modifier.isStatic(m.getModifiers()));
		}
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__CatBase() {
		for (int pass = 0; pass < 10; pass++) {
			int h = 1 + RND.nextInt(42), w = 1 + RND.nextInt(42);
			CatBase[] cbs = { new CatBase(h, w) {
			}, new CatHouse(h, w), new CatRoom(h, w) };
			for (CatBase cb : cbs) {
				int actualHeight = cb.getHeight(), actualWidth = cb.getWidth();
				assertEquals(h, actualHeight);
				assertEquals(w, actualWidth);
			}
		}
		for (int pass = 0; pass < 30; pass++) {
			int h = -RND.nextInt(42) - 1, w = -RND.nextInt(42) - 1;
			boolean exceptionSeen = true;
	
			try {
				if (pass % 3 == 0) {
					new CatHouse(h, w);
				} else if (pass % 3 == 1) {
					new CatRoom(h, w);
				} else {
					new CatBase(h, w) {
					};
				}
				exceptionSeen = false;
			} catch (Throwable t) {
				assertSame(IllegalArgumentException.class, t.getClass());
			}
			assertTrue("Expected an exception but didn't see one...", exceptionSeen);
		}
	}

	@Test(timeout = 666)
	public void pubTest__CatRoom__getX__getY__setXY() {
		for (int pass = 0; pass < 10; pass++) {
			CatRoom cr = new CatRoom(1 + RND.nextInt(42), 1 + RND.nextInt(42));
			int actualX = cr.getX(), actualY = cr.getY();
			assertEquals(-1, actualX);
			assertEquals(-1, actualY);
			int cr_x = RND.nextInt(42), cr_y = RND.nextInt(42);
			cr.setXY(cr_x, cr_y);
			assertEquals(cr_x, cr.getX());
			assertEquals(cr_y, cr.getY());
			cr.setXY(-1, -1);
			assertEquals(-1, actualX);
			assertEquals(-1, actualY);
		}
	}

	@Test(timeout = 1666)
	public void pubTest__CatHouse__canPlace_before_place() {
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 99 + RND.nextInt(42), ch_w = 99 + RND.nextInt(42);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(42), cr_w = 7 + RND.nextInt(42);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			int cr_x = 7 + RND.nextInt(42), cr_y = 7 + RND.nextInt(42);
			boolean actual = ch.canPlace(cr, cr_x, cr_y);
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): canPlace(" + cr_x + "," + cr_y + ").", actual);
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): canPlace(" + cr_x + "," + cr_y + "), getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
				}
			}
		}
		for (int pass = 0; pass < 42; pass++) {
			int ch_h = 99 + RND.nextInt(42), ch_w = 99 + RND.nextInt(42);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(42), cr_w = 7 + RND.nextInt(42);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			boolean exceptionSeen = true;
			try {
				if (pass % 2 == 0) {
					ch.canPlace(cr, -RND.nextInt(42) - 1, -RND.nextInt(42) - 1);
				} else {
					ch.canPlace(cr, ch_w + RND.nextInt(42), ch_h + RND.nextInt(42));
				}
				exceptionSeen = false;
			} catch (Throwable t) {
				assertSame(IllegalArgumentException.class, t.getClass());
			}
			assertTrue("Expected an exception but didn't see one...", exceptionSeen);
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): canPlace at illegal place, getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
				}
			}
		}
	}

	@Test(timeout = 1666)
	public void pubTest__CatHouse__place__getCatRoomAt__CatRoom__getX__getY() {
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 99 + RND.nextInt(42), ch_w = 99 + RND.nextInt(42);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(42), cr_w = 7 + RND.nextInt(42);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			int cr_x = 7 + RND.nextInt(42), cr_y = 7 + RND.nextInt(42);
			ch.place(cr, cr_x, cr_y);
			int actualX = cr.getX(), actualY = cr.getY();
			assertEquals(cr_x, actualX);
			assertEquals(cr_y, actualY);
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					if (x >= cr_x && x < cr_x + cr_w && y >= cr_y && y < cr_y + cr_h) {
						assertSame("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place(" + cr_x + "," + cr_y + "), getCatRoomAt(" + x + "," + y + ").", cr, ch.getCatRoomAt(x, y));
					} else {
						assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place(" + cr_x + "," + cr_y + "), getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
					}
				}
			}
		}
		for (int pass = 0; pass < 42; pass++) {
			int ch_h = 99 + RND.nextInt(42), ch_w = 99 + RND.nextInt(42);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(42), cr_w = 7 + RND.nextInt(42);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			boolean exceptionSeen = true;
			try {
				switch (pass % 6) {
				case 0:
					ch.place(cr, -RND.nextInt(42) - 1, RND.nextInt(42));
					break;
				case 1:
					ch.place(cr, RND.nextInt(42), -RND.nextInt(42) - 1);
					break;
				case 2:
					ch.place(cr, ch_w + RND.nextInt(42), RND.nextInt(42));
					break;
				case 3:
					ch.place(cr, RND.nextInt(42), ch_h + RND.nextInt(42));
				case 4:
					ch.place(cr, ch_w - 1 - RND.nextInt(cr_w / 2), RND.nextInt(42));
					break;
				case 5:
					ch.place(cr, RND.nextInt(42), ch_h - 1 - RND.nextInt(cr_h / 2));
					break;
				}
				exceptionSeen = false;
			} catch (Throwable t) {
				assertSame(IllegalArgumentException.class, t.getClass());
			}
			assertTrue("Expected an exception but didn't see one...", exceptionSeen);
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place at illegal place, getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
				}
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__CatHouse__place__remove__getCatRoomAt__CatRoom__getX__getY() {
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 99 + RND.nextInt(42), ch_w = 99 + RND.nextInt(42);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(42), cr_w = 7 + RND.nextInt(42);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			boolean exceptionSeen = true;
			try {
				ch.remove(cr);
				exceptionSeen = false;
			} catch (Throwable t) {
				assertSame(IllegalArgumentException.class, t.getClass());
			}
			assertTrue("Expected an exception but didn't see one...", exceptionSeen);
			int cr_x = 7 + RND.nextInt(42), cr_y = 7 + RND.nextInt(42);
			ch.place(cr, cr_x, cr_y);
			assertEquals(cr_x, cr.getX());
			assertEquals(cr_y, cr.getY());
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					if (x >= cr_x && x < cr_x + cr_w && y >= cr_y && y < cr_y + cr_h) {
						assertSame("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place(" + cr_x + "," + cr_y + "), getCatRoomAt(" + x + "," + y + ").", cr, ch.getCatRoomAt(x, y));
					} else {
						assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place(" + cr_x + "," + cr_y + "), getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
					}
				}
			}
			ch.remove(cr);
			assertEquals(-1, cr.getX());
			assertEquals(-1, cr.getY());
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): place and remove, getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
				}
			}
		}
	}

	@Test(timeout = 6666)
	public void pubTest__CatRoom__placeNextPossible() {
		boolean actual;
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 42 + RND.nextInt(7), ch_w = 42 + RND.nextInt(7);
			CatHouse ch = new CatHouse(ch_h, ch_w);
			int cr_h = 7 + RND.nextInt(7), cr_w = 7 + RND.nextInt(7);
			CatRoom cr = new CatRoom(cr_h, cr_w);
			assertEquals(-1, cr.getX());
			assertEquals(-1, cr.getY());
			for (int i = 0; i < 128; i++) {
				actual = cr.placeNextPossible(ch);
				assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [" + (i + 1) + "x]", actual);
				int cr_x = i % (ch_w - cr_w + 1), cr_y = i / (ch_w - cr_w + 1);
				assertEquals(cr_x, cr.getX());
				assertEquals(cr_y, cr.getY());
				for (int y = 0; y < ch_h; y++) {
					for (int x = 0; x < ch_w; x++) {
						if (x >= cr_x && x < cr_x + cr_w && y >= cr_y && y < cr_y + cr_h) {
							assertSame("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [" + (i + 1) + "x], getCatRoomAt(" + x + "," + y + ").", cr, ch.getCatRoomAt(x, y));
						} else {
							assertNull("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [" + (i + 1) + "x], getCatRoomAt(" + x + "," + y + ").", ch.getCatRoomAt(x, y));
						}
					}
				}
			}
			ch.place(cr, ch_w - cr_w - 2, ch_h - cr_h);
			actual = cr.placeNextPossible(ch);
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [towards end]", actual);
			actual = cr.placeNextPossible(ch);
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [at end]", actual);
			actual = cr.placeNextPossible(ch);
			assertFalse("CatHouse(" + ch_h + "," + ch_w + ") / CatRoom(" + cr_h + "," + cr_w + "): placeNextPossible [after end]", actual);
		}
	}

	@Test(timeout = 6666)
	public void pubTest__CatHouse__placeAll__sheetExample__andModified() {
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 3, ch_w = 4;
			CatHouse ch = new CatHouse(ch_h, ch_w);
			ArrayList<CatRoom> crsAL = new ArrayList<>();
			crsAL.addAll(Arrays.asList(new CatRoom(2, 2), new CatRoom(1, 2), new CatRoom(1, 1), new CatRoom(1, 4)));
			Collections.shuffle(crsAL);
			CatRoom[] crs = crsAL.toArray(new CatRoom[0]);
			boolean actual = ch.placeAll(Arrays.copyOf(crs, crs.length));
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / " + CatHotelPublicTest.rooms2string(crs) + "): placeAll - possible.", actual);
			boolean[][] used = new boolean[ch.height][ch.width];
			for (CatRoom cr : crs) {
				assertTrue("Room cannot fit.", cr.getY() >= 0 && cr.getX() >= 0 && cr.getY() + cr.getHeight() <= ch_h && cr.getX() + cr.getWidth() <= ch_w);
				for (int y = 0; y < cr.getHeight(); y++) {
					for (int x = 0; x < cr.getWidth(); x++) {
						assertFalse("Rooms overlap.", used[cr.getY() + y][cr.getX() + x]);
						if(!used[cr.getY() + y][cr.getX() + x]){
							//System.out.println("HIER!");
						}
						used[cr.getY() + y][cr.getX() + x] = true;
					}
				}
			}
			
			//System.out.println(Arrays.deepToString(used));
			
		}
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 3, ch_w = 4;
			CatHouse ch = new CatHouse(ch_h, ch_w);
			ArrayList<CatRoom> crsAL = new ArrayList<>();
			crsAL.addAll(Arrays.asList(new CatRoom(2, 2), new CatRoom(1, 2), new CatRoom(1, 1), new CatRoom(1, 4) /**/, new CatRoom(1, 1) /**/ ));
			Collections.shuffle(crsAL);
			CatRoom[] crs = crsAL.toArray(new CatRoom[0]);
			boolean actual = ch.placeAll(Arrays.copyOf(crs, crs.length));
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / " + CatHotelPublicTest.rooms2string(crs) + "): placeAll - possible.", actual);
			boolean[][] used = new boolean[ch.height][ch.width];
			for (CatRoom cr : crs) {
				assertTrue("Room cannot fit.", cr.getY() >= 0 && cr.getX() >= 0 && cr.getY() + cr.getHeight() <= ch_h && cr.getX() + cr.getWidth() <= ch_w);
				for (int y = 0; y < cr.getHeight(); y++) {
					for (int x = 0; x < cr.getWidth(); x++) {
						assertFalse("Rooms overlap.", used[cr.getY() + y][cr.getX() + x]);
						used[cr.getY() + y][cr.getX() + x] = true;
					}
				}
			}
		}
		for (int pass = 0; pass < 10; pass++) {
			int ch_h = 3, ch_w = 4;
			CatHouse ch = new CatHouse(ch_h, ch_w);
			ArrayList<CatRoom> crsAL = new ArrayList<>();
			crsAL.addAll(Arrays.asList(new CatRoom(2, 2), new CatRoom(1, 2), new CatRoom(1, 1), new CatRoom(1, 4) /**/, new CatRoom(1, 1), new CatRoom(1, 1) /**/ ));
			Collections.shuffle(crsAL);
			CatRoom[] crs = crsAL.toArray(new CatRoom[0]);
			boolean actual = ch.placeAll(Arrays.copyOf(crs, crs.length));
			assertFalse("CatHouse(" + ch_h + "," + ch_w + ") / " + CatHotelPublicTest.rooms2string(crs) + "): placeAll - possible.", actual);
			for (CatRoom cr : crs) {
				assertTrue("Room must not pe placed.", cr.getY() == -1 && cr.getX() == -1);
			}
		}
	}

	@Test(timeout = 16666)
	public void pubTest__CatHouse__placeAll__random() {
		for (int pass = 0; pass < 3; pass++) {
			int ch_h = 6 + RND.nextInt(3), ch_w = 6 + RND.nextInt(3); // 6x6 bis 8x8
			boolean[][] taken = new boolean[ch_h][ch_w];
			CatHouse ch = new CatHouse(ch_h, ch_w);
			ArrayList<CatRoom> crsAL = new ArrayList<>();
			for (int y = 0; y < ch_h; y++) {
				for (int x = 0; x < ch_w; x++) {
					if (!taken[y][x]) {
						int cr_h = 1 + (ch_h - y > 0 ? RND.nextInt(ch_h - y) : 0);
						int cr_w = 1, cr_w_max = 1 + (ch_w - x > 0 ? RND.nextInt(ch_w - x) : 0);
						while (cr_w < cr_w_max && !taken[y][x + cr_w]) {
							cr_w++;
						}
						CatRoom cr = new CatRoom(cr_h, cr_w);
						crsAL.add(cr);
						for (int dy = y; dy < y + cr_h; dy++) {
							for (int dx = x; dx < x + cr_w; dx++) {
								taken[dy][dx] = true;
							}
						}
					}
				}
			}
			Collections.shuffle(crsAL);
			CatRoom[] crs = crsAL.toArray(new CatRoom[0]);
			//System.out.println(Arrays.toString(crs));
			boolean actual = ch.placeAll(Arrays.copyOf(crs, crs.length));
			assertTrue("CatHouse(" + ch_h + "," + ch_w + ") / " + CatHotelPublicTest.rooms2string(crs) + "): placeAll - possible.", actual);
			boolean[][] used = new boolean[ch.height][ch.width];
			for (CatRoom cr : crs) {
				assertTrue("Room cannot fit.", cr.getY() >= 0 && cr.getX() >= 0 && cr.getY() + cr.getHeight() <= ch_h && cr.getX() + cr.getWidth() <= ch_w);
				for (int y = 0; y < cr.getHeight(); y++) {
					for (int x = 0; x < cr.getWidth(); x++) {
						assertFalse("Rooms overlap.", used[cr.getY() + y][cr.getX() + x]);
						used[cr.getY() + y][cr.getX() + x] = true;
					}
				}
			}
		}
	}

	// ========== HELPER ==========
	protected static final String rooms2string(CatRoom[] crs) {
		String s = "[";
		for (CatRoom cr : crs) {
			s += "CatRoom(" + cr.height + "," + cr.getWidth() + ") ";
		}
		return s + "]";
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