package Exam03_Te;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

public class Exam03_Go_Client extends Application{
	
	TextArea textArea;
	TextField ipTextField;
	TextField idTextField;
	Button connBtn;
	TextField chatTextField;
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br;
	
	private void printMsg(String msg){
		Platform.runLater(() ->{
			textArea.appendText(msg + "\n");
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
	
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		FlowPane upFlowPane = new FlowPane();
		
		upFlowPane .setPadding(new Insets(10,10,10,10)); // 패딩 객체가 따로 있어서 inset 객체에 공백을 상하좌우 10만큼 여백 설정.
		upFlowPane .setColumnHalignment(HPos.CENTER); // 정렬 관련 메서드
		upFlowPane .setPrefSize(100, 40);
		upFlowPane .setHgap(10);

		ipTextField = new TextField();
		ipTextField.setPrefSize(200, 40);
	
		connBtn = new Button("서버접속");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> { 
			// 서버에 접속.
			// 특정 IP와 port 번호를 이용해서 Socket객체를 생성을 시도.
			// 만역 성공하면 서버와 연결된 socket 객체를 하나 얻어요.!
			try {
				socket= new Socket(ipTextField.getText(),5020); // 접속 시도. (서버측에 젒촉 시도) 이후 성공하면 소켓에 생성.
				// 연결이 되면 클라이언트에서 데이터 스트림 보내고, 서버측에서 받고, 서브측에서 보내고, 클라이언트에서 받음.
				pr = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		upFlowPane.getChildren().add(ipTextField);
		upFlowPane.getChildren().add(connBtn);
		
		
		
		
		
		FlowPane bottomFlowPane = new FlowPane();
		
		bottomFlowPane .setPadding(new Insets(10,10,10,10)); // 패딩 객체가 따로 있어서 inset 객체에 공백을 상하좌우 10만큼 여백 설정.
		bottomFlowPane .setColumnHalignment(HPos.CENTER); // 정렬 관련 메서드
		bottomFlowPane.setPrefSize(100, 40);
		bottomFlowPane .setHgap(10);
		
		idTextField = new TextField();
		idTextField.setPrefSize(150, 40);
		
		
		chatTextField = new TextField();
		chatTextField.setPrefSize(300, 40);
		
		chatTextField.setOnAction(e->{
			// 채팅입력창에서 Enter를 치면, action 이벤트가 발생해서 이 코드가 실행.
			// action event가 발생.
			String id = idTextField.getText(); // 상자에 있는 내용 갖고오기
			String msg = chatTextField.getText();
			
			pr.println(id + "> "+ msg);  // 1번 과정 클라이언트 측에 데이터 전송.
			pr.flush(); // 실제 전공.
			
			try {
				String receive = br.readLine();
				printMsg(receive);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		bottomFlowPane.getChildren().add(idTextField);
		bottomFlowPane.getChildren().add(chatTextField);
		
		
		
		root.setTop(upFlowPane);
		root.setBottom(bottomFlowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Client");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
