
public class Algebra {

	public static Nat modulo(Nat x, Nat y) {
		return x == Nat.zero() ? Nat.zero() : y == Nat.zero() ? Nat.zero() : Nat.sub(x, Nat.mul(Nat.div(x, y), y));
	}

	public static Nat kleiner(Nat x, Nat y) {
		return groesser(x, y) == Nat.zero() ? Nat.succ(Nat.zero()) : Nat.zero();
	}

	public static Nat groesser(Nat x, Nat y) { // >
		return Nat.sub(x, y) == Nat.zero() ? Nat.zero() : Nat.succ(Nat.zero());

	}

	public static Nat gleich(Nat x, Nat y) {
		return modulo(x, y) == Nat.zero() ? groesser(x, y) == Nat.zero() ? Nat.succ(Nat.zero()) : Nat.zero() : Nat.zero();
	}

	public static Nat ggt(Nat x, Nat y) {
		return y == Nat.zero() ? x : ggt(y, modulo(x, y));
	}

	public static Nat kgv(Nat x, Nat y) {
		return x == Nat.zero() ? Nat.zero()
				: y == Nat.zero() ? Nat.zero()
						: ggt(x, y) == Nat.zero() ? Nat.zero() : Nat.div(Nat.mul(x, y), ggt(x, y));
	}

	public static Liste<Nat> pfz(Nat x) {
		return pfzH(x, Nat.succ(Nat.succ(Nat.zero())));

	}

	private static Liste<Nat> pfzH(Nat zahl1, Nat zahl2) {
		if (groesser(zahl1, zahl2) == Nat.zero()) {
			return Liste.vorne(new Liste<Nat>(), zahl2);
		} else if (modulo(zahl1, zahl2) == Nat.zero()) {
			return Liste.vorne(pfzH(Nat.div(zahl1, zahl2), zahl2), zahl2);
		} else {
			return pfzH(zahl1, Nat.add(zahl2, Nat.succ(Nat.zero())));
		}
	}

}
