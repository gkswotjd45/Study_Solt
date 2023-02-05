package java_codingTest_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p63_goodNumber {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int data = Integer.parseInt(br.readLine()); // 입력받을 데이터 수.
		int arr[] = new int[data]; // 입력받은 배열 공간 선언
		int result = 0;
		StringTokenizer sc = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(sc.nextToken());
		} // 배열안에 임의의 수를 개수만큼 저장.
		
		Arrays.sort(arr);
		
		for(int k = 0; k<data; k++) {
			int start = 0; 
			int end = data-1;
			long find = arr[k];
			
			while(start<end) { //start 인덱스 값이 end 인덱스값을 넘을 경우 while문 벗어남,
				
				if(arr[start]+arr[end]== find) { // 2개의 숫자의 합이 찾는 값과 동일
				
					
					if(start != k && end != k ) { //시작인덱스값과, 마지막 인덱스가 자기 자신을 포함하지 않을 때
						result ++;
						break;
					}else if(start == k) {
						start ++;
						
					}else if(end==k) {
						end--;
					}
					
				}else if ((arr[start]+arr[end])<find) { // 두개의 배열의 합이 찾는 값보다 작을 경우 시작 인덱스 증가,
					start++;
				}else
					end--;
			}
		}
		
		System.out.println(result);

	}

}
