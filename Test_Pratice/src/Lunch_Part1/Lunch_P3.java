package Lunch_Part1;

public class Lunch_P3 {
	public static void main(String[] args) {
		
		
		
		
			for(int i = 2 ; i<6 ; i++) {
				for (int j = 0; j<10; j++) {
					if(!((i*j) % 2  == 0)) {
						System.out.println(i + "*" + j + "= " +i*j);
						}
					}
				}
	}
}
