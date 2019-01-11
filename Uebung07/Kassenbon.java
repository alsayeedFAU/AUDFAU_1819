public class Kassenbon {
	/**
	 * Maximal erlaubte Laenge fuer die Bezeichnung eines Produkts.
	 */
	protected static final int MAX_PRODUKT_LAENGE = 20;
	protected final String[] produkte;
	protected final Integer[] preise;
	private int anzahl;

	/**
	 * Konstruktor fuer neue und zunaechst leere Kassenbons.
	 * 
	 * @param max
	 *            maximale Anzahl erlaubter Produkte pro Einkauf
	 */
	public Kassenbon(int max) {
		produkte = new String[max];
		preise = new Integer[max];
	}

	/**
	 * Fuegt ein {@code produkt} samt {@code preis} zum aktuellen Kassenbon
	 * hinzu.
	 * 
	 * @param produkt
	 *            das hinzuzufuegende Produkt
	 * @param preis
	 *            der Preis des hinzuzufuegendes Produkts (aus technischen
	 *            Gruenden erfolgt die Uerbergabe zunaechst als Zeichenkette)
	 */
	public void speichern(String produkt, String preis) {
		if (produkt.length() > MAX_PRODUKT_LAENGE) {
			return;
		}
		produkte[anzahl] = produkt;
		preise[anzahl] = Integer.parseInt(preis);
		anzahl++;
	}

	/**
	 * Gibt die Bezeichnung des zuletzt hinzugefuegten Produktes zurueck.
	 * 
	 * @return Bezeichnung des zuletzt hinzugefuegten Produktes
	 */
	public String letztesProdukt() {
		return produkte[anzahl - 1];
	}

	/**
	 * Berechnet die Gesamtsumme der Preise aller Produkte auf diesem Kassenbon.
	 * 
	 * @return Gesamtsumme der Preise aller Produkte auf diesem Kassenbon
	 */
	public int summe() {
		int summe = 0;
		for (int i = 0; i < preise.length; i++) {
			summe += preise[i];
		}
		return summe;
	}

	/**
	 * Berechnet den durchschnittlichen Preis (Mittelwert) der Produkte auf
	 * diesem Kassenbon.
	 * 
	 * @return Durchschnittspreis (Mittelwert) der Produkte auf diesem Kassenbon
	 */
	public int mittelwert() {
		int summe = 0;
		for (int i = 0; i < anzahl; i++) {
			summe += preise[i];
		}
		return summe / anzahl;
	}
}