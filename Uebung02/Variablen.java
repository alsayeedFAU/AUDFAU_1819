public class Variablen {
	// TODO: declare a constant named "FOO_BAR" with a value of -123.456 *
	// 10^(-89)
	static final double FOO_BAR = -123.456 * Math.pow(10, -89);

	// TODO: declare an enumeration named "Months" containing the
	// english names in CAPITAL_LETTERS (!) of all the 12 months of the year in
	// correct chronological order
	enum Months {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}

	public static char[] someCharacters() {
		// TODO: declare, fill and return an 1-dimensional
		// array containing the ASCII-letters A to Z (capital letters)
		char[] tmp = new char[26];
		for (char c = 'A'; c <= 'Z'; c++) {
			tmp[c - 'A'] = c;
		}
		return tmp;
	}

	public static char[][] someMoreCharacters() {
		// TODO: declare, fill and return a 2-dimensional array containing
		// 1) the values (!) 0 to 25 in the first row,
		// 2) the ASCII-letters A to Z (capital letters) in the second row,
		// 3) the ASCII-characters (!) 0 to 9, then 0 to 9 again and finally 0
		// to 5 in the third row
		// 4) the ASCII-letters a to z (non-capital letters) in the fourth row,
		char[][] tmp = new char[4][26];
		for (int i = 0, j = 65, k = 97, l = 48; i < tmp[0].length; i++, j++, k++, l = i % 10 + 48) {
			tmp[0][i] = (char) i;
			tmp[1][i] = (char) j;
			tmp[2][i] = (char) l;
			tmp[3][i] = (char) k;
		}
		return tmp;
	}

	public static int[][][] theCube() {
		// TODO: declare, fill and return a 3-dimensional array
		// of size 3x3x3 (try to imagine a
		// http://en.wikipedia.org/wiki/Rubik's_Cube)
		// containing only integer numbers such that
		// the value at [x][y][z] == (x+1)*100+(y+1)*10+(z+1)
		// (e.g. cube[2][1][0] == 321)
		int tmp[][][] = new int[3][3][3];
		for (int i = 0; i < tmp.length; i++)
			for (int j = 0; j < tmp[1].length; j++)
				for (int k = 0; k < tmp[0][1].length; k++)
					tmp[i][j][k] = ((i + 1) * 100) + ((j + 1) * 10) + (k + 1);
		return tmp;
	}
}