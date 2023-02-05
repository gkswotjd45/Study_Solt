package example.view;



import example.controller.Button1Controller;
import example.controller.Button2Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView extends Application{

	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// stage는 하나의 창을 생성 (준비 됨)
		// 이제 화면 구성을 하면 됨.
		
		//layout은 borderpane으로 사용.
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		Button btn1 = new Button("위쪽 버튼");
		Button btn2 = new Button("아래쪽 버튼");
		
		//이 코드는 View가 아니라, Controller에서 나와야 함.
		// 하지만, 지금 같은 경우는 방법이 없음.
		// 어쩔수 없이 여기에서 View에서 이벤트 처리코도그 나와야 함.
		btn1.setOnAction(e->{
			
			Button1Controller controller = new Button1Controller(); //이 컨트롤러를 통해 임의로 수행할 동작을 넘기는 방식.
			String str = controller.getReuslt();
			
			// 결과를 받은 str을 이용해서 화면을 제어. (사실 컨트롤러에서 처리를 해야하지만, View에서 처리 결과를 받아서 수행.
		});
		
		
		btn2.setOnAction(e->{ // 컨트롤러가 이벤트마다 붙음 (각각의 버튼마다 컨트롤러가 생성 단, serivce는 1개에서 별도의 메서드를 지정)
			Button2Controller controller = new Button2Controller();
			String str = controller.getReuslt();
		});
		
		root.setTop(btn1);
		root.setBottom(btn2);
		
		Scene scene = new Scene(root); // scene에 layout을 붙임.
		primaryStage.setScene(scene); // 창에 scene에 붙임.
		primaryStage.show();
		
		
		
	}
	
}
