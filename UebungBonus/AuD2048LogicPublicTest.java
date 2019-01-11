import static org.junit.Assert.*;
import org.junit.*;

public class AuD2048LogicPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_AuD2048LogicNormalGame = "AuD2048LogicNormalGame";
	protected static final String EX_NAME_AuD2048LogicEleven = "AuD2048LogicEleven";
	protected static final String EX_NAME_AuD2048LogicFibonacci = "AuD2048LogicFibonacci";

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_AuD2048LogicNormalGame_roughlyCheckInterface() {
		pubTest_checkInterface(0);
	}

	@Test(timeout = 666)
	public void pubTest_AuD2048LogicEleven_roughlyCheckInterface() {
		pubTest_checkInterface(1);
	}

	@Test(timeout = 666)
	public void pubTest_AuD2048LogicFibonacci_roughlyCheckInterface() {
		pubTest_checkInterface(2);
	}

	// ========== HELPER ==========
	private static final void pubTest_checkInterface(int logicId) {
		AuD2048Logic logic = logicId == 0 ? new AuD2048LogicNormalGame(4, 4)
				: logicId == 1 ? new AuD2048LogicEleven(4, 4) : new AuD2048LogicFibonacci(4, 4);
		String msg = "Start new game of " + logic + "(4,4) and check result of ";
		logic.startNewGame();
		assertNotNull(msg + "getGameBoard.", logic.getGameBoard());
		assertEquals(msg + "getScore.", 0, logic.getScore());
		assertFalse(msg + "isGameOver.", logic.isGameOver());
		assertFalse(msg + "hasWinner.", logic.hasWinner());
		logic.move(Direction.DOWN);
	}
}