package bpackage;


public class Cclass { // 
	
	
	//constructor
	
	
	//field 
	
	//method
	
	public static void main(String[] args) {
		Bclass b = new Bclass(); // 같은 패키지 안에서 사용이 가능.
		// b.bField = 100; //  (오류로 인한) 직접적인 접근 제어하는 것이 아니라, 안전하게,적절한 접근하는 것을 이용하자. 
						// 접근 자체가 안되면 어떻게 값을 바꾸어야 하나?
		b.setField(100);		//setter를 아용햐서 값을 바꿔야 한다.
		
	}
}
	