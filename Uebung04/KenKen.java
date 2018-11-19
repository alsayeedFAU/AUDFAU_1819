import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class KenKen {

	public static int checkIntegrity(int[][][] is) {

		// kenken is non-null and contains at least one entry, i.e. a "row" in
		// the first dimension representing a partition
		if (is == null || is.length == 0) {
			return 1;
		}
		// each row (partition) contains a non-null 2D-array of type int[][]
		// with at least two sub-entries of type int[]
		
		
		for (int[][] is1 : is) {
			if (is1 == null || is1.length < 2) {
				return 1;
			}
			// each sub-entry in a row (partition) is non-null and has length 2
			for (int i = 0; i < is1.length; i++) {
				if (is1[i] == null || is1[i].length != 2) {
					return 3;
				}

				if (i == 0) {
					// the first sub-entry in a row is a pair {result,
					// operation}, where result is an integer number and
					// operation is a char(acter)
					if (is1[0][1] < 0 || is1[0][1] > 127) {
						return 4;
					} else if (!(is1[0][1] == '+' || is1[0][1] == '-' || is1[0][1] == '*' || is1[0][1] == '/'
							|| is1[0][1] == ' ')) {
						// operation must be one of '+', '-', '/', '*', ' '
						// (whitespace)
						return 5;
					}

					// if operation is ' ' (whitespace), then exactly one
					// sub-entry must follow the {result, operation}-pair
					if (is1[i][1] == ' ') {
						if (is1.length > 2) {
							return 7;
						}
					}

					// if operation is '-' or '/', then exactly two sub-entries
					// must follow the {result, operation}-pair
					if (is1[i][1] == '-' || is1[i][1] == '/') {
						if (is1.length != 3) {
							return 8;
						}
					}

					// if operation is '+' or '*', then at least two sub-entries
					// must follow the {result, operation}-pair
					// must follow the {result, operation}-pair
					if (is1[i][1] == '+' || is1[i][1] == '*') {
						if (is1.length < 3) {
							return 9;
						}
					}

				} else {
					// all other sub-entries (except for the first one) in a row
					// must be pairs of non-negative int-numbers
					if (is1[i].length != 2 || is1[i][0] < 0 || is1[i][1] < 0) {
						return 6;
					}
				}

			}

		}

		return 0;
	}

	public static int checkValidity(int[][][] is) {
		if (checkIntegrity(is) != 0) {
			return 1;
		}

		HashSet<String> set = new HashSet<>();
		for (int[][] is1 : is) {
			for (int i = 1; i < is1.length; i++) {
				if (set.contains("" + is1[i][0] + is1[i][1])) {
					return 2;
				} else {
					set.add("" + is1[i][0] + is1[i][1]);
				}

			}
		}
		return 0;

	}

	private static int[] kenkenSize(int[][][] kenken) {
		int maxX = 0;
		int maxY = 0;

		for (int i = 0; i < kenken.length; i++) {
			for (int j = 1; j < kenken[i].length; j++) {
				if (kenken[i][j][0] > maxX) {
					maxX = kenken[i][j][0];
				}
				if (kenken[i][j][1] > maxY) {
					maxY = kenken[i][j][1];
				}
			}
		}

		return new int[] { maxX + 1, maxY + 1 };
	}

	public static int[][] solve(int[][][] kenken) {

		if (checkIntegrity(kenken) != 0) {
			return null;
		}
		return solveHelper(kenken, new int[kenkenSize(kenken)[0]][kenkenSize(kenken)[1]], 0);
	}

	private static int[][] solveHelper(int[][][] kenken, int[][] solution, int index) {

		// completed?
		if (index == solution.length * solution.length) {
			return solution;
		}

		int[] tmp = validatedNumbers(kenken, solution, index % solution.length, index / solution.length);

		// iterate possible solutions
		for (int i = 0; i < tmp.length; i++) {
			solution[index / solution.length][index % solution.length] = tmp[i];
			if (solveHelper(kenken, solution, index + 1) != null) {
				// if solution found, return
				return solution;
			}
			// else turn back
			solution[index / solution.length][index % solution.length] = 0;
		}
		return null;
	}

	private static int[] validatedNumbers(int[][][] kenken, int[][] solution, int x, int y) {
		List<Integer> validNum = new ArrayList<>();

		for (int i = 1; i <= solution.length; i++) {
			solution[y][x] = i;
			if (validate(kenken, solution)) {
				validNum.add(i);
			}
			solution[y][x] = 0;
		}

		int[] tmp = new int[validNum.size()];
		for (int i = 0; i < validNum.size(); i++) {
			tmp[i] = validNum.get(i);
		}

		return tmp;
	}

	private static boolean validate(int[][][] kenken, int[][] solution) {
		for (int y = 0; y < solution.length; y++) {
			for (int x = 0; x < solution[y].length; x++) {
				boolean[] checkX = new boolean[solution.length];
				boolean[] checkY = new boolean[solution.length];

				for (int t = 0; t < solution.length; t++) {

					if (solution[t][x] != 0) {
						if (checkY[solution[t][x] - 1] == true) {
							return false;
						} else {
							checkY[solution[t][x] - 1] = true;
						}
					}

					if (solution[y][t] != 0) {
						if (checkX[solution[y][t] - 1] == true) {
							return false;
						} else {
							checkX[solution[y][t] - 1] = true;
						}
					}

				}

			}
		}

		for (int[][] partition : kenken) {
			boolean solved = true;
			for (int i = 1; i < partition.length; i++) {
				if (solution[partition[i][0]][partition[i][1]] == 0) {
					solved = false;
				}
			}
			if (solved && !solutionCheck(partition, solution)) {
				return false;
			}
		}

		return true;

	}

	private static boolean solutionCheck(int[][] partition, int[][] solution) {
		int tmp;
		switch (partition[0][1]) {
		case ' ':
			if (solution[partition[1][0]][partition[1][1]] != partition[0][0]) {
				return false;
			}

			break;

		case '+':
			tmp = 0;
			for (int i = 1; i < partition.length; i++) {
				tmp += solution[partition[i][0]][partition[i][1]];
			}

			if (tmp != partition[0][0]) {
				return false;
			}

			break;

		case '*':
			tmp = 1;
			for (int i = 1; i < partition.length; i++) {
				tmp *= solution[partition[i][0]][partition[i][1]];
			}

			if (tmp != partition[0][0]) {
				return false;
			}

			break;

		case '-':
			if (solution[partition[1][0]][partition[1][1]] > solution[partition[2][0]][partition[2][1]]) {
				if (solution[partition[1][0]][partition[1][1]]
						- solution[partition[2][0]][partition[2][1]] != partition[0][0]) {
					return false;
				}
			} else {
				if (solution[partition[2][0]][partition[2][1]]
						- solution[partition[1][0]][partition[1][1]] != partition[0][0]) {
					return false;
				}
			}
			break;

		case '/':
			if (solution[partition[1][0]][partition[1][1]] > solution[partition[2][0]][partition[2][1]]) {
				if (solution[partition[1][0]][partition[1][1]]
						/ solution[partition[2][0]][partition[2][1]] != partition[0][0]) {
					return false;
				}
			} else {
				if (solution[partition[2][0]][partition[2][1]]
						/ solution[partition[1][0]][partition[1][1]] != partition[0][0]) {
					return false;
				}
			}

			break;
		default:
			break;
		}
		return true;
	}
}
