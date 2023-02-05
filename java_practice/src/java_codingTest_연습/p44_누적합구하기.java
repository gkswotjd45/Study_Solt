package java_codingTest_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p44_누적합구하기 {
	public static void main(String[] args) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer stringTokenizer;
			try {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int Arrcount = Integer.parseInt(stringTokenizer.nextToken()); // 숫자의 개수
				int Qcount = Integer.parseInt(stringTokenizer.nextToken()); // 수행 반복 수
			
				//int array [] = new int[Arrcount]; // 배열 형태의 입력 저장공간 
				long SumArray [] = new long [Arrcount+1]; // 누적 합 배열 공간
				
				//array[0] = 0;
				SumArray[0] = 0;
		
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				
				for(int i = 1; i<SumArray.length; i++) {
					SumArray[i] = SumArray[i-1] + Integer.parseInt(stringTokenizer.nextToken()); ;
					
				}
		
				
				for(int k=0; k<Qcount; k++) {
					stringTokenizer = new StringTokenizer(bufferedReader.readLine());
					int i = Integer.parseInt(stringTokenizer.nextToken()); ;
					int j = Integer.parseInt(stringTokenizer.nextToken()); ;
		
					System.out.println("Array[" + i+ "] 부터 Array[" + j +"]의 합은 :" +(SumArray[j] - SumArray[i-1]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 임의의 정수 입력 및 누적 합 수행.

		
	}
}
