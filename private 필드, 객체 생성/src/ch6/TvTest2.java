package ch6;

public class TvTest2 {
	
	public static void main(String[] args) {
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		
		System.out.println("t1의 채널 값은" + t1.channel+ "입니다.");
		System.out.println("t2의 채널 값은 " + t2.channel + "입니다.");
		
		t1.channel = 7;
		System.out.println("t1의 채널 값은 " + t1.channel + "변경하였습니다.");
		
		System.out.println("t1의 채널 값은" +t1.channel+"입니다.");
		System.out.println("t2의 채널 값은" +t2.channel+ "입니다.");
	}
}
