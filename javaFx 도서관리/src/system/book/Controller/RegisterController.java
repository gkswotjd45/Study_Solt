package system.book.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import system.book.service.bookService;
import system.book.vo.MemberVO;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputID;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegist;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputPhone;

    @FXML
    private PasswordField inputPassword;

    @FXML
    void initialize() {
        assert inputID != null : "fx:id=\"inputID\" was not injected: check your FXML file 'Register.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'Register.fxml'.";
        assert btnRegist != null : "fx:id=\"btnRegist\" was not injected: check your FXML file 'Register.fxml'.";
        assert inputEmail != null : "fx:id=\"inputEmail\" was not injected: check your FXML file 'Register.fxml'.";
        assert inputPhone != null : "fx:id=\"inputPhone\" was not injected: check your FXML file 'Register.fxml'.";
        assert inputPassword != null : "fx:id=\"inputPassword\" was not injected: check your FXML file 'Register.fxml'.";
        
        btnBack.setOnAction(e->{

			Parent home_page;
			try {
				home_page = FXMLLoader.load(MainLoginController.class.getResource("../view/mainLogin.fxml"));
				Scene home_page_scene = new Scene(home_page);
				Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		        app_stage.setScene(home_page_scene);
		        app_stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        });
        
        btnRegist.setOnAction(e ->{
        	bookService service = new bookService();
    		MemberVO member = new MemberVO(
					inputID.getText().toString(),
					inputPassword.getText().toString(),
					inputEmail.getText().toString(),
					inputPhone.getText().toString());
        	
        	int result = service.getRegister(member);
        	System.out.println("가입이 1건 수행하였습니다.");
        	
//        	service.set
        	if(result==1) {
        		Dialog<String>dialog =new Dialog<String>();
				dialog.setTitle("로그인 등록결과");
				ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
				
				StringBuffer sb = new StringBuffer();
				sb.append(member.getmID() + "님이 가입하셨습니다.\n");
			
				dialog.setContentText(sb.toString());
			    dialog.getDialogPane().getButtonTypes().add(type);
			    dialog.getDialogPane().setMinHeight(100);
	            dialog.showAndWait();
        	}
        });

    }
}
