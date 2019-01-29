
public class GaussschesEliminationsverfahren {

	public static void pivotisiere(long[][] matrix, long[] vektor, int position) {
		long max = 0L;
		int merker = 0;
	

		for (int i = position; i < matrix[1].length; i++) {
			if ((Math.abs(matrix[i][position])) > max) {
				max = Math.abs(matrix[i][position]);
				merker = i;

			}
		}
		
		long[] t = matrix[merker];
		matrix[merker] = matrix[position];
		matrix[position] = t;
		
		
		long tausch = vektor[position];
		vektor[position] = vektor[merker];
		vektor[merker] = tausch;

	}

	public static void eliminiere(long[][] matrix, long[] vektor, int position) {

		for (int i = matrix[1].length; i >= (matrix[1].length - 1) + position; i--) {
			long pA = matrix[position][position];
			long pB = matrix[i - 1][position];
			for (int j = position; j < matrix.length; j++) {
				matrix[i - 1][j] = pB * matrix[position][j] - pA * matrix[i - 1][j];
			}
			vektor[i - 1] = pB * vektor[position] - pA * vektor[i - 1];
		}

	}

	public static double[] loese(long[][] matrix, long[] vektor) {
		double result[] = new double[vektor.length];
		for (int i = vektor.length; i > 0; i--) {
			double teiler = 0;
			for (int j = 0; j < vektor.length; j++) {
				teiler += (matrix[i - 1][j] * result[j]);
			}
			result[i - 1] = (vektor[i - 1] - teiler) / matrix[i - 1][i - 1];

		}
		return result;
	}

}
