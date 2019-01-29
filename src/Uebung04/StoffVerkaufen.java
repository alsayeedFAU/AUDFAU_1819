public class StoffVerkaufen {

	public static long verkaufenNaive(Polizei p, int stoff, long[] preise) {
		p.kontrolle();
		if (stoff == 0) {
			return 0;
		}
		long tmp = 0;

		for (int i = 1; i <= preise.length; i++) {
			if (stoff - i >= 0) {
				long t = 0;
				if ((t = preise[i - 1] + verkaufenNaive(p, stoff - i, preise)) > tmp) {
					tmp = t;
				}
			}
		}
		return tmp;
	}

	public static long verkaufenDP(Polizei p, int stoff, long[] preise) {
		p.kontrolle();
		long[] bestPreise = new long[stoff];
		bestPreise[0] = preise[0];

		for (int i = 2; i <= stoff; i++) {
			for (int j = i - 1; j >= (i % 2 == 0 ? (i / 2) : ((i + 1) / 2)); j--) {
				if (bestPreise[i - 1] < bestPreise[j - 1] + bestPreise[i - j - 1]) {
					bestPreise[i - 1] = bestPreise[j - 1] + bestPreise[i - j - 1];
				}
			}

			if (i < preise.length && bestPreise[i - 1] < preise[i - 1]) {
				bestPreise[i - 1] = preise[i - 1];
			}
		}

		long result = 0;
		for (int i = bestPreise.length; stoff > 0; i--) {
			if (stoff >= i) {
				long l = stoff / i;
				stoff -= i * l;
				result += bestPreise[i - 1] * l;
			}
		}

		return bestPreise[bestPreise.length - 1];
	}

}
