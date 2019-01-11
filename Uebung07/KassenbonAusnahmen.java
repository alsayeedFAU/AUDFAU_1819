
public class KassenbonAusnahmen {

	public static void ausnahme1() {
		Kassenbon kassenbon = new Kassenbon(-1);
		
	}
	public static void ausnahme2() {
		Kassenbon kassenbon = new Kassenbon(10);
		kassenbon.speichern(null, "0");
		
	}
	public static void ausnahme3() {
		Kassenbon kassenbon = new Kassenbon(10);
		kassenbon.speichern("Abc", null);
		
	}
	public static void ausnahme4() {
		Kassenbon kassenbon = new Kassenbon(10);
		kassenbon.letztesProdukt();
		
	}
	public static void ausnahme5() {
		Kassenbon kassenbon = new Kassenbon(0);
		kassenbon.speichern("ABC", "1");
		
	}
	public static void ausnahme6() {
		Kassenbon kassenbon = new Kassenbon(0);
		kassenbon.mittelwert();
		
	}
	public static void ausnahme7() {
		Kassenbon kassenbon = new Kassenbon(3);
		kassenbon.summe();
		
	}
	public static void ausnahme8() {
		Kassenbon kassenbon = new Kassenbon(2);
		kassenbon.speichern("QWERtZUIOPASDFGHJKLYXCVBNMWERTZ", "123");
		
	}

}
