package Lunch_Part1;

public class Lunch_P4 {
	public static void main(String[] args) {
		
		int i =1;
		double hap = 0;
		while(i<100) {
			hap += i;
			i++;
		}
		System.out.println("합계 :" + hap);
		System.out.println("평균 :" + hap/100);
		
	}
}
