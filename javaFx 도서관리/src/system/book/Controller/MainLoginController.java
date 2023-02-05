package system.book.Controller;


import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MainLoginController implements Initializable {
  
  
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML
    private TextField inID;
    @FXML
    private PasswordField inPassword;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnRegister;
    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URI location;
    
    private static MemberVO User;

	public static MemberVO getUser() {
		return User;
	}

	public void setUser(MemberVO user) {
		User = user;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btnLogin.setOnAction(e ->{
			
			System.out.println("test");
			
			bookService service = new bookService();
			MemberVO member = service.getLogin(inID.getText().toString(), inPassword.getText().toString());
			setUser(member);
//			User = member;
			
			Dialog<String>dialog =new Dialog<String>();
			dialog.setTitle("로그인 결과");
			ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			
			//StringBuffer sb = new StringBuffer();
			//sb.append(member.getmID() + "님이 로그인 하셨습니다.\n");
			dialog.setContentText(member.getmID() + "님이 로그인 하셨습니다.\n");
		    dialog.getDialogPane().getButtonTypes().add(type);
		    dialog.getDialogPane().setMinHeight(100);
            dialog.showAndWait();
            
			try {
				
				Parent home_page = FXMLLoader.load(MainLoginController.class.getResource("../view/home.fxml"));
				Scene home_page_scene = new Scene(home_page);
				Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		        app_stage.setScene(home_page_scene);
		        app_stage.show();  
		        
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		btnRegister.setOnAction(e ->{
//			System.out.println("test");
			Parent Registerpage;
			try {
				Registerpage = FXMLLoader.load(MainLoginController.class.getResource("../view/Register.fxml"));
				Scene home_page_scene = new Scene(Registerpage);
				Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		        app_stage.setScene(home_page_scene);
		        app_stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}


}
