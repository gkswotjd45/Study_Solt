package MultiplyEcho;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class EchoRunnable implements Runnable{
	// 소켓객체를 필드로 초기값
	PrintWriter pr;
	BufferedReader br;
	Socket socket;
	
	
	public EchoRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	public EchoRunnable(Socket socket) { // 객체 초기화 수행시 출력, 버퍼 스트림 초기화
		this.socket = socket;
		
		try {
			pr = new PrintWriter(socket.getOutputStream()); //출력 스트림
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		
		while(true) { // 계속 textArea의 입력을 계속 기다리는 상태로 유지 why? Run() 메서드가 종료되면 해당 클라이언트는 쓰레드로 생성되어 
					  // 다시 서버와 연렬을 할 수 없기 때문에, 계속해서 run() 유지하는 과정을 수행.
		String msg =null;
		
		try {
			msg = br.readLine();// BufferedReaer를 통해 한줄 단위로 읽어서 msg에 저장
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(msg.equals("/exit")) {
			break;
		}
		
		pr.println(msg);
		pr.flush();
		
		}
	}
}

// 서버 소켓을 연결하는 방법을 안보고 풀 수 있도록 함 (반복 연습)
public class Ser extends Application {  //자바 GUI 보여주기 위해 application 상속 (추상클래스)
	
	// 컴포넌트들은 필드로 선언., 해달 클래스에서 계속 사용할 수 있도록 (지역변수 해당 메서드에서 선언하면, 메서드 종료시 사라짐으로) 지정.
	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	ServerSocket server;// 서버소켓을 단순히 메서드 내에서 변순 선언하면 해당 메서드 종료 하면 객체가 사라짐 
	Socket s;
	PrintWriter pr; //출력 스트림
	BufferedReader br; // 입력스트림
	
	// 우리클래스 내에서 사용되기 때문에 해당 메서드 private를 사용함. 
	private void printMsg(String msg) {  //쓰레드가 왜 필요한지, 어던 방식으로 수행 되는지, 결과는 어던지, 왜 문제가 발생되었는지....
		// GUI 특성상 내부 쓰레드를 사용하기 때문에, (컴포넌트를 제어하기) runlater() 수행
					Platform.runLater(() -> {
						textArea.appendText(msg + "\n");// textarea는 여러 줄이 사용하기 때문에 문장 자체 를 넣어주는 메서드 //
					});  // 익명객체를 생성 후 run 메서드를 오버라이딩 하여 run() 메서드 내용을 수행할 수 있도록 함.
												  // static 메서드 (특수한 기능을 제공). 쓰레드를 만드는 메서드, 따라서 해당 메서드 안에 Runnable 인터페이스를 객체를 삽입.
										 	     // Runnable 인터페이스를 Run() 오버라이딩을 하여 해당 쓰레드가 동작하는 해야 과정을 담음.
					
	}
			
	@Override  //추상클래스 옹버라이딩 //실제로 윈도우(args)를 만들고 창을 띄우는 메서드
	
	public void start(Stage primaryStage) throws Exception {
		
		
		//창의 화면구성을 하게 됨.
		// 일단은 ~pane 이라고 불리는 layout이 먼저 있어여 함. ex) StackPane, BorderPane (상하좌우 중앙 5개 영역), FlowPance (좌측부처 타례대로 부탁)
		// 해당 pane은 뒤쪽에 Scene에 부착. // Scene은 뒤쪽에 Stage(윈도우에 뜨는 창)에 붙여서 수행. 
		// 1. Boarder pane에 TextArea 부착 2. flowlayout에 Button 2개 부착후 하단에 배치
		// Layout을 설정
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); // 전체 창의 크기. (Layout 가로 세로 크기)
		textArea = new TextArea();
		root.setCenter(textArea); // textarea를 boarderpane 중아에 부착.
		
		startBtn = new Button("서버 시작");
		startBtn.setPrefSize(150, 40); // 버튼 가로 세로 설정.
		// 이벤트 처리는 Listener 객체(Handler 객체)가 담당 -> delegation model
		
		startBtn.setOnAction(e -> { //handle 메서드는 block 메서드 임.
			
			//blocking method! 실행되는 동안 수행이 잠시 중지되요! -> 해당 메서드가 수행시간이 오래걸리면 약간 문제가 됨. 이 메서드가 끝날때 까지 홀드가 발생 됨.
			//Textarea 값이 방대하면 서버 중지 버튼은 그 동안에는 아무 작업도 수행할 수 없음. => 비동기로 제어해아함. (각각 알아서 실앻) = Thread 처리로 수행.
			//순차처리를 안하기 위해 당연히 Thread를 사용해야 함.
			
			
			printMsg("서버가 시작되었어요!");
			try {
				server = new ServerSocket(5001);
				printMsg("클라이언트 접속대기중!!!");
				
				(new Thread(() -> {
					try {
						while(true) {
							s = server.accept();
							printMsg("새로운 클라이언트 접속 성공!!");
							(new Thread(new EchoRunnable(s))).start();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				})).start();				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}); // 버튼 클릭시 이벤트 처리. // -> "축약해서 표현" Handler 오버라이딩으로 수행. 객체 e로 진행.
		
		
		stopBtn = new Button("서버 중지");
		stopBtn.setPrefSize(150, 40); 
		stopBtn.setOnAction(null);
		
		
		FlowPane flowpane = new FlowPane(); // 하단에 버튼 2개를 부착할 용도. // 처음에는 버튼이 좌측 상단에 붙음(초기값)
		flowpane.setPadding(new Insets(10,10,10,10));  //flowpane 패딩은 안쪽 여백 (상하 좌우 각 여백(간격)을 지칭) 지정안하면 위쪽에 붙음 
													   /// margin 바깥쪽 여백.(layout의 바깥쪽 여백을 지칭)
		
		flowpane.setColumnHalignment(HPos.CENTER); // 컬럼에 때한  로 정령
		flowpane.setHgap(10); //Hgap은 각 컴포넌트 간격을 지칭.
		//flowpane.getChildren().add(flowpane); // flowpane에게 getchildern을 통해 붙임
		
		flowpane.getChildren().add(startBtn);
		flowpane.getChildren().add(stopBtn);
		
		
		root.setBottom(flowpane); // boroader Pane에 flowpane을 부착.
		Scene scene = new Scene(root); //Scene에게 layout 설정.
		primaryStage.setScene(scene);  // 
		
		
		primaryStage.setTitle("Echo Server Program");
		primaryStage.show(); // 창을 화면에 뜨움
		
	}
	
	public static void main(String[] args) { //메인쓰레드로 인해 실제 실행할 내용 
		launch(args); // GUI Thread가 생성되서 우리 창이 싱행. (내부적으로 쓰레드를 만들어서, start() 메서드를 호출 해서 창을 띄움
	}
}
