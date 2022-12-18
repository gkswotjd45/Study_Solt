package Lunch_Part2;

import java.util.StringTokenizer;

public class Lunch_P2_3 {
	public static void main(String[] args) {
		String str = "1.22,4.12,5.93,8.71,9.34";
		double data [] = new double[5];
		double sum = 0; // 합계 필드
		
		StringTokenizer st; // 사용자가 지정하는 구분자를 경계로 문자열 나눠줌
		st = new StringTokenizer(str,",");  // str 문자열을 st 라는 String Toekenizer 객체에 생성하여, "," 단위로 분류
	

		for(int i =0; st.hasMoreElements();i++) {
	
			data[i] = Double.parseDouble(st.nextToken()); //double 형 배열에 토큰을 하나씩 불리하여, double형 배열에 삽입.
 		}
		
		for (double a : data) {
			sum += a;
		}
		System.out.println("합계 : " + sum);
		System.out.println("평균 : " + sum/data.length);
		
	}
}
