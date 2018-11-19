// PLEASE KEEP IN MIND:
// Place your new/additional code immediately below the line marked "// TODO:"
// and/or replace the expression after the return statement if necessary.
// Do not change any given code above or below those lines!
// For higher computational precision perform all multiplications before divisions!

public class Geometrie {
	// computes and returns the circumference of a
	// regular polygon with n edges of length a
	public static double umfangRegelmaessigesVieleck(int n, double a) {
		// TODO:
		return (a <= 0) || (n <= 0) ? 0 : a*n;
	}

	// computes and returns the circumference of a circle with radius r
	// (hint: use Math.PI for a precise value of PI)
	public static double umfangKreis(double r) {
		// TODO:
		return (r <= 0) ? 0 : Math.PI * 2 * r;
	}

	// computes and returns the surface area of a trapezium (aka trapezoid)
	// with base edge a, opposite edge c, and height h
	public static double flaecheTrapez(double a, double c, double h) {
		// TODO:
		return (a<=0)||(c<=0)||(h<=0) ? 0 : ((a+c)*h)/2;
	}

	// computes and returns the volume of a square pyramid
	// with base length a and height h
	public static double volumenPyramide(double a, double h) {
		// TODO:
		return (a <= 0) || (h <= 0) ? 0 : (a*a*h)/3;
	}

	// computes and returns the surface are of a square pyramid
	// with base length a and height h
	// (hint: use Math.sqrt(x) to compute the square root of x)
	public static double flaechePyramide(double a, double h) {
		// TODO:
		return (a<=0)||(h<=0) ? 0 : a*a+a*Math.sqrt((4*(h*h))+(a*a));
	}

	// computes and returns the volume of a sphere with radius r
	public static double volumenKugel(double r) {
		// TODO:
		return (r <= 0) ? 0 : (4*Math.pow(r,3)*Math.PI)/3;
	}

	// computes and returns the surface area of a closed irregular polygon
	// with consecutive connected nodes at (xi, yi)
	// (hint: use Math.abs(x) to compute the absolute value of x, i.e. |x|)
	public static double flaecheVieleck8(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double x5, double y5, double x6, double y6, double x7, double y7) {
		// TODO:
		return Math.abs((((y0+y1)*(x0-x1))+((y1+y2)*(x1-x2))+((y2+y3)*(x2-x3))+((y3+y4)*(x3-x4))+((y4+y5)*(x4-x5))+((y5+y6)*(x5-x6))+((y6+y7)*(x6-x7)))/2);
	}

	// classifies a triangle by the given sides a, b and c
	// and returns one of the values 0 to 3 as follows:
	// 0 : if a,b,c are illegal, i.e. do not form a valid triangle
	// 1 : if triangle is scalene
	// 2 : if triangle is isosceles
	// 3 : if triangle is equilateral
	public static int typDesDreiecks(long a, long b, long c) {
		// TODO:
		if(a == b && b == c){
			return 3;
		}
		
		if(a == b || b == c || c == a){
			return 2;
		}
		
		if( a > 0 && b > 0 && c > 0){
			return 1;
		}
		
		
		return 0;
	}
}