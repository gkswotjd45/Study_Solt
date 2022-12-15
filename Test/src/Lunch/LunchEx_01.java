package Lunch;

public class LunchEx_01 {
	public static void main(String[] args) {
		
		
		Double a = Double.parseDouble(args[0]);
		Double b = Double.parseDouble(args[1]);
		
		//System.out.println(a + " ," + b);
		if((a % b >=1)) {
			System.out.println("나머지가 1보다 크다.");

		}else {
			System.out.println("나머지가 1보다 작거나 같다.");
		}
		
	}
}

