package sample;

interface myInterface{ //클래스에 특별한 형태, 아직 완전히 만들어지지 않기 때문에 (추상메서드) 형태로 존재. 
	void aa();
}


public class InterfaceTest {
	
	public static void main(String[] args) {
		
		myInterface a = new myInterface() { //임시 클래스가 생성. -> 실제 인스턴스 생성 후 오버라이딩을 제대로 해주면 됨. 이 방법을 수행하지 않으면
											// 매번 클래스를 생성해야 함.
			
			@Override
			public void aa() { //묵시적, 해당 메서드를 바로 오버라이딩 수행하여 객체 생성.
				
			}
		};
	}
}
