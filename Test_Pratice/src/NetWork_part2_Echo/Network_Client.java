package NetWork;

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

public class Network_Client extends Application{

	TextArea textArea;
	Button conBtn;
	TextField textfiled;
	TextField idfield;
	
	Socket s;
	PrintWriter pr;
	BufferedReader br;
	
	//GUI에 textarea에 넣는 메서드
	private void printMag(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n"); //textArea에 메시지를 추가하는 메서드
			
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		textArea = new TextArea();
		root.setCenter(textArea); // boardPane에 textarea 중앙에 삽입
		
		conBtn = new Button("서버 접속");
		conBtn.setPrefSize(150, 40);
		
		conBtn.setOnAction(e->{
			try {
				new Socket("127.0.0.1",5001);
				printMag("서버에 연결이 되었습니다.");
				
				pr = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		idfield = new TextField();
		idfield.setPrefSize(200, 40);
		
		textfiled = new TextField();
		textfiled.setPrefSize(200, 40);
		textfiled.setDisable(true);
		textfiled.setOnAction(e -> {
			
			String msg = textfiled.getText();
			String id = idfield.getText();
			
			pr.println(id+ " > " +msg);// 소켓과 연결된 통로에  내가얻은 메시지 전송,
			pr.flush(); // printWriter를 통해 실제 전송
			if(!msg.equals("/exit")) {
				try {
					printMag(br.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}	else {
				printMag("클라이언트가 서버와 연결이 종료 되었다");
			}
			
		});
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));  //flowpane 패딩은 안쪽 여백 (상하 좌우 각 여백(간격)을 지칭) 지정안하면 위쪽에 붙음 
		   /// margin 바깥쪽 여백.(layout의 바깥쪽 여백을 지칭)

		flowpane.setColumnHalignment(HPos.CENTER); // 컬럼에 때한  로 정령
		flowpane.setHgap(10); //Hgap은 각 컴포넌트 간격을 지칭.
//flowpane.getChildren().add(flowpane); // flowpane에게 getchildern을 통해 붙임

		flowpane.getChildren().add(conBtn);
		flowpane.getChildren().add(idfield);
		flowpane.getChildren().add(textfiled);


		root.setBottom(flowpane); // boroader Pane에 flowpane을 부착.
		Scene scene = new Scene(root); //Scene에게 layout 설정.
		primaryStage.setScene(scene);

		primaryStage.setTitle("Echo Client Program");
		primaryStage.show(); // 창을 화면에 뜨움	
		
	}

	public static void main(String[] args) {
		launch();
	}

}
