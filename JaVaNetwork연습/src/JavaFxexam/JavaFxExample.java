package JavaFxexam;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class JavaFxExample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {  //스레드할 때 Start() 메서드아님. java Fx에서 특결한 기능을 하는 메서드 오버라이딩.
			// Stage 클래스는 primaryStage가 실제 윈도우 역할 (화면의 창의) 역할 (Stage 윈도우 자체를 의미) -> Scene 화면의 크기를 제공 -> layout 버튼을 구성
			//위치 )
		//화면 구성하는 작업을 여거서 함.. (창을 구성하는 컴포넌트(입력,출력, 버튼)를 어떻게 구성할지 메서드)
		Button btn = new Button();
		btn.setText("안녕");
		// button에 이벤트 처리를 해야함.
		// java는 delegation model을 이용해요. => Web의 javaScript도 이방식을 이용.
	
		// 버튼에 Action이라는 Event를 처리할 수 있는 Listener 객체를 붙임.
		btn.setOnAction(new EventHandler<ActionEvent>() { //EventHandler 은 인터페이스 이므로 Handle 오버라이딩을 하여 수행. 이 객체가 수행 
															// handle 라는 내용이 수행 됨.
														 // Action이라는 이벤트를 갖고.
			@Override
			public void handle(ActionEvent arg0) {
				// Event가 발생하면 자동으로 호출되는 군요.
				System.out.println("안녕하세요");
			}
		}); 
		
		
		// Layout을 설정.(컴포넌트가 붙는 방식을 결정하는 객체)
		StackPane root = new StackPane(); // 투명한 판때기에 버튼을 붙이는 방식. 내가 원하는 위치 싶은 곳에 붙일 쑤 있는 용도. (layout)가 위치를 조정.
										  // layout에 컴포넌트 아무 위치 시킬 수 있는 용도. 
									      // Stack Pan은 layout중 추가되는 순서대로 덧붙여서 시각화하는 layout
		
		root.getChildren().add(btn); // 직접적인 붙이는 것이 아니라, 하단 객체(getchideren)을 통해 붙임.
		
		
		//scene 객체를 생성 
		Scene scene = new Scene(root,300,150); //장면의 크기는 가로 세로 지정정.
		primaryStage.setTitle("연습입니다."); // 윈도우 제목표시줄에 생성.
		primaryStage.setScene(scene); // 윈도우 객체안에 씬과 결합
		primaryStage.show(); // 창을 띄움.
		
		
	}
	
	public static void main(String[] args) {
		launch(); // static 메서드, 해당 메서드는 Application으로 파생된 메서드 (특수한 가능을 하는 메서드)것을 파악., GUI 쓰레드를 생성 후 시작.
				  //메인쓰레드와 별개인 쓰레드를 별도로 생성하는 형태. GuI 쓰레드가 실행되고 화면에 출력. 단 메인쓰레드는 죽지만 Lunch(); 쓰레드는 수행.
		
	}

}
