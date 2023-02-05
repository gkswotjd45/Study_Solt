package java_codingTest_연습;

import java.util.Scanner;

public class p56_numberSUm {

	public static void main(String[] args) {
		int count = 1; // 자연수의 합의 개수 (자기자신)
		int start_index = 1; // 처음 포인터 
		int end_index = 1; // 마지막 포인터
		int sum = 1; 
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // 원하는 숫자의 누적합 
		
		while(end_index != num) {
			if (sum == num) {
				end_index++;
				sum += end_index;
				count++;
			}
			else if(sum>num) {
				sum -= start_index;
				start_index++;
			}
			else if(sum<num) {
				end_index ++;
				sum += end_index;
			}
		}
		System.out.println(count); // 자연수 앙목의 누적합 개수.
	}

}
