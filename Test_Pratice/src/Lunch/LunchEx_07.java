package Lunch;

public class LunchEx_07 {
	
	
	
	public static void main(String[] args) {
		
		double hap = 0; // 
		boolean check = false;
		Double arr[] = new Double[args.length];
		
		for(int i = 0; i<arr.length; i++) {
			arr[i] = Double.parseDouble(args[i]);
		}
		
		
		
		for(int i = 0; i<arr.length; i++) {
			
			if((arr[i]<10) || (arr[i]>99)) {
				System.out.println("다시 입력하세요.");
				check = true;
				break;
			}else if(arr.length > 5) {
				System.out.println("다시 입력하세요.");
				check = true;
				break;
			}
		}
		
		if(!check) {
			hap += ((arr[0]+arr[1])/2) *0.6;
			hap += ((arr[2]+arr[3])/2) *0.2;
			hap += arr[4] *0.2;
			System.out.println(hap);
			System.out.print("Class :");
			if(hap >= 90) {
				System.out.print("Gold Class");
			}else if (hap>=80) {
				System.out.print("Sliver Class");
			}else if (hap>=70) {
				System.out.print("Bronze Class");
			}else if (hap<70) {
				System.out.print("Nomal Class");
				}
		}
	}
}
