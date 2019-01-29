import static org.junit.Assert.*;
import org.junit.*;

public class KassenbonPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Ausnahmen = "Ausnahmen ausloesen";
	protected static final String EX_NAME_Verbesserungen = "Ausnahmen verhindern";

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__KassenbonAusnahmen__mindestensEineAusnahme() {
		boolean fail = false;
		try {
			KassenbonAusnahmen.ausnahme1();
			KassenbonAusnahmen.ausnahme2();
			KassenbonAusnahmen.ausnahme3();
			KassenbonAusnahmen.ausnahme4();
			KassenbonAusnahmen.ausnahme5();
			KassenbonAusnahmen.ausnahme6();
			KassenbonAusnahmen.ausnahme7();
			KassenbonAusnahmen.ausnahme8();
			fail = true;
		} catch (Throwable t) {
		}
		if (fail) {
			fail("Keine Ausnahme provoziert.");
		}
	}

	@Test(timeout = 666)
	public void pubTest__KassenbonVerbessert__ProduktNameZuLang() {
		boolean fail = false;
		try {
			Kassenbon kb = new KassenbonVerbessert(2);
			kb.speichern("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "12");
			fail = true;
		} catch (Exception e) {
			assertSame(IllegalArgumentException.class, e.getClass());
		}
		if (fail) {
			fail("Keine Ausnahme provoziert.");
		}
	}
}