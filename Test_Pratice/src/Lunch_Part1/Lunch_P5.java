package Lunch_Part1;

class Calc {
	
	public int sum(int a, int b) {
		
		return a + b;
	}
	public int subtract(int a, int b) {
			
			return a - b;
		}
	public int Multiply(int a, int b) {

		return a * b;
	}
	public int divide(int a, int b) {
		
		if(a == 0 || a<0) {
		return 0;
		}
		return a/b;
	}
}


public class Lunch_P5 {
	
	public static void main(String[] args) {
		
		Calc calc = new Calc();
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
	
		System.out.println("합 : "+calc.sum(a, b));
		System.out.println("차 : "+calc.subtract(a, b));
		System.out.println("곱 : " +calc.Multiply(a, b));
		System.out.println("나누기 : " +calc.divide(a, b));
	}
	
}
