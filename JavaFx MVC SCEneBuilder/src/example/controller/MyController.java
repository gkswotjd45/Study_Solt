package example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

// javaFx view로 연결된 Controller 예요. 
// view가 생성되면 자동적으로 Controller객체도 자동적으로 생성되요.
// 이 controller안에서 버튼에 대한 이벤트처리를 해야함.
// 여기에.. 버튼에 대한 reference가 있어야. . 버튼 클릭 처리를 하던 말던하겠죠..
public class MyController implements Initializable {

	// 컨트롤러를 초기화 시키기 위해서 Initializable 인터페이스를 구현하도록 함.
	
	@FXML private Button upperBtn; // @fxml 어노테이션 = xml로 연결된 Button 필드 생성.
	@FXML private Button bottomBtn;
	
	public MyController() {
		System.out.println("constructor가 호출");
	}

	// 이제는 fxml로 연결된 controller에서 실제 수행하는 작업을 작성.
	// 이제는 Controller 1개당 View 1개로 나타냄. 이벤트 처리와 결과 처리를 독단적으로 수행이 가능. 1대1매칭 
	// 아까는 이벤트마다 각각의 Controller 필요. 아까는 view에서 이벤트처리와 결과처리를 하였음.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 초괴화할 내용이 있으면 여기에 작성.
		// 일반적으로 이벤트 등록 처리코드가 나옴.
		upperBtn.setOnAction(e->{
			// Serivce 객체를 만들어서 일을 시키면 되요.
		});
		
		bottomBtn.setOnAction(e->{
			
		});
	}
}
