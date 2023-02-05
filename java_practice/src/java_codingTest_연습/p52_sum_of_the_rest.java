package java_codingTest_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p52_sum_of_the_rest {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sc = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(sc.nextToken()); // 배열 개수
		int M = Integer.parseInt(sc.nextToken()); // 나머지 연신
		int sumArray [] = new int[N]; // 배열 합
		int countArray []  = new int[N]; // 나머지 인덱스 카운트 배열,
		int answer = 0;
		
		sc = new StringTokenizer(br.readLine());
		for(int i = 1; i<N; i++) {
			sumArray[i] = sumArray[i-1]+ Integer.parseInt(sc.nextToken());
		}
		
		for(int j = 0; j<N ; j++) {
			int remainder = sumArray[j] % M;
			if(remainder == 0 ) {
				remainder++;
			}
			countArray[j] = remainder;
		}
		
		for(int i=0; i<N ; i++) {
			if(countArray[i]> 1) {
			
				answer = answer+(countArray[i]*(countArray[i] - 1))/2;
				
			}
		}
		
		System.out.println(answer);
	}
}
