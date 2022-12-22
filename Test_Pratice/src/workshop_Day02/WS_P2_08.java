package workshop_Day02;

public class WS_P2_08 {
	public static void main(String[] args) {
		int [] arr  = {10,20,30,40,50};
		
		int sum = 0; 
		
		for(Integer a :arr) {
			sum += a;
		}
		System.out.println("sum = " +sum);
		System.out.println("avg = " +sum/arr.length);
	}
}
