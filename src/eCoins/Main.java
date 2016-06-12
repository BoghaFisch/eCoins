package eCoins;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	public static Problem[] readInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			Problem[] problems = new Problem[n];
			String line;
			String[] lineComponents;
			int m, s;
			CoinType[] coinTypes;
			
			// Read each problem
			for (int i = 0; i < n; i++) {
				
				// Fetch m and S
				line = br.readLine();
				lineComponents = line.split(" ");
				m = Integer.parseInt(lineComponents[0]);
				s = Integer.parseInt(lineComponents[1]);
				coinTypes = new CoinType[m];
				
				// Read CoinTypes for each problem
				for (int j = 0; j < m; j++) {
					lineComponents = br.readLine().split(" ");
					coinTypes[j] = new CoinType(Integer.parseInt(lineComponents[0]), Integer.parseInt(lineComponents[1]));
				}
				problems[i] = new Problem(m, s, coinTypes);
				
				// Skip empty line
				br.readLine();
			}
			br.close();
			return problems;
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
	public static void main(String[] args) {
		Problem[] problems = readInput();
		
		// Solve the problems
		int[] results = new int[problems.length];
		for (int i = 0; i < problems.length; i++) {
			results[i] = problems[i].solve();
		}
		// Print results
		PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
		for (int i = 0; i < results.length; i++) {
			if (results[i] != Integer.MAX_VALUE) {
				writer.println(results[i]);
			}
			else writer.println("not possible");
		}
		writer.flush();
		writer.close();
	}
}
