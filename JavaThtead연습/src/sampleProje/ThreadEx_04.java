package sampleProje;

 // 데몬스레드 생성 및 설명
public class ThreadEx_04 implements Runnable { 
	
	//내 클래스가 runnable 을 구현하는 킁애스
	
	
	
	//field
	static boolean autoSave = false; //클래스의 이름만 가지고 접근이 가능.
	
	public static void main(String[] args) { // 메인 스레드에 의해서 실행되는 코드.
		
		Thread t = new Thread(new ThreadEx_04()); //메인 메서드 안에서 main 스레드를 생성함 // t는 메인 스레드를 보조해주는 스레드.
		t.setDaemon(true); // 자기를 만든 스레드를 보조하게 됨. // if 주석처리 하면 데몬 스레도르 처리하지 않겠다며 메인스레드는 종료되었더라도 run() 메서드는 무한 루프
							// 를 수행하게 됨.
		t.start(); // 스레드를 시작.
		
		//메인스레드 에서 수해하는 내용
		
		for( int i = 0; i<20; i++) {
			try {
				//t1.sleep(1000); t1의 객체가 재우는 의미로 인식하지만 실제로 클래스로 저장된 static으로 나오는게 맞음
				Thread.sleep(1000); // 현재 실행되는 스레드를 재울 때. => 메인스레드를 재움. //클래스에 지정된 static Sleep 으로 사용이 가능함, 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i ==5) {
				autoSave = true; // 필드 초기화시 Static 으로 지정했기때문에 사용가능.
			}
		}
		
		//for문이 종료 되면 run() 메서드도 같이 종료.
		
	}
	
	

	@Override
	public void run() { //실행하는 코드 , 메인에서 파생되는 스레드.
		
		while(true) {
				
			// thread를 일정시간 동안 재울껏. (데몬스레드를 재우는 스레드) static 메서드
			try {
				Thread.sleep(3000); // 일정 시간동안 스레드를 중지 Sleep 은 static 메서드
			} catch (InterruptedException e) { // 예외가 발생할 여지가 있어 해당 예외가 발생하면 처리하는 코드.
				// TODO Auto-generated catch block
				e.printStackTrace(); // 예외상황을 화면을 출력.
			} // static 메서드로 지정돔 (클래스를 바로 수행)
			
			if(autoSave) { // 다시 실행하는 방식
				System.out.println("자동저장 되었어요.");
				
			}
			System.out.println("");
		}
	}
}
