package sampleProje;
// 공유객체를 생성하기 위한 class
class Shread {
	
	// 번걸아 실행하기 위해 동기화 시켜줌
	public synchronized void printName() { //공유객체의 공유 메서든, 모든 쓰레드가 저 메서드를 갖고 이름을 프린트 //첫번째 코드가 들어가면 그다음 쓰레드가 block 처리되
											// 기다리는 형태여서 강제로 대기상태.
		
		try {
			for(int i=0; i<10; i++) { // 자기 이름을 10번만
				Thread.sleep(1000);	
				System.out.println(Thread.currentThread().getName());
				notify(); // 내가 wait으로 들어갈때 notify통해 사전에 모니터를 꺼내여 수행.
				wait(); // 첫번째 스레드가 집입후 알아서 block 상태로 집입
				// notify(); wait가 이미 들어간 상태여서, 깨우지 못함. 
			}
		} catch (Exception e) {

		}
	}
	
}//공유객체를 만들기 위해 생성된 클래스, 해당 클래스는 메서드를 통해 수행


class ThreadEx13_1 implements Runnable { //쓰레드로 상속받지 않으므로 공유객체가 해당클레스에서 있어야함.
										// run 수행 메서드 안에 공유객체 메서드가 있어야함.ㄴ
	private Shread shread; // 선언 이유 Shread라는 객체를 필드로 생성하여 사용하도록 함. (공유변수)
	
	public ThreadEx13_1() {
		// TODO Auto-generated constructor stub
	}
	
	public ThreadEx13_1(Shread shread) {
		super();
		this.shread = shread;
	}
	
	@Override
	public void run() {
		
		shread.printName();
	}
	
}
public class ThreadEx_13 {
	public static void main(String[] args) {
		Shread shared = new Shread();// 공유객체 생성
		
		Thread t1 =  new Thread(new  ThreadEx13_1(shared) ,"첫번째");
		Thread t2 =  new Thread(new  ThreadEx13_1(shared) ,"두번째"); //shread 공유객체에 런어블 객체인 Thread 13_1에 쓰레드 객체 생성.
		
		t1.start();
		t2.start();
	}
}