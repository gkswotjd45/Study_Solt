package Exam01;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class Exam01_DataCilent extends Application { //주석을 보고 어떤 의미으로 진행하는 지 파악 정도. // 절차 위주로 어떤 프로세스를 주고 받을 수 있는지 파악.

	//field //ctrl + shift + o import문 
	TextArea textarea;
	Button connbtn;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 객체를 이 안에서 화면 구성 예정.   //board pane = layout의 각각의 항목의 위치를 할당해주는 역할. (5개의 영역으로 화면을 분할)
		// 글상자(textarea)는 center에 붙임. button 은 south에 붙임 
		// 일단 레이아웃부터 만듬.
		
		BorderPane root = new BorderPane(); //가로 700, 500 픽셀
		root.setPrefSize(700, 500);
		
		// component 생성 - 우리 눈에 보이는 체크박스, 버튼. 이런 것들을 말함.
		textarea = new TextArea();
		root.setCenter(textarea); // 가운에 textarea 부착
		connbtn = new Button("Data 서버 접속");
		connbtn.setPrefSize(150, 40); // 사이즈를 지정할 때
		connbtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// 버튼을 클릭하면 하는 일을 작성.
				// 서버와 접속하는 코드를 만들어 주면 됨.
				textarea.clear(); // textarea안의 내용을 싹다 다지움
				
				// 서버에 접속해요. => Socket객체 생성 시도.
				// 클릭버튼을 클릭시 현재 서버쪽에서의 소켓 의 (포트 3000)에 접촉시킴 => new 소켓객체를 만들어야 함. => 소켓이 만들어짐 유무에 따라 접속 성공 여부가 달림.
				// 서버에 접촉하려면 ip주소와 포트번호를 알아야함 함. => 네트워크 내에 나가서 내 컴퓨터를 찾아야 함 (lookBack)
				// 자신을 찾을 때 사용하는 ip주소 127.0.0.1, localhost
				try {
					Socket s = new Socket("127.0.0.1",3001); // 성공하면 socket s 만듬
					System.out.println("서버에 접속 성공");
					
					
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 받은 데이터 통로 생성 후 데이터를 방아야 함.
					// Buffered Reader를 생성.
				
					String msg = br.readLine(); // 통로로 부터 데이터를 라인 단위로 데이터를 읽는다.
												// 시간상 동시에 일어나지 않음, 누군가는 조금이라도 먼저 받을 것임.
												// 관을 통해 보내기 전에, 받는 측에서 먼저 수행이 되서, 보내는 주체는 아직 안 보냄 => 받는측에서 아직 기다림.
												// 클라이언트는 어떤 경우라도 수신 o
					System.out.println(msg);
					
					br.close();
					s.close();
					
					System.out.println("서버와 연결 종료!");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //127.0.0.1 - 나 자신을 가르키는 ip 주소, "local host 주소",
				
				
				
			}
		}); //버튼이 눌렀을 때 오버라이딩하여 수행
		
		//일단 South도 레이아웃 선언 (layoutCompoment 붙일 수 있는 객체) - > Flow pane => (컴포넌트들이 왼쪽부터 차례대로 부착) = 미리 공간을 확보할 수 있는 레이아웃.
		// flowPane 부터 만듬
		FlowPane flowpane = new FlowPane(); // 아래쪽 영역에 붙는 layout
		// 여백좀 잡아서 그나마 좀 보기 좋게 만들어 보아요!
		flowpane.setPadding(new Insets(10,10,10,10) ); //남은 여백 설정. 10 만큼 상하 좌우의 공간 설정.
		flowpane.setColumnHalignment(HPos.CENTER); // 가로로 어떻게 정렬할지 결정, 가운데 정렬로 붙음.
		flowpane.setPrefSize(700, 400); //flow pane 크기가 가로 700, 세로 40
		flowpane.setHgap(10); // 여백 설정. // 모양 예브게 만듬.
		
		//버튼을 flowpane에 붙임 
		flowpane.getChildren().add(connbtn);
		
		//이렇게 만든 flowpane (판때기) border 아래쪽에 붙여야 함.
		root.setBottom(flowpane); 
		
		Scene scene = new Scene(root); //씬은 root 안으로 삽입 시킴
		primaryStage.setScene(scene);
		primaryStage.show();
		
 	}
	
	
	public static void main(String[] args) {
		launch();
	}
}
