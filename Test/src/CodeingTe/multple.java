package CodeingTe;

import java.util.Scanner;

public class multple {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int hap = 0;
		int mok = 0;
		int n = s.nextInt();
		
		if((n>= 3 && n <=5000)) {
			
			if(n % 5 == 0) {
				hap = n % 5;
				mok = n/5;
				System.out.println(mok);
			}else if (n % 3 == 0) {
				hap += n % 3;
				mok = n/3;
				System.out.println(mok);
			}
		}
	}
}


