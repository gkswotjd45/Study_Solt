package workshop_Day02;

public class WS_P2_04 {
	
	public static void main(String[] args) {
		
			for(int i = 1; i<=6 ; i++) {
				for (int j = 1; j<=6; j++) {
					if(i + j == 6) {
						System.out.println(i+ "+" + j + "=" + (i+j));
					}
				}
			}
	}
}
