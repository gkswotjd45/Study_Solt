package Lunch;

public class LunchEx_03 {

	public static void main(String[] args) {
		/*
		int inx = 0;
		while (inx <= 6) {
			int jnx = 0;
			while (jnx <= inx) {
				System.out.println("*");
				jnx ++;
			}
			System.out.println("@");
			inx ++;
		}
		*/
		
		int i; 
		for(i = 0; i<=6; i++) {
			for(int j=0; j<=i; j++) {
				System.out.println("*");
			}
			System.out.println("@");
		}

	}
}