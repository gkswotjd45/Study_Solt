package workshop_Day03;

public class WS_P3_01 {
	public static void main(String[] args) {
		int arr [] = {10,20,30,40,50,60,70,80,90,100};
		int sum = 0;
		
		for (int i = 0 ; i<arr.length; i++) {
			if(i % 2 == 1) {
				sum += arr[i];
			}
		}
		System.out.println("Sum = " +sum);
	}
}
