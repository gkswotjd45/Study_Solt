package sampleProje;
// sleep을 이용한 상태 전이 방법, 순서 정렬.
class TreadEx_05_1 extends Thread{
	/* t1.sleep(3000) 재우기 위한 방법. (나중에 수행되는 방법)
	try {
		Thread.sleep(2000) //Thread.sleep(2000); static 은 인스턴스를 안만들고 바로 클래스에서 바로 수행이 가능. 인스턴스 객체를 만들어도 클래스에 있는 static
						// t1의 인스턴스를 재우는 것이 아니라 해당 스레드가 수행될 때 자는 형태로 수행.. = 그래서 메인스레드가 가장 마지막에 나타오게 됨.
						// 특정스레들 지정해서 수행을 일시적으로 수행하는 방법이 따로 있음.
						//
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	
	@Override
	public void run() { // thread를 시작하면 동작되는 코드
		for(int i = 0; i<300 ; i++) {
			System.out.print("-");
		}
		System.out.println("<<Tread 1 종료 >>"); //for문을 다 돌리고 나서 마지막 출력.
	}
}

class ThreadEx_05_2 extends Thread{
	
	@Override
	public void run() {  // thread를 시작하면 동작되는 코드
		for(int i = 0; i<300 ; i++) {
			System.out.print("|");
		}
		System.out.println("<<Tread 2 종료 >>");  //for문을 다 돌리고 나서 마지막 으로 출력.
	}
	
}
public class ThreadEx_05 {
	
	public static void main(String[] args) {
		Thread t1 = new TreadEx_05_1();
		Thread t2 = new ThreadEx_05_2();
		
		t1.start(); //t1으로 생성한 스레드 시작
		t2.start(); //t2으로 생성한 스레드 시작
		
		try {
			t1.sleep(2000); //Thread.sleep(2000); static 은 인스턴스를 안만들고 바로 클래스에서 바로 수행이 가능. 인스턴스 객체를 만들어도 클래스에 있는 static
							// t1의 인스턴스를 재우는 것이 아니라 메인스레드가 자는 형태로 수행.. = 그래서 메인스레드가 가장 마지막에 나타오게 돔.
							// 특정스레들 지정해서 수행을 일시적으로 수행하는 방법이 따로 있음.
							//
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("<<Main thread 종료>>"); 
	}
}
