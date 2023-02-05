package system.book.Controller;

import java.io.IOException;

/**
 * Sample Skeleton for 'home.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import system.book.vo.MemberVO;

public class HomeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtMemberID"
    private Label txtMemberID; // Value injected by FXMLLoader

    @FXML // fx:id="txtPoint"
    private Label txtPoint; // Value injected by FXMLLoader

    @FXML // fx:id="btnMymeber"
    private Button btnMymeber; // Value injected by FXMLLoader

    @FXML // fx:id="btnManage"
    private Button btnManage; // Value injected by FXMLLoader

    @FXML // fx:id="btneAllmember"
    private Button btneAllmember; // Value injected by FXMLLoader

    @FXML // fx:id="btnBookRental"
    private Button btnBookRental; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtMemberID != null : "fx:id=\"txtMemberID\" was not injected: check your FXML file 'home.fxml'.";
        assert txtPoint != null : "fx:id=\"txtPoint\" was not injected: check your FXML file 'home.fxml'.";
        assert btnMymeber != null : "fx:id=\"btnMymeber\" was not injected: check your FXML file 'home.fxml'.";
        assert btnManage != null : "fx:id=\"btnManage\" was not injected: check your FXML file 'home.fxml'.";
        assert btneAllmember != null : "fx:id=\"btneAllmember\" was not injected: check your FXML file 'home.fxml'.";
        assert btnBookRental != null : "fx:id=\"btnBookRental\" was not injected: check your FXML file 'home.fxml'.";
        
        MemberVO member = MainLoginController.getUser();
        if(member.getmID().equals("admin")) {
//        	btnMymeber.setVisible(false);//보이지 않게 함.
        	btnMymeber.setDisable(true); // 버튼을 비활성화
//        	btnBookRental.setVisible(true);//보이지 않게 함.
        	btnBookRental.setDisable(true); //버튼을 비활성화
        	
        }else {
//        	btneAllmember.setVisible(false);//보이지 않게 함.
        	btneAllmember.setDisable(true); // 버튼을 비활성화
//        	btnManage.setVisible(true);//보이지 않게 함.
        	btnManage.setDisable(true); //버튼을 비활성화
        }
        
        txtMemberID.setText(member.getmID().toString());
        btnBookRental.setOnAction(e ->{
        	Parent BookRentalpage;
			try {
				BookRentalpage = FXMLLoader.load(MainLoginController.class.getResource("../view/bookRental.fxml"));
				Scene home_page_scene = new Scene(BookRentalpage);
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
