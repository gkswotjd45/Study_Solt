package Lunch_Part1;

public class Lunch_P2 {
	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		
		if((a*b)<10) {
			System.out.println("한자리 수 입니다.");
		}else
			System.out.println("두자리 수입니다.");
	
	}
}
