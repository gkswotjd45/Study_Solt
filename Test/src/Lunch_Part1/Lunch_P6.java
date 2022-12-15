package Lunch_Part1;

public class Lunch_P6 {
	public static void main(String[] args) {
		int [] array = {1,2,3,4,5,6,7,8,9,10};
		int i=0;
		double hap =0;
		
		while(i<array.length-1) {
			if((array[i] % 2 ==0)) {
				i++;
				continue;
			}
			hap += array[i];
			i++;
		}
		
		System.out.println("합계 : " + hap);
	}
}
