package example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//기존에는 화면구성을 일일이 다 자바코드를 작성.
		//화면은 이미 xml로 만들어져서 있어요. 그거 가지고 오면 되요.
		
		//Scene Builder로 만들어진 한번 확인.
		Parent root = null;
		//fxml를 로더해야 하기 때문에 Loader를 만들어야 함.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("myView.fxml")); //메인 클래스에 현재 resource 위치를 지정.
		root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch();
	}
}
