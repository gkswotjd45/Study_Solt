package sampleproj;

class ThreadEx_10_1 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.print("-");
		}
	}
}

class ThreadEx_10_2 extends Thread {

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.print("|");
		}
	}
}


public class ThreadEx_10 {

	public static void main(String[] args) {
		long startTime = 0;
		
		Thread t1 = new ThreadEx_10_1();
		Thread t2 = new ThreadEx_10_2();
		
		t1.start();
		t2.start();
		
		startTime = System.currentTimeMillis(); // 숫자로 현지시간을 표현
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("소요시간 : " 
		+ (System.currentTimeMillis() - startTime));
		
	}
}


