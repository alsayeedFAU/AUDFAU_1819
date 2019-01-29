public class Kryptogramm {

	public static char[] extrahiereZeichen(String[] operanden) {
		String s = "";
		for (int i = 0; i < operanden.length; i++) {
			for (int j = 0; j < operanden[i].length(); j++) {
				if (!s.contains("" + operanden[i].charAt(j))) {
					s += operanden[i].charAt(j);
				}
			}
		}
		return s.toCharArray();
	}

	public static long werteAus(String operand, char[][] zuordnung) {
		String s = operand;
		for (int i = 0; i < zuordnung.length; i++) {
			s = s.replace(zuordnung[i][0], zuordnung[i][1]);

		}
		long tmp = 0;
		for (int i = 0; i < operand.length(); i++) {
			tmp *= 10;
			tmp += s.charAt(i);
		}
		return tmp;
	}

	public static char[][] loese(String[] operanden) {
		char[] ziffern = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		char[] symbol = extrahiereZeichen(operanden);
		return helper(operanden, symbol, 0, ziffern, new char[symbol.length][]);
	}

	private static char[][] helper(String[] operanden, char[] symbol, int indexS, char[] ziffern, char[][] solution) {
		char[][] tmp = null;
		if (indexS >= symbol.length) {
			if (check(operanden, solution)) {
				return solution;
			}
		} else {

			for (int i = 0; i < ziffern.length; i++) {
				if (ziffern[i] != 100) {
					char[] z1 = new char[ziffern.length];
					for (int c = 0; c < ziffern.length; c++) {
						z1[c] = ziffern[c];
					}
					solution[indexS] = new char[] { symbol[indexS], ziffern[i] };
					z1[i] = 100;
					tmp = helper(operanden, symbol, indexS + 1, z1, solution);
					if (tmp != null) {
						return tmp;
					}
				}

			}
		}
		return tmp;
	}

	private static boolean check(String[] operanden, char[][] zuordnung) {
		// höchstwertige stelle != 0
		for (String s : operanden) {
			for (char[] c : zuordnung) {
				if (c[0] == s.charAt(0)) {
					if (c[1] == 0) {
						return false;
					}
				}
			}
		}

		// mathematisch korrekt
		long t = 0;
		for (int i = 0; i < operanden.length - 1; i++) {
			t += werteAus(operanden[i], zuordnung);
		}

		if (werteAus(operanden[operanden.length - 1], zuordnung) != t) {
			return false;
		}

		return true;
	}

}
