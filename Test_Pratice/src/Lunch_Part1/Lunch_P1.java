package Lunch_Part1;

public class Lunch_P1 {
	public static void main(String[] args) {
		
		int a = Integer.parseInt(args[0]);
		if((a < 100) &&( a>0)) {
			if (a % 2 == 0) {
				System.out.println("2의 배수 입니다.");
			}else 
				System.out.println("2의 배수가 아닙니다.");
		}
	}
}
