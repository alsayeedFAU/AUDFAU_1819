import static org.junit.Assert.*;
import org.junit.*;

public class MyApplicationPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_UML2JAVA = "UML2JAVA";

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_smokeTest() {
		Controller c = null;
		MyApplication ma = null;
		DataStore ds = null;
		Subject s = null;
		DataStoreObserver dso = null;
		Observer o = null;
		assertTrue(c == null && ma == null && ds == null && s == null && dso == null && o == null);
		ViewTable vt = null;
		ViewTableLandscape vtl = null;
		ViewTableNormal vtn = null;
		ViewSum vs = null;
		ViewSumColumn vsc = null;
		ViewSumRow vsr = null;
		assertTrue(vt == null && vtl == null && vtn == null && vs == null && vsc == null && vsr == null);
	}
}