package workshop_Day02;

public class WS_P2_07 {

	public static void main(String[] args) {
		int sum =0;
		int n = Integer.parseInt(args[0]); // String을 int형으로 변환
		for(int i=1; i<=100; i++) {
			if(i % n == 0) {
				sum += i;
			}
		}
		
		System.out.println("Sum = " +sum);
		
	}

}
