package sampleproj;

class ThreadEx_03_1 extends Thread {

	public ThreadEx_03_1() {
		// TODO Auto-generated constructor stub
	}
	
	public ThreadEx_03_1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(getName());
		}
	}
}

public class ThreadEx_03 {

	public static void main(String[] args) {
		
		Thread t1 = new ThreadEx_03_1("Thread-01");
		Thread t2 = new ThreadEx_03_1("Thread-02");
		
		// 실행시키기 전에 우선순위를 매겨보아요!
		t1.setPriority(1);
		t2.setPriority(9);
		
		t1.start();
		t2.start();
		
	}
}






