package Exam03_Te;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
//어떻게 동작을 하는지 그 과정을 이해.

class MyRunnable implements Runnable{
	

	// 필드에서 선언
	Socket socket;
	PrintWriter pr;
	BufferedReader br; 
	
	
	
	public MyRunnable() { //기본 생성자
		// TODO Auto-generated constructor stub
	}
	
	public MyRunnable(Socket socket) { // 소켓을 하나 받아들려서 생성할 수 있는 생성자.
		super();
		this.socket = socket;
		try {
			this.pr = new PrintWriter(socket.getOutputStream());
			this.br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 실제 클라이언트 와 서버가 동작하는 작업 명시 // 서버가 한 일을 쓰레드에게 작업을 위탁.
	@Override
	public void run() {
		
		try {
			
			while(true) {
			String msg = br.readLine(); // 2번 서버측에서 데이터를 수신.
			
			pr.println(msg); // 3번 클라이언측에 내용 전달.
			pr.flush(); // 실제 전달.
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 데이터를 받는 메서드, 클리아언트가 데이터를 줄 때 까지 hold상태.
									
		
		
	}
	
}
public class Exam03_Go_Server extends Application {
	TextArea textArea; // 컴포넌트들은 다른 메서드에서도 수행되기 때문에 필드로 선언해야함.
	Button startBtn;
	Button stopBtn;
	
	ServerSocket server;
	
	private void printMsg(String msg){
		Platform.runLater(() ->{ // GUI 쓰레드에 Textarea 내용 삽입.
			textArea.appendText(msg + "\n");
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//화면 구성을 수행 , primaryStage이 실제 윈도우 창
		
		//화면을 구성할 layout을 먼저 만들기. (컴포넌트 붙일 위치, 어덯게 붙일 지 결정)
		//우리는 화면 구성을 boarderPane을 이용하여 구성할 껏예요 ! (동서남북중앙 호면 5등분)
		BorderPane root = new BorderPane(); // 기본적인 컴포넌트가 붙이는 근본적인 layout
		root.setPrefSize(700, 500);
		

		
		//실제 화면 구성을 해봄.
		textArea = new TextArea(); // TextArea 선언하면 지역변수 임므로 필드로 선언해야함.
		root.setCenter(textArea);
		
		startBtn = new Button("서버 기동"); //버튼 객체 생성
		startBtn.setPrefSize(150, 40);
		startBtn.setOnAction(e -> { 
			//이벤트 처리 코드가 나옴. (자바 람다)
			//여기가 실행될 동안 window는 잠시 block 됨. // start 이벤트는 처리될 동안 실제윈도우는 잠시 실행 멈침 -> 이벤트 처리가 끝나야지 다시 사용할 수 있도록 됨.
			//여기서 실앵이 안되면 사용자의 반응성이 문제 발생. // 응답시간을 고려해서 수행하야 함.
			
			// 하필이면, 클라이언트에 접속을 무한정 기다리는 코드가 나와야함. => 서버가 기동하면 클라이언트 응답이 기다려야함 accept() 상태. => 해당 메서드는 안끝나서 무한 대기.
			// accept()는 쓰레드를 가져야 함.ㅇ (block을 가지지 않기 위해서, 쓰레드에 적절하게 할당시켜야 함)
			// 이문제를 해결하기 위해 쓰레드를 하나 생성하여 실행시켜야 함. -> 이벤트를 쓰레드에게 맡김.
			
			// 해당 쓰레드는 서버에서 accept()를 발행하면, 동시에 다수의 클라이언트가 접속할 수 없어서, 쓰레드 안에 소켓을 생성해야 데이터 교환을 수행 할 수 있음.
			(new Thread(()-> {
				
				try { // 대기 했다가 소멧만들고, 쓰레드 생성후 시작 반복.
					server = new ServerSocket(5020); // 포트가 다른 프로그램에서 사용되고 있는 중일 수 있기 때문에 예외상황이 발생할 수 있음.
					// 이 코드는 예외처리가 강제됨.

					while(true) { // 새로운 클라이언트르 만다고 시작 수행하고 다시 대기. (클라이언트 기다리는 역할) , 프로그램이 동작이 끝날 때까지
					Socket socket = server.accept(); // 프로세스가 일단 쓰데드가 잠시 대기.// 클라이언트에 접속을 기다림 //  대기하고 있다가
				// 클라이언트가 접속하면 해당 클라이언트와 연결된 Socket 객체를 하나 생성.
				// 쓰레드가 소켓을 가지고 있어야 함.
				// 쓰레드를 하나 생성해야 함. // socket 을 이용해서 클라이언트와 서버 간의 직접 통신처리할 쓰레드를 생성.
						
					printMsg("새오운 클라이언트 접속");
					MyRunnable r = new MyRunnable(socket);	// 쓰레드를 만들기 위한 클래스가 있어야함. 위에서 클래스를 작성해야 함.
							// 해당클래스에서 소켓을 가지고 객체를 만들 수 있는 생성자를 통해 소켓을 기지고 수행.
					Thread t = new Thread(r); // 실
					t.start();
				}
				
				
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} 
				})).start(); //Thread 객체 안에서 Runnable 객체를 호출할 수 있음. 
			// Runnable 객체 안에서는 인터페이스 이므로 run() 메서드를 추상메서드 이므로 재정의하야야 함.
			// 객체를 생성할 수 있음 -> 그래서 재정의가 필요함. // 객체를 생성하여 재정의할 수 있지만, 따로 생성하지 않고,
			//해당 쓰레드에는 Runnable 객체에 run()메서드만 호출이 되므로, 람다식으로 수행이 가능)
			
			//서버 소켓을 하나 생성 및 실행.. 클라이언트의 접속을 기다려야 함.
			
			
		}); // 버튼의 이벤트 처리코드를 일단 먼저 작성 // 버튼이 클릭하나면 Event Handler 인터페이스의 handle 추상메서드 재성즤
			// 인자 E를 가지고 데이터를 처리 "람다식" // "메서드를 1'개만 가지고 수행하면되므로, 람다식 (e)을 통해 수행이 가능. 
			
	
		stopBtn = new Button("서버 중단");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> { 
			//이벤트 처리 코드가 나옴. (자바 람다)
			
		}); // 버튼의 이벤트 처리코드를 일단 먼저 작성 // 버튼이 클릭하나면 Event Handler 인터페이스의 handle 추상메서드 재성즤
			// 인자 E를 가지고 데이터를 처리 "람다식" // "메서드를 1'개만 가지고 수행하면되므로, 람다식 (e)을 통해 수행이 가능. 
			
		// Boarder pane 영역에서는 1개의 컴포넌트만 붙일 수 있으모, flow pane을 통해 2개의 버튼을 붙임.
		// 공간은 하나인떼 버튼은 2개이므로, 붙일수가 없음.
		// 일단 판자(Flow Pane)을 만들어서 버튼 2개를 차레대로 붙이고, 이 판다를 Boarder Pane 아래에 button을 붙임
		
		
		FlowPane flowPane = new FlowPane(); // 일반전적으로 클래스 타입의 인스턴스는 대부분 클래스 앞글자를 소문자로만 바꿔서 수행 (식별자는 관용적으로 소문자로 사용되기 때문에)
		// 이 판자에 버튼을 2개 붙임 그런데 설정 (padding, 정렬)을 안하면 모양이 보기가 좋지 않음. 설정이 좀 들어가야 함.
		
		flowPane.setPadding(new Insets(10,10,10,10)); // 패딩 객체가 따로 있어서 inset 객체에 공백을 상하좌우 10만큼 여백 설정.
		flowPane.setColumnHalignment(HPos.CENTER); // 정렬 관련 메서드
		flowPane.setPrefSize(100, 40);
		flowPane.setHgap(10);
		// 판자 설정이 끝났으니, 판자에 버튼을 붙어야 함.
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn); // flowpane에 버튼을 getchildern을 통해서 붙임.
		
		//Boardpane Layout 아래쪽에 붙임
		root.setBottom(flowPane);
		
		
		
		// 이런 레이앗웅을 이용해서 장면을 (Scene)을 만듬
		
		Scene scene = new Scene(root); // Scene을 레이아웃(root)에 안에 나타내기 위한 모습을 표현 
		
		// 이제 만들어진 장면을 winddow에 넣어서 띄움.
		primaryStage.setScene(scene); // Stage에 Scene을 넣어서 실제 윈도우에 표시함.
		primaryStage.setTitle("Echo Server program"); // 윈도우 창의 제목표시줄
		
		primaryStage.show(); // 창을 화면에 띄움
		
	}
	
	public static void main(String[] args) {
		//Main 쓰레드에서 으해서 최초로 실행되는 method
		// Starting entry pointf
		launch(); // GuI 쓰레드 생성. (창을 띄우고 독자적으로 수행할 수 있는 메서드 호출) / 별도의 쓰레드를 호출하여 메인쓰레드는 종료되고, 별도의 쓰레드가 동작.
	}

}
