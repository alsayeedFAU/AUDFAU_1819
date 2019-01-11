import java.util.Comparator;

public class UnimodaleSuche<T>{

	public static <T> T suche(UnimodaleListe<T> Liste, int start, int end, Comparator<T> vergleich) {
		
		if (Liste == null || start < 0 || end < 0 || (end - start) < 0) {
			return null;
		}

		int mitteListe = start + ((end - start) / 2);
		T naechtest = Liste.hole(mitteListe + 1);
		T mittleres = Liste.hole(mitteListe);

		
		if ((end - start) > 2) {
			if (vergleich.compare(mittleres, naechtest) < 0) {
				return suche(Liste, mitteListe + 1, end, vergleich);
			}else{
				return suche(Liste, start, mitteListe, vergleich);
			}
		}else{
			if (vergleich.compare(mittleres, naechtest) < 0) {
				return naechtest;
			}
			if (vergleich.compare(Liste.hole(mitteListe - 1), mittleres) > 0) {
				return Liste.hole(mitteListe - 1);
			}
			
			
		}
		return mittleres;
		
		
		
	}

	public static <T extends Comparable<T>> T suche(UnimodaleListe<T> Liste, int start, int end) {

		if (Liste == null || start < 0 || end < 0 || (end - start) < 0) {
			return null;
		}

		int mitteListe = start + ((end - start) / 2);
		T naechtest = Liste.hole(mitteListe + 1);
		T mittleres = Liste.hole(mitteListe);

		if ((end - start) > 2) {
			if (mittleres.compareTo(naechtest) > 0) {
				return suche(Liste, start, mitteListe);
			} else if (mittleres.compareTo(naechtest) < 0) {
				return suche(Liste, mitteListe + 1, end);
			}
		} else {
			if (mittleres.compareTo(naechtest) < 0) {
				return naechtest;
			}
			if (Liste.hole(mitteListe - 1).compareTo(mittleres) > 0) {
				return Liste.hole(mitteListe - 1);
			}

		}
		return mittleres;
	}



}