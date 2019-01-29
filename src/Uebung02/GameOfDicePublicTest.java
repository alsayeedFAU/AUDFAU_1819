import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GameOfDicePublicTest {
	// ========== SYSTEM ==========

	// ========== TEST DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__sheet_and_random() {
		int len, d1, s1, d2, s2;
		String[] ds;
		for (int pass = 0; pass < 42; pass++) {
			assertEquals("Player 1 should win this.", 1, GameOfDice.game(new String[] { "4", "6", "1", "3" }));
			assertEquals("Player 2 should win this.", 2, GameOfDice.game(new String[] { "2", "6", "3", "5", "3", "4" }));
			assertEquals("Nobody should win this (draw).", 0, GameOfDice.game(new String[] { "3", "5", "4", "4" }));
			// --------------------
			len = 2 * RND.nextInt(42) + 1;
			ds = new String[len];
			for (int i = 0; i < len; i++) {
				ds[i] = String.valueOf(1 + RND.nextInt(6));
			}
			assertEquals("Should be illegal input (odd number of dices: " + ds.length + ").", -1, GameOfDice.game(ds));
			// --------------------
			len = 2 + 2 * RND.nextInt(42);
			ds = new String[len];
			for (int i = 0; i < len; i++) {
				ds[i] = String.valueOf(7 + RND.nextInt(6));
			}
			assertEquals("Should be illegal input (illegal dice value: > 6).", -1, GameOfDice.game(ds));
			// --------------------
			len = RND.nextInt(42);
			ds = new String[2 * len];
			s1 = s2 = 0;
			for (int i = 0; i < len; i++) {
				s1 += d1 = 1 + RND.nextInt(6);
				ds[i] = String.valueOf(d1);
				s2 += d2 = 1 + RND.nextInt(6);
				ds[len + i] = String.valueOf(d2);
			}
			assertEquals((s1 == s2 ? "Nobody" : s1 > s2 ? "Player 1" : "Player 2") + " should win this.", s1 == s2 ? 0 : s1 > s2 ? 1 : 2, GameOfDice.game(ds));
		}
	}

	// ========== Play the game: ==========
	// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
	// to run on command line: java -cp .:/usr/share/java/junit4.jar $(ls * | grep PublicTest.class | sed s/.class//)
	public static void main(String args[]) {
		int e = GameOfDice.game(args);
		if (e == -1) {
			System.out.println("Illegal Input");
		} else if (e == 0) {
			System.out.println("Draw");
		} else if (e == 1) {
			System.out.println("Player 1 won");
		} else if (e == 2) {
			System.out.println("Player 2 won");
		} else {
			System.out.println("Game Error");
		}
	}
}