
public class Hanoi {

	public static String solve(int n, String start, String spare, String target, HanoiRecCheck hrc) {
		hrc.checkRecHanoi();
		String tmp = "";
		if (n == 0) {
			return " ";
		}

		if (n > 0) {
			tmp += solve(n - 1, start, target, spare, hrc);
			tmp += "" + start + "->" + target;
			tmp += solve(n - 1, spare, start, target, hrc);
		}
		return tmp;

	}

}
