package system.book.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = null;
		//fxml를 로더해야 하기 때문에 Loader를 만들어야 함.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLogin.fxml")); //메인 클래스에 현재 resource 위치를 지정.
		root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
