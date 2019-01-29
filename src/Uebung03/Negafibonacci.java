
public class Negafibonacci {

	public static int negaFibo(int n, NegaFiboRecCheck nfrc) {
		nfrc.nfrc();
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n > 1) {
			return negaFibo(n - 2, nfrc) + negaFibo(n - 1, nfrc);
		} else {
			return (int) Math.pow(-1, n + 1) * negaFibo(n * -1, nfrc);
		}
	}

}
