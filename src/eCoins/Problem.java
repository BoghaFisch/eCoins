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
		
		// Initialize the smallest number of coins needed to reach s to max value for int ("positive inf")
		int smallestNrCoins = Integer.MAX_VALUE;
		int[][] shortestPath = new int[border][border];
		
		// Initialize all elements to max value for integer ("positive inf")
		for (int i = 0; i < border; i++) {
			for (int j = 0; j < border; j++) {
				shortestPath[i][j] = Integer.MAX_VALUE;
			}
		}
		// Set number of steps for starting point in origo to 0
		shortestPath[0][0] = 0;
		int iPrev, jPrev;
		
		// Use s^2 for comparison, to avoid having to do sqrt on each element
		int sSquare = s*s;
		int eModSquare;
		
		for (int i = 0; i < border; i++) {
			for (int j = 0; j < jBorder; j++) {
				
				// Calculate eMod squared to compare with s squared
				eModSquare = i*i + j*j;
				
				// For each cointype, check if subtracting their x and y coordinates lead to a reachable path from [0, 0]
				for (int t = 0; t < m; t++) {
					iPrev = i - coinTypes[t].getX();
					jPrev = j - coinTypes[t].getY();
					
					// Check so that we don't go outside the border
					if (iPrev >= 0 && jPrev >= 0) {
						
						// If the coin leads to a shorter path to [i, j], update the shortest path
						int newPathLength = shortestPath[iPrev][jPrev] + 1;
						if (shortestPath[iPrev][jPrev] != Integer.MAX_VALUE && newPathLength < shortestPath[i][j]) {
							shortestPath[i][j] = newPathLength;
							
							// Check if we have reached the desired e-modulus. If we have done so in a shorter way than before, update
							// least amount of coins required
							if (eModSquare == sSquare && shortestPath[i][j] < smallestNrCoins) {
								smallestNrCoins = shortestPath[i][j];
								jBorder = j;
							}
							// If eMod larger than the one we are looking for, update jBorder, since all j's following will also be 
							// outside the desired eMod
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
