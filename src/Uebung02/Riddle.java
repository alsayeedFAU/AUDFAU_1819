
public class Riddle {

	public static boolean a0(boolean stan, boolean cartman, boolean kyle) {
		// TODO Auto-generated method stub
		return !(stan || kyle);
	}

	public static boolean a1(boolean stan, boolean cartman, boolean kyle) {
		// TODO Auto-generated method stub
		return !(cartman || stan);
	}

	public static boolean a2(boolean stan, boolean cartman, boolean kyle) {
		// TODO Auto-generated method stub
		return ((stan ^ cartman) ^ kyle);
	}

	public static boolean a3(boolean stan, boolean cartman, boolean kyle) {
		// TODO Auto-generated method stub
		return ((((stan) && !(cartman || kyle)) ^ (cartman) && !(stan || kyle)) ^ ((kyle) && !(stan || cartman)));
	}

	public static boolean eval(boolean stan, boolean cartman, boolean kyle) {
		// TODO Auto-generated method stub
		if (((stan && !(cartman || kyle)) ^ ((cartman) && !(stan || kyle)) ^ (kyle && !(stan || cartman))))
			return true;
		else
			return false;
	}

	public static boolean implies(boolean a, boolean b) {
		// TODO Auto-generated method stub
		return !a || b;
	}

	public static int checkRiddle() {
		if (eval(true, false, false))
			return 1;
		else if (eval(false, false, true))
			return 2;
		else if (eval(false, true, false))
			return 0;

		return -1;
	}

}
