package workshop_Day03;

import java.util.Random;

public class WS_P3_04 {
	public static void main(String[] args) {
		int arr3 [] = new int[5];
		Random r = new Random();
		for(int i = 0; i<5; i++) {
			int a = r.nextInt(10)+1;
			for(Integer num : arr3) {
				if(num == a) {
					//arr3. = r.nextInt();
				}
			}
			arr3[i] = a;
		}
		
		
		for(Integer k : arr3) {
			System.out.print(k + " ");
		}
	}
}
