package NetWork_part2_Echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Network_server extends Application {

	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	ServerSocket serverSocket;
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
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); //레이아웃 설정
		textArea = new TextArea();
		root.setCenter(textArea); 
		
		startBtn = new Button();
		startBtn.setPrefSize(150, 40);
		
		startBtn.setOnAction(e -> {
			
			printMsg("서버가 시작 되었어요.");
			 try {
				serverSocket = new ServerSocket(5010);
				printMsg("클라이언트에 성공적으로 연결하였습니다.")	;
				pr = new PrintWriter(s.getOutputStream());// 소켓에 outputstream을 통해 PrintWriter을 통해 출력함.
				br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 소켓에 inputstream을 통해 getinputsteamReader 호출 후 buffered
									//Reader을 통해 입력 스트림 생성.
				
			 } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // 서버소켓에 5010 요청받아서 Socket s 생성.
			
			
		});
		
		stopBtn = new Button();
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(null);
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10)); //1.Insets 메서드 
		flowPane.setColumnHalignment(HPos.CENTER);// 가로정렬을 센터에 맞춘다.
		flowPane.setHgap(10); // 각 컴포넌트의 간격을 지칭.
		
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		root.setBottom(flowPane); // Boarder layout에 flowpane 하단 배치
		Scene scene = new Scene(root); // Scene 객체에 root첨가
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Sever program");
		
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch();

	}
}
