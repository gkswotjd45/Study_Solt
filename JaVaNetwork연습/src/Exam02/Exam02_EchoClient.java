package Exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam02_EchoClient extends Application {

	TextArea textArea; // 여러줄 입력필드
	Button connBtn;
	TextField textfield;// 한줄 짜리 입력필드
	TextField idfield;
	
	Socket s;
	PrintWriter pr;
	BufferedReader br;
	
	private void printMsg(String msg) {  //쓰레드가 왜 필요한지, 어던 방식으로 수행 되는지, 결과는 어던지, 왜 문제가 발생되었는지....
		// GUI 특성상 내부 쓰레드를 사용하기 때문에, (컴포넌트를 제어하기) runlater() 수행
					Platform.runLater(() -> {
						textArea.appendText(msg + "\n");// textarea는 여러 줄이 사용하기 때문에 문장 자체 를 넣어주는 메서드 //
					});  // 익명객체를 생성 후 run 메서드를 오버라이딩 하여 run() 메서드 내용을 수행할 수 있도록 함.
												  // static 메서드 (특수한 기능을 제공). 쓰레드를 만드는 메서드, 따라서 해당 메서드 안에 Runnable 인터페이스를 객체를 삽입.
										 	     // Runnable 인터페이스를 Run() 오버라이딩을 하여 해당 쓰레드가 동작하는 해야 과정을 담음.
					
	}
	
	@Override
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
		
		connBtn = new Button("서버 접속");
		connBtn.setPrefSize(150, 40); // 버튼 가로 세로 설정.
		// 이벤트 처리는 Listener 객체(Handler 객체)가 담당 -> delegation model
		
		connBtn.setOnAction(e -> { //handle 메서드는 block 메서드 임.
			try {
				s = new Socket("127.0.0.1", 5010);  // 예외상황이 발생을 미연에 방지
				printMsg("서버에 연결이 성공했습니다,");
				textfield.setDisable(false); //textarea 모습이 보임.
				pr = new PrintWriter(s.getOutputStream()); // 소켓을 통해서 출력스트림을 여는것.
				br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 소켓을 통해서 입력스트림을 여는 것.
				
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();	
			}
			
			
		}); // 버튼 클릭시 이벤트 처리. // -> "축약해서 표현" Handler 오버라이딩으로 수행. 객체 e로 진행.
		
		
		idfield = new TextField();
		idfield.setPrefSize(200, 40);
		
		
		textfield = new TextField();
		textfield.setPrefSize(200, 40);
		textfield.setDisable(true); // 사용할수 없도록 형태로 만듬. 
		textfield.setOnAction(e -> { // textfield 입력 받고 enter 누르면 입력 받으면 삭제
			
			String msg = textfield.getText(); // msg 문자열에 textfield 에 텍스트 필드 삽입.
			String id = idfield.getText();
			
			
			pr.println(id + "> " + msg); // 소켓과 연결된 통로에 내가 얻은 메시지를 전송
			
			pr.flush();// 실제 전송.
			if(!msg.equals("/exit")) { // 내가 했던게 끝난게 아니면. 
			try { // 읽어드린 메시지를 가지고 출력
				printMsg(br.readLine()); // 클라이언트는 또다시 기다림. 데이터를 받을 때 까지 무한 데기상태인 메서드
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}else {
				printMsg("클라이언트가 서버와 연결이 종료되었어요."); // 서버가 종료가 클라이언트도 사용할 수 없기 때문에..
			}
		});
		
		
		FlowPane flowpane = new FlowPane(); // 하단에 버튼 2개를 부착할 용도. // 처음에는 버튼이 좌측 상단에 붙음(초기값)
		flowpane.setPadding(new Insets(10,10,10,10));  //flowpane 패딩은 안쪽 여백 (상하 좌우 각 여백(간격)을 지칭) 지정안하면 위쪽에 붙음 
													   /// margin 바깥쪽 여백.(layout의 바깥쪽 여백을 지칭)
		
		flowpane.setColumnHalignment(HPos.CENTER); // 컬럼에 때한  로 정령
		flowpane.setHgap(10); //Hgap은 각 컴포넌트 간격을 지칭.
		//flowpane.getChildren().add(flowpane); // flowpane에게 getchildern을 통해 붙임
		
		flowpane.getChildren().add(connBtn);
		flowpane.getChildren().add(idfield);
		flowpane.getChildren().add(textfield);
		
		
		root.setBottom(flowpane); // boroader Pane에 flowpane을 부착.
		Scene scene = new Scene(root); //Scene에게 layout 설정.
		primaryStage.setScene(scene);  // 
		
		
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show(); // 창을 화면에 뜨움
		
		
	}
	public static void main(String[] args) {
		launch();
	}
	
}
