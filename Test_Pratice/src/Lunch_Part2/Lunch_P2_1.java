package Lunch_Part2;

public class Lunch_P2_1 {
	public static void main(String[] args) {
		int [][] array = {{12,41,36,56},{82,10,12,61},
						 {14,16,18,78},{45,26,72,23}};
		
		double hap = 0; // 합계
		double avg = 0; // 평균
		int count = 0; // 각 행에 대한 요소의 수.
			
		System.out.println(array.length);
		for(int [] tmp : array) {
			count += tmp.length; // arr[i][]요소의 길이를 누적.= 각 행에 데한 요소를 더함. 
			for(int i : tmp) {
				hap += i;
			}
		}
		avg = hap / count;
		System.out.println("합계 :" + hap);
		System.out.println("평균 :" + avg);
	}
}
