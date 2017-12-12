package eCoins;

public class Problem {
	// Number of different cointypes
	private int m;
	
	// The e-modulus value to reach
	private int s;
	
	// The list of the different cointypes for the problem
	private CoinType[] coinTypes;
	
	Problem(int m, int s, CoinType[] coinTypes) {
		this.m = m;
		this.s = s;
		this.coinTypes = coinTypes;
	}
	/**
	 * @return The number of coins needed to reach s
	 */
	public int solve() {
		int border = s + 1;
		int jBorder = border;
		
		int smallestNrCoins = Integer.MAX_VALUE;
		int[][] shortestPath = new int[border][border];
		
		for (int i = 0; i < border; i++) {
			for (int j = 0; j < border; j++) {
				shortestPath[i][j] = Integer.MAX_VALUE;
			}
		}
		
		shortestPath[0][0] = 0;
		int iPrev, jPrev;
		
		int sSquare = s*s;
		int eModSquare;
		
		for (int i = 0; i < border; i++) {
			for (int j = 0; j < jBorder; j++) {
				eModSquare = i*i + j*j;
				for (int t = 0; t < m; t++) {
					iPrev = i - coinTypes[t].getX();
					jPrev = j - coinTypes[t].getY();
					
					if (iPrev >= 0 && jPrev >= 0) {
						int newPathLength = shortestPath[iPrev][jPrev] + 1;
						if (shortestPath[iPrev][jPrev] != Integer.MAX_VALUE && newPathLength < shortestPath[i][j]) {
							shortestPath[i][j] = newPathLength;
							if (eModSquare == sSquare && shortestPath[i][j] < smallestNrCoins) {
								smallestNrCoins = shortestPath[i][j];
								jBorder = j;
							}
							else if (eModSquare > sSquare) {
								jBorder = j;
							}
						}
					}
				}
			}
		}
		return smallestNrCoins;
	}
}
