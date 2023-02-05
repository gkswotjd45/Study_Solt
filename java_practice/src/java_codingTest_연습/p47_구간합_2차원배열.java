package java_codingTest_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p47_구간합_2차원배열 {
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		int M = Integer.parseInt(st.nextToken()); // 반복횟수
		int Array [][] = new int [N+1][N+1];
		
		int sum[][] = new int [N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				Array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + Array[i][j];
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
			System.out.println(result);
		}
	}
}
