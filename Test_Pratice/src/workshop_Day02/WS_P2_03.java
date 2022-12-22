package workshop_Day02;

public class WS_P2_03 {

	public static void main(String[] args) {
		
		/*
		for(int i = 0; i<=10; i++) {
			for(int j = 0; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}*/
		int i = 0;
		int j = -1;
		while(i<10) {
			System.out.print("*");
			j++;
			if(i==j) {
				System.out.println();
				i++;
				j = -1;
			}
		
		}
	}

}
