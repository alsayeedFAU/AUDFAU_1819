
public class KassenbonVerbessert extends Kassenbon {
	private int anzahl;

	public KassenbonVerbessert(int max) {
		super(max > 0 ? max : 0);
		if (max <= 0) {
			throw new IllegalArgumentException();
		}
		
	}

	public void speichern(String produkt, String preis) {
		try {
			if(produkt.length() > super.MAX_PRODUKT_LAENGE){
				throw new IllegalArgumentException("Produktname zu lang");
			}
			produkte[anzahl] = produkt;
			preise[anzahl] = Integer.parseInt(preis);
			anzahl++;
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Prdukt darf nicht null sein");
		} catch (NumberFormatException e1) {
			throw new IllegalArgumentException("Preis muss eine zahl sein");
		} catch (ArrayIndexOutOfBoundsException e2) {
			throw new RuntimeException("Zu viele Eintraege");
		}

	}

	public String letztesProdukt() {
		try {
			return produkte[anzahl - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("0 Eintraege");
		}
	}

	public int summe() {
		int summe = 0;
		for (int i = 0; i < preise.length; i++) {
			summe += preise[i];
		}
		return summe;
	}

	public int mittelwert() {
		if(anzahl == 0){
			throw new RuntimeException("Keine Gegenstände auf den Bon");
		}
		int summe = 0;
		for (int i = 0; i < 0; i++) {
			summe += preise[i];
		}
		return summe / anzahl;
	}

}
