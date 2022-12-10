package sampleProje;

import java.util.concurrent.LinkedTransferQueue;

// 이 ThreadEx_11_1의 instance을 생성해서 실행하면, 10초마다 일정량의 메모리 량을 감소시킴.
class ThreadEx_11_1 extends Thread{
	
	
	
	// 상수 필드를 사용함._고정되고 변하지 않는 필드
	final static int Max_MEMORY = 1000;//전체 메모리량 표현 // 인스턴스를 만들지 않고 사용 가능 , 원래는 상수 필드는 관용적으로 대문자로 푷현하고 Snake class를 이용.
	int usedMemory = 0; // 사용된 메모리량. 
	
	@Override
	public void run() { // 하는일은 10초마다 일어나서 메모리를 차감되는 역할.
		
		while(true) { //무한루프 (계속 수행)
			try {
				Thread.sleep(10000); // 10초간 잠을 잠. // 인터럽트 호출되거나, 이 쓰레드가 실행하면 10초 대기상태
			} catch (Exception e) {
				//자다가 깬경우 바로 아래 gc(), 메모리 량을 출력. 수행 후 다시 
			}
			gc(); //메모리 청소해서 현재 메모리 용량을 확보하는 메서드.
			System.out.println("남은 메모리 량 :" + freeMemory());  //10초마다 수행해서 300씩 깐다음 현재 남은 메모리량을 계속 확인시켜줌.
			//청소하고 남은 메모리량 표시.
		}
		
	}
	public void gc() { //현재 사용되믄 메모리량을 300만큼 줄임
		usedMemory -= 300; // 10초마다 300씩 깜. // usedMemory = usedMemory -300; 
		if(usedMemory<0) { //전체 메모리가 음수는 안되므로, 해당 조건. 만약 차감을 하려고 하였으나, 메모리량이 의도치 않게 음수이면 안되므로
			usedMemory =0; 
		}
	}
	
	public int totalMemory() { //전체 메모리량을 리턴하는 메서드
		return Max_MEMORY; // "전체" 사용가능한 메모리량 반환
	} 
	
	public int freeMemory() {  // 전체메모리에서 사용된 메모리량을 뺴서
							   // 현재 가둉한 메모리량을 알아내요.
		return Max_MEMORY - usedMemory; //전체 메모리 량에 사용된 메모리 량을 차감하면서 수행.
	}
}


public class ThreadEx_11 {	
	public static void main(String[] args) {
		ThreadEx_11_1 t = new ThreadEx_11_1(); // 이쓰레드는 메인쓰레드가 만들었즘.
		
		t.setDaemon(true); //데몬 스레드로 만들어야, 이 스레드를 메인(파생시킨 스레드)가 실제로 종료하면 파생된 스레드도 같이 종료 
							// 메인쓰레드에 의해 t객체(보조 쓰레드)가 만들어짐 //   t는 메인쓰레드에 의해 -> 파생 쓰레드
		t.start(); // 10초가 잤다가 메모리 청소 수행 
		
		int requireMemory = 0; //목적 메인쓰레드를 조금 조금씩 사용하기로 함. 
		for(int i =0; i<20; i++){
			
			requireMemory  = ((int)(Math.random()*10))*20; // 난수값으로 도출. 0부터 ~ 1 사이의 실수값을 리턴함. (0은 포함하지만 1은 포함하지 않음)
			// 0 ~ 180 사이 나옴. 0, 20,40,60,... 180;
			// Math.randdom() * 10 =  0.xxxx ~ 9.9xxx 이고 다시 20 곱하면, 0.xxx ~ 180.9xxxx 
			
			// 만약 필요한 위에서 구한 필요한 메모리량 사용할 수 있는 메모리량보다 크거나, 
			// 1. 혹은 전체 메모리의 60% 이상을 사용했을 때 GC를 깨울꺼에요. =>메모리 부족.!
			
			if((t.freeMemory()<requireMemory) || t.freeMemory()<t.totalMemory() * 0.4) { //사용가능한 량보다 필요한 량이 만흔 경우와 
				//해당 조건이 메모리가 부독한 경우.//gc 메모리를 실행시켜야 메모리 청소 수행하여함. //
				t.interrupt();//인터럽트 걸어서 sleep을 중단시키고 바로 가비지 청소 수향.
				
				try {
					t.join(100); //0.1 초만 실행시키고 바로 메인쓰레드가 다시 수행.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 2. t를 실행시키는 메인스레드로 잠시 중단 후 T쓰레드를 수행
			} // 이순간에 쓰레드가 동시처리가 아닌 메인쓰레들 잠시 중지시킨후 메모리 청소 후 다시 메인 쓰레드를 다시 재개 => join 메서드로 수행해야 함.
			
			t.usedMemory += requireMemory; // 사용된 메모리량을 누적.
			System.out.println("남은 메모리량 " + t.freeMemory()); //메인 스레드에서 계속 수행시키며 메모리량이 감소시킴.
			
		}
		
	}
}
