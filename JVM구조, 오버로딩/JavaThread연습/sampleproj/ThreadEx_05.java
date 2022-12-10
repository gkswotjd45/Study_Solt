package sampleproj;

class ThreadEx_05_1 extends Thread {
	@Override
	public void run() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("소리없는 아우성!");
		}
		
		for(int i=0; i<300; i++) {
			System.out.print("-");
		}
		System.out.println("<< Thread 1 종료 >>");
	}
}

class ThreadEx_05_2 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.print("|");
		}
		System.out.println("<< Thread 2 종료 >>");		
	}
}

public class ThreadEx_05 {

	public static void main(String[] args) {
		Thread t1 = new ThreadEx_05_1();
		Thread t2 = new ThreadEx_05_2();
		
		t1.start();
		t2.start();
		
		System.out.println("<<main thread 종료>>");
	}
}




