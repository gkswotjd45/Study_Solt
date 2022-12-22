package CodeingTe;

public class Array {
	
	static int sumOf(int[] a) {
		int sum = 0;
		
		for (Integer i: a) {
			sum += i;
		}
		return sum;
	}
	
	
	

	public static void main(String[] args) {
		Array ray = new Array();
		int arr [] = {2,5,3,9,6,7};
		int n = arr.length;
		for(int i = 1; i<=arr.length /2 ; i++) {
			for(Integer a : arr) {
				System.out.print(a + " ");
			}
			int tmp = arr[i-1];
			arr[i-1] = arr[arr.length-i];
			arr[(arr.length)-i]  = tmp;
			System.out.println();
			System.out.print("arr["+(i-1)+"]과 arr[" + (arr.length-i)+"]을 교환합니다.");
			
			System.out.println();
		}
		
		for(Integer num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("\n"+ray.sumOf(arr));
	}

}
