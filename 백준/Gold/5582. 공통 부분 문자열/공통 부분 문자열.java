import java.io.*;
import java.util.*;

public class Main {
	
	static String inputA, inputB;
	static int N, M;		// inputA / inputB 의 길이
	static int [][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 입력 받기
		inputA = br.readLine();
		inputB = br.readLine();
		N = inputA.length();
		M = inputB.length();
		
		int ans = 0;
		int[][] dp = new int[N + 1][M + 1];
		
		// 2. 대각선 보면서 DP 수행
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (inputA.charAt(i - 1) == inputB.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		bw.write(String.valueOf(ans));
		
		bw.flush();
		bw.close();
		br.close();
	}
}