package Lunch;

class Month{
	public Month() {
		// TODO Auto-generated constructor stub
	}
	
	public int GetDays(int montha) {
		if(montha == 2) {
			return 28;
		}else if ((montha == 9)||(montha == 4)||(montha == 6)||(montha == 11)){
			return 30;
		}else if((montha>12) ||(montha<1)) {
			return -1;
		}
		return 31;
	}
}

public class LunchEx_06 {
	
	public static void main(String[] args) {
		Month m = new Month();
		int a = Integer.parseInt(args[0]);
		System.out.println("입력 된 월 :" + args[0]);
		
		if(m.GetDays(a) == -1) {
			System.out.println("잘못된 정수입니다.");
		}else {
		System.out.println("마지막 일 : "+ m.GetDays(a));
		}
	}
}
