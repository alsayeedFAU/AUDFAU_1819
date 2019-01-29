import java.awt.Dimension;

public class Vektor {
	protected double[] komponenten;

	public Vektor(int dimension) {
		komponenten = new double[dimension];
	}

	public Vektor(double[] komponenten) {
		this.komponenten = new double[komponenten.length];
		for (int i = 0; i < komponenten.length; i++) {
			this.komponenten[i] = komponenten[i];
		}
	}

	public int dimension() {
		return komponenten == null ? 0 : komponenten.length;
	}

	public double komponente(int i) {
		return this.komponenten[i];
	}

	public void initialisiere(double[] komponenten) {
		if (this.komponenten != null && this.komponenten.length == komponenten.length) {
			for (int i = 0; i < komponenten.length; i++) {
				this.komponenten[i] = komponenten[i];
			}
		}
	}

	public void skalarMultiplikation(double m) {
		for (int i = 0; komponenten != null && i < komponenten.length; i++) {
			komponenten[i] *= m;
		}
	}

	public double skalarProdukt(Vektor v) {
		if (this.dimension() != v.dimension()) {
			return Double.NaN;
		}

		double tmp = 0;

		for (int i = 0; i < dimension(); i++) {
			tmp += (this.komponente(i) * v.komponente(i));
		}
		return tmp;
	}

	public static double skalarProdukt(Vektor v1, Vektor v2) {
		return v1.skalarProdukt(v2);
	}

	public void addition(Vektor v) {
		if (this.dimension() == v.dimension()) {
			for (int i = 0; i < this.dimension(); i++) {
				this.komponenten[i] = this.komponente(i) + v.komponente(i);
			}
		}

	}

	public static Vektor addition(Vektor v1, Vektor v2) {
		if (v1.dimension() == v2.dimension()) {
			Vektor v = new Vektor(v1.dimension());
			for (int i = 0; i < v.dimension(); i++) {
				v.komponenten[i] = v1.komponente(i) + v2.komponente(i);
			}
			return v;

		}
		return null;
	}

	public double norm() {
		return Math.sqrt(skalarProdukt(this));
	}

	public void normiere() {
		double n = norm();
		
		for(int i = 0; i < this.dimension(); i++) {
			komponenten[i] = komponente(i) / n;
		}

	}

}
