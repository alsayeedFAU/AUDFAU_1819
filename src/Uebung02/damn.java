
public class damn {
	static int x = 20;
	static int y = 2;
	static double z = 0.815;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean a = (y > 1) && (5 != 4) || (x > -4);
		int b = +x++ + ++x - -x-- - --x;
		double c = -2;
		c = c * z;
		int d = x | 5;
		boolean e = ((x == y) || (x < z * y));
		int f = y << y;
		boolean g = !((x >> 666 < y) & (z++ == x));

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
	}
}
