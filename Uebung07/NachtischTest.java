
public class NachtischTest {

	public static void main(String[] args) {
		Verdaulich vos = new ObstSmoothie();

		System.out.println(vos.menge);

		System.out.println(vos.verdauen(1));

		Obst oos = new ObstSmoothie();

		System.out.println(oos.schaelen("Kiwi"));

		System.out.println(oos.schaelen(Integer.MAX_VALUE + 123));

		System.out.println(oos.bananen);

		System.out.println(oos.zubereiten(1));

		System.out.println(oos.vegan);

		System.out.println(oos.menge);

		System.out.println(oos.verdauen(2));

		System.out.println("-------");
		ObstSmoothie osos = new ObstSmoothie();
		System.out.println(osos.bananen);

		System.out.println(osos.zubereiten(1));

		System.out.println(osos.schaelen("Guacamole"));

		System.out.println(osos.menge);

		Trinkbar tbw = new Branntwein();

		System.out.println(tbw.verdauen(3));

		Trinkbar tos = new ObstSmoothie();

		System.out.println(tos.menge);
		System.out.println(tos.verdauen(4));

	}
}

interface Verdaulich {
	String menge = "zu viel";

	String verdauen(int anzahl);
}

interface Trinkbar {
	long menge = 7L;

	String verdauen(long drink);
}

class Branntwein implements Trinkbar {
	String menge = "nicht genug";

	@Override
	public String verdauen(long flaschen) {
		return "Leberzirrhose";
	}

	public String verdauen(int flaschen) {
		return "tut gut";
	}

}

abstract class Obst implements Verdaulich {
	int bananen = 321;
	protected boolean vegan = true;

	public String verdauen(int menge) {
		return Integer.toString(bananen);
	}

	public static String zubereiten(long bananen) {
		return "ABC";
	}

	public static String schaelen(int bananen) {
		return "Schale";
	}

	public String schaelen(String adv) {
		return "Guacamole";
	}

}

class ObstSmoothie extends Obst implements Trinkbar{
	static String menge = "1 Teller";
	String bananan = "Krumme Dinger";
	protected boolean vegan = false;

	@Override
	public String verdauen(long menge) {
		return Long.toString(menge);
	}
	
	public static String zubereiten(long bananen) {
		return "XYZ";
	}

	public static String schaelen(long bananen) {
		return "Paradiesfeigen";
	}

	public String schaelen(String adv) {
		return "Alligatorbirnen";
	}

}
