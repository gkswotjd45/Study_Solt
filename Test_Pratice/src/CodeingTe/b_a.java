package CodeingTe;

import java.util.Scanner;

public class b_a {


	public static void main(String[] args) {
		
		Scanner d = new Scanner(System.in);
		
		System.out.print("a값 :" );
		int a = d.nextInt();
		
		System.out.print("b값 :" );
		int b = d.nextInt();
		
		if(a>=b) {
			System.out.println("a보다 큰값을 입력하세요.");
			
		}else {
			System.out.println("b-a는 " + (b-a));
		}
	}
}
