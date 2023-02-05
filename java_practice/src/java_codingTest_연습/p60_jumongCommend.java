package java_codingTest_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p60_jumongCommend {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int matiors = Integer.parseInt(br.readLine()); // 재료의 수
		int garde = Integer.parseInt(br.readLine());  // 강화의 수치
		int arr[] = new int[matiors]; // 재료의 목록의 수치 명시
		
		StringTokenizer sc = new StringTokenizer(br.readLine());

		for(int i = 0 ; i<arr.length; i++) {
			arr[i] = Integer.parseInt(sc.nextToken());
		}
		
		Arrays.sort(arr);
		
		int i = 0;
		int j = matiors -1;
		int count = 0; // 결고값
		
		while(i<j){
			
			if((arr[i]+arr[j])<garde) {
				i++;
			}
			else if((arr[i]+arr[j])>garde) {
				j--;
			}
			else {
				count++;
				i++;
				j--;
			}
		}
		System.out.println(count);
		br.close();
	}
}
