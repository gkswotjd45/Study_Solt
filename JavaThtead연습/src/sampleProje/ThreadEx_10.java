package sampleProje;

class ThreadEx_10_2 extends Thread{
	
	@Override
	public void run() {
		for(int i = 0; i<100 ;i++) {
			System.out.println("--");
		}
	}
}
class ThreadEx_10_1 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i<100 ;i++) {
			System.out.println("|");
		}
	}
}


public class ThreadEx_10 {

	public static void main(String[] args) {
		long startTime = 0; // 얼마나 걸리는 지 시간 볼려고
		Thread t1 = new ThreadEx_10_1();
		Thread t2 = new ThreadEx_10_2();
		
		t1.start();
		t2.start();
		//메인스레드 ,t1, t2 시작
		
		startTime = System.currentTimeMillis(); // 현재시간을 천분의 1초 단위로 표현. 숫자로 현재시간을 표현.
		
		try {
			t1.join(); //메인스레드가 여기에 오면 중지시키고, t1을 쓰레드를 참여(포함) 시킴 -> 언제까지 t1이 끝날때 까지 수행. 
			t2.join(); // t1을 중지시키고, t2를 참여시킴 
						// 메인스레드는 t1,t2까지 완료 될때까지는 메인스레드를 중지시킴.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try catch 에외 처리 (강체로 해야함
		
		System.out.println("소요시간"  + (System.currentTimeMillis() - startTime)); // 현재시간에서 과거 시간을 빼서 소요 시간을 결과를 계산함.
	}
}

