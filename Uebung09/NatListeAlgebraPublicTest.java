import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class NatListeAlgebraPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Liste = "Liste";
	protected static final String EX_NAME_Algebra = "Algebra";
	protected static final String EX_NAME_Algebra_pfz = "Algebra.pfz";
	protected static final String CLASS_NAME_Liste = "Liste";
	protected static final String METH_NAME_Liste_neu = "neu";
	protected static final String METH_NAME_Liste_vorne = "vorne";
	protected static final String METH_NAME_Liste_kopf = "kopf";
	protected static final String METH_NAME_Liste_rest = "rest";

	// ==================== define some Nats... ====================
	private static final Nat[] n;
	static {
		n = new Nat[666];
		n[0] = Nat.zero();
		for (int i = 1; i < n.length; i++) {
			n[i] = Nat.succ(n[i - 1]);
		}
	}

	// ==================== Intestines: Liste ====================
	@Test(timeout = 666)
	public void pubTest__Liste__INTESTINES__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Class.forName("Liste");
			assertFalse("Ist keine Klasse sondern eine Annotation.", clazz.isAnnotation());
			assertFalse("Ist keine Klasse sondern ein Enum.", clazz.isEnum());
			assertFalse("Ist keine Klasse sondern ein Interface.", clazz.isInterface());
			assertFalse("Ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals("Darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertEquals("Muss direkt von Object erben.", Object.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an anderen Schweinereien.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals("Hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredClasses().length);
			assertEquals("Hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			assertEquals("Hat falsche Anzahl an Attributen.", 2, clazz.getDeclaredFields().length);
			assertEquals("Hat falsche Anzahl an Methoden.", 4, clazz.getDeclaredMethods().length);
			Constructor<?> constructor = clazz.getDeclaredConstructor(); // default cons!
			assertNotNull("Konstruktor inkl. -parameter: inkorrekt", constructor);
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				assertTrue(field + " - Feld: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(field.getModifiers()));
				assertFalse(field + " - Feld: Ist kein Instanzattribut", Modifier.isStatic(field.getModifiers()));
			}
			assertTrue("Felder haben falschen Typ: eines muss sein: " + Liste.class, fields[0].getType().equals(Liste.class) || fields[1].getType().equals(Liste.class));
			assertTrue("Felder haben falschen Typ: eines muss generisch (wg. type erasure dann Object) sein.", fields[0].getType().equals(Object.class) || fields[1].getType().equals(Object.class));
			for (Method method : clazz.getDeclaredMethods()) {
				assertTrue(method + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertTrue(method + " - Methode: Ist faelschlicherweise Instanzmethode.", Modifier.isStatic(method.getModifiers()));
				assertFalse(method + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				switch (method.getName()) {
				case METH_NAME_Liste_neu:
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 0, method.getParameterTypes().length);
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 0, method.getParameterTypes().length);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Liste.class, method.getReturnType());
					break;
				case METH_NAME_Liste_vorne:
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 2, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Liste.class, method.getParameterTypes()[0]);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Object.class, method.getParameterTypes()[1]); // generic type => type erasure!
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Liste.class, method.getReturnType());
					break;
				case METH_NAME_Liste_kopf:
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 1, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Liste.class, method.getParameterTypes()[0]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Object.class, method.getReturnType()); // generic type => type erasure!
					break;
				case METH_NAME_Liste_rest:
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 1, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Liste.class, method.getParameterTypes()[0]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Liste.class, method.getReturnType());
					break;
				default:
					fail("Unbekannte Methode: " + method);
				}
			}
		} catch (ClassNotFoundException e) {
			fail("Klasse nicht gefunden: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	// ==================== Intestines: Algebra ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__INTESTINES__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Class.forName("Algebra");
			assertFalse("Ist keine Klasse sondern eine Annotation.", clazz.isAnnotation());
			assertFalse("Ist keine Klasse sondern ein Enum.", clazz.isEnum());
			assertFalse("Ist keine Klasse sondern ein Interface.", clazz.isInterface());
			assertFalse("Ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals("Darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertEquals("Muss direkt von Object erben.", Object.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an anderen Schweinereien.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals("Hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredClasses().length);
			assertEquals("Hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			if (clazz.getDeclaredFields().length == 1) {
				assertEquals("Hat unerwartete Attribute.", "$assertionsDisabled", clazz.getDeclaredFields()[0].getName());
			} else {
				assertEquals("Hat falsche Anzahl an Attributen.", 0, clazz.getDeclaredFields().length);
			}
			assertEquals("Hat falsche Anzahl an Methoden.", 8, clazz.getDeclaredMethods().length);
			Constructor<?> constructor = clazz.getDeclaredConstructor(); // default cons!
			assertNotNull("Konstruktor inkl. -parameter: inkorrekt", constructor);
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
			for (Method method : clazz.getDeclaredMethods()) {
				assertTrue(method + " - Methode: Ist faelschlicherweise Instanzmethode.", Modifier.isStatic(method.getModifiers()));
				assertFalse(method + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				if (method.getName().equals("pfz")) {
					assertTrue(method + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 1, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Nat.class, method.getParameterTypes()[0]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Liste.class, method.getReturnType());
				} else if (method.getName().equals("pfzH")) {
					assertTrue(method + " - Methode: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(method.getModifiers()));
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 2, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Nat.class, method.getParameterTypes()[0]);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Nat.class, method.getParameterTypes()[1]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Liste.class, method.getReturnType());
				} else {
					assertTrue(method + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 2, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Nat.class, method.getParameterTypes()[0]);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", Nat.class, method.getParameterTypes()[1]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", Nat.class, method.getReturnType());
				}
			}
		} catch (ClassNotFoundException e) {
			fail("Klasse nicht gefunden: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	// ==================== Liste ====================
	@Test(timeout = 666)
	public void pubTest__Liste__null_oder_neu() {
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_kopf + " von null muss null sein.", Liste.kopf(null));
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_rest + " von null muss null sein.", Liste.rest(null));
		Liste<Nat> l = Liste.<Nat> neu();
		Nat kopf = Liste.kopf(l);
		Liste<Nat> rest = Liste.rest(l);
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_kopf + " von leerer Liste muss null sein.", kopf);
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_rest + " von leerer Liste muss null sein.", rest);
	}

	@Test(timeout = 666)
	public void pubTest__Liste__nicht_leer() {
		Liste<String> l0 = Liste.<String> neu();
		Liste<String> l1a = Liste.vorne(l0, "AuD");
		Liste<String> l1b = Liste.vorne(l0, "PFP");
		Liste<String> l2a = Liste.vorne(l1a, "FAUL");
		Liste<String> l2b = Liste.vorne(l1b, "FAIL");
		Liste<String> l1x = Liste.rest(l2a);
		Liste<String> l1y = Liste.rest(l2b);
		Liste<String> l0x = Liste.rest(l1x);
		Liste<String> l0y = Liste.rest(l1y);
		String kopf2a = Liste.kopf(l2a);
		String kopf2b = Liste.kopf(l2b);
		assertNotNull(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", kopf2a);
		assertNotNull(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", kopf2b);
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "FAUL", Liste.kopf(l2a));
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "FAIL", Liste.kopf(l2b));
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "AuD", Liste.kopf(l1a));
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "PFP", Liste.kopf(l1b));
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "AuD", Liste.kopf(l1x));
		assertEquals(NatListeAlgebraPublicTest.CLASS_NAME_Liste + " ist fehlerhaft.", "PFP", Liste.kopf(l1y));
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_kopf + " von leerer Liste muss null sein.", Liste.kopf(l0));
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_rest + " von leerer Liste muss null sein.", Liste.rest(l0));
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_kopf + " von leerer Liste muss null sein.", Liste.kopf(l0x));
		assertNull(NatListeAlgebraPublicTest.METH_NAME_Liste_rest + " von leerer Liste muss null sein.", Liste.rest(l0y));
	}

	// ==================== Algebra.modulo ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__modulo__42_modulo_7_ist_0__und__42_modulo_9_ist_6() {
		assertSame("modulo(n[42], n[7])", n[0], Algebra.modulo(n[42], n[7]));
		assertSame("sub(n[6], modulo(n[42], n[9]))", n[0], Nat.sub(n[6], Algebra.modulo(n[42], n[9])));
		assertSame("sub(modulo(n[42], n[9]), n[6])", n[0], Nat.sub(Algebra.modulo(n[42], n[9]), n[6]));
	}

	// ==================== Algebra.kleiner ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__kleiner__42_kleiner_69_ist_1__und__69_kleiner_42_ist_0() {
		assertSame("sub(n[1], kleiner(n[42], n[69]))", n[0], Nat.sub(n[1], Algebra.kleiner(n[42], n[69])));
		assertSame("sub(kleiner(n[42], n[69]), n[1])", n[0], Nat.sub(Algebra.kleiner(n[42], n[69]), n[1]));
		assertSame("kleiner(n[69], n[42])", n[0], Algebra.kleiner(n[69], n[42]));
	}

	// ==================== Algebra.gleich ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__gleich__3p5_gleich_8__und__8_gleich_3p5() {
		assertSame("sub(n[1], gleich(add(n[3], n[5]), n[8]))", n[0], Nat.sub(n[1], Algebra.gleich(Nat.add(n[3], n[5]), n[8])));
		assertSame("sub(gleich(add(n[3], n[5]), n[8]), n[1])", n[0], Nat.sub(Algebra.gleich(Nat.add(n[3], n[5]), n[8]), n[1]));
		assertSame("sub(n[1], gleich(n[8], add(n[3], n[5])))", n[0], Nat.sub(n[1], Algebra.gleich(n[8], Nat.add(n[3], n[5]))));
		assertSame("sub(gleich(n[8], add(n[3], n[5])), n[1])", n[0], Nat.sub(Algebra.gleich(n[8], Nat.add(n[3], n[5])), n[1]));
	}

	// ==================== Algebra.groesser ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__groesser__42_groesser_69_ist_0__und__69_groesser_42_ist_1() {
		assertSame("groesser(n[42], n[69])", n[0], Algebra.groesser(n[42], n[69]));
		assertSame("sub(n[1], groesser(n[69], n[42]))", n[0], Nat.sub(n[1], Algebra.groesser(n[69], n[42])));
		assertSame("sub(groesser(n[69], n[42]), n[1])", n[0], Nat.sub(Algebra.groesser(n[69], n[42]), n[1]));
	}

	// ==================== Algebra.ggt ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__ggt__42_ggt_105_ist_21__und__105_ggt_42_ist_21() {
		assertSame("sub(n[21], ggt(n[42], n[105]))", n[0], Nat.sub(n[21], Algebra.ggt(n[42], n[105])));
		assertSame("sub(ggt(n[42], n[105]), n[21])", n[0], Nat.sub(Algebra.ggt(n[42], n[105]), n[21]));
		assertSame("sub(n[21], ggt(n[105], n[42]))", n[0], Nat.sub(n[21], Algebra.ggt(n[105], n[42])));
		assertSame("sub(ggt(n[105], n[42]), n[21])", n[0], Nat.sub(Algebra.ggt(n[105], n[42]), n[21]));
	}

	// ==================== Algebra.kgv ====================
	@Test(timeout = 666)
	public void pubTest__Algebra__kgv__21_kgv_15_ist_105__und__15_kgv_21_ist_105() {
		assertSame("sub(n[105], kgv(n[21], n[15]))", n[0], Nat.sub(n[105], Algebra.kgv(n[21], n[15])));
		assertSame("sub(kgv(n[21], n[15]), n[105])", n[0], Nat.sub(Algebra.kgv(n[21], n[15]), n[105]));
		assertSame("sub(n[105], kgv(n[15], n[21]))", n[0], Nat.sub(n[105], Algebra.kgv(n[15], n[21])));
		assertSame("sub(kgv(n[15], n[21]), n[105])", n[0], Nat.sub(Algebra.kgv(n[15], n[21]), n[105]));
	}

	// ==================== pfz ====================
	@Test(timeout = 666)
	public void pubTest__Algebra_pfz() {
		Nat kopf;
		Liste<Nat> pfz = Algebra.pfz(n[360]); // 360 = 2*2*2 * 3*3 * 5
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \">2<*2*2 * 3*3 * 5\"", n[0], Nat.sub(n[2], kopf));
		assertSame("pfz(360) = \">2<*2*2 * 3*3 * 5\"", n[0], Nat.sub(kopf, n[2]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \"2*>2<*2 * 3*3 * 5\"", n[0], Nat.sub(n[2], kopf));
		assertSame("pfz(360) = \"2*>2<*2 * 3*3 * 5\"", n[0], Nat.sub(kopf, n[2]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \"2*2*>2< * 3*3 * 5\"", n[0], Nat.sub(n[2], kopf));
		assertSame("pfz(360) = \"2*2*>2< * 3*3 * 5\"", n[0], Nat.sub(kopf, n[2]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \"2*2*2 * >3<*3 * 5\"", n[0], Nat.sub(n[3], kopf));
		assertSame("pfz(360) = \"2*2*2 * >3<*3 * 5\"", n[0], Nat.sub(kopf, n[3]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \"2*2*2 * 3*>3< * 5\"", n[0], Nat.sub(n[3], kopf));
		assertSame("pfz(360) = \"2*2*2 * 3*>3< * 5\"", n[0], Nat.sub(kopf, n[3]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNotNull(kopf);
		assertSame("pfz(360) = \"2*2*2 * 3*3 * 5\"", n[0], Nat.sub(n[5], kopf));
		assertSame("pfz(360) = \"2*2*2 * 3*3 * >5<\"", n[0], Nat.sub(kopf, n[5]));
		pfz = Liste.rest(pfz);
		assertNotNull(pfz);
		kopf = Liste.kopf(pfz);
		assertNull(kopf);
		pfz = Liste.rest(pfz);
		assertNull(pfz);
	}
}