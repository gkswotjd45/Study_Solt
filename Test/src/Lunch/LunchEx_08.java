package Lunch;


public class LunchEx_08 {
	
	public static void main(String[] args) {
		String str = args[0];
		
		char[] arr = str.toCharArray();
		
		for(int i = arr.length -1; i>=0 ; i--) {
			System.out.print(arr[i]);
		}
		
	}
}
