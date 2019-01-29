public class Liste<T> {
	private T kopf;
	private Liste<T> rest;
	
	
	public Liste() {
		
	}
	
	public static <T> Liste<T> neu() {
		return new Liste<T>();
	}

	public static <T> Liste<T> vorne(Liste<T> l, T t) {
		Liste<T> tmp = neu();
		tmp.rest = l;
		tmp.kopf = t;
		return tmp;
	}

	public static <T> T kopf(Liste<T> l) {
		if (l == null || l.kopf == null) {
			return null;
		}
		return l.kopf;
	}

	public static <T> Liste<T> rest(Liste<T> l) {
		if (l == null || l.rest == null) {
			return null;
		}

		return l.rest;
	}

}
