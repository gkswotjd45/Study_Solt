package Lunch;

public class LunchEx_02 {

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int hap =  1;
		if(a>=5) {
		for (int i = 1; i<=a;i++) {
				hap *= i;
				
				if(i == a) {
					System.out.print(i + " =");
				}
				System.out.print(i + " * ");
		}
		System.out.print(hap);
			/*
			if (i == a) {
				System.out.print(i + " =");
				hap *= i;  // hap = hap * i;
			}else {
			System.out.print(i + "*");
			hap *= i;}
		}
		*/
		
	}}
}
