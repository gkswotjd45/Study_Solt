package workshop_Day03;

public class WS_P3_02 {
	public static void main(String[] args) {
		int [][] arr2 = {{5,5,5,5,5},{10,10,10,10,10},
				{20,20,20,20,20},{30,30,30,30,30}};
		int sum = 0; // 배열 총합
		double count =0; // 배열 개수
		for(int i=0 ; i<4 ; i++) {
			for(int j=0; j<5; j++) {
				sum = sum + arr2[i][j]; 
			}
			count = count + arr2[i].length;
		}
		System.out.println("total = "+ sum);
		System.out.println("average = " +sum/count);
	}
}
