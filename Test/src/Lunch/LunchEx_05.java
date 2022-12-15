package Lunch;

public class LunchEx_05 {

	
	public static void main(String[] args) {

		
		int arr [] = new int[3];
		
		for(int i = 0; i<3 ; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}
		
		
		for(int j = 0; j<2; j++) {
			for(int k = 2; k > 0; k--) {
				if (k == 1 & j == 1) {
					break;
				}
				if(arr[j]>arr[k]) {
					int tmp;
					tmp = arr[j];
					arr[j]=arr[k];
					arr[k] = tmp;
				}
			}

			
		}
		
		System.out.println("최소값 " + arr[0]);
		System.out.println("최대값 " + arr[2]);
	}
}
