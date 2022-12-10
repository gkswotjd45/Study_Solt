package sampleProje;

// 스레드 우선순위 설명. 그러나 싱글 코어에서만 사용됨.
class ThreadEx_03_01 extends Thread{
	
	
	public ThreadEx_03_01() {
		// TODO Auto-generated constructor stub
	}
	public ThreadEx_03_01(String name){
		super(name);
	}
	@Override
	public void run() {
		for (int i=0; i<5; i++) {
			System.out.println(getName());
		}
	}
}
public class ThreadEx_03 {
	
	
	public static void main(String[] args) {
		Thread t1 = new ThreadEx_03_01("Thread 01");
		Thread t2 = new ThreadEx_03_01("Thread-02");
		
		//실행시키 전에 스레드를 기동하기 전에 우선수위를 매겨 보아요
		t1.setPriority(1);
		t2.setPriority(9); // 우선순위 의미 없음. -> 제어안됨. (싱글 코어인 경우 의미 O)
		
		t1.start();
		t2.start();
		
	}
}
