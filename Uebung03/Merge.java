
public class Merge {

	public static long[] merge(long[] ns, int i, Merger m) {
		m.merge();
		long[] tmp;

		if (i < 0) {
			return merge(ns, 0, m);
		} else if (i > ns.length - 1) {
			return new long[] {};
		} else {

			if (i < ns.length - 1) {
				if (ns[i] == ns[i + 1]) {
					tmp = merge(ns, i + 1, m);

					tmp[0] *= ns[i];

					return tmp;
				} else {
					tmp = merge(ns, i + 1, m);

					return m.prepend(ns[i], tmp);
				}

			} else {
				tmp = merge(ns, i + 1, m);

				return m.prepend(ns[i], tmp);
			}
		}
	}

}
