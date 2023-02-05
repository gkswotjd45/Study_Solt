package system.book.Controller;

/**
 * Sample Skeleton for 'bookRental.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.book.service.bookService;
import system.book.vo.BookVO;

public class BookRentalController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtBookName"
	private TextField txtBookName; // Value injected by FXMLLoader

	@FXML // fx:id="btnSearch"
	private Button btnSearch; // Value injected by FXMLLoader

	@FXML // fx:id="bookListTable"
	private TableView<BookVO> bookListTable; // Value injected by FXMLLoader

	@FXML // fx:id="btnReturn"
	private Button btnReturn; // Value injected by FXMLLoader

	@FXML // fx:id="btnRental"
	private Button btnRental; // Value injected by FXMLLoader

	@FXML // fx:id="btnRentalList"
	private Button btnRentalList; // Value injected by FXMLLoader

	@FXML
	private TableColumn<BookVO, String> bIsbncategory;

	@FXML
	private TableColumn<BookVO, String> bAuthorcategory;

	@FXML
	private TableColumn<BookVO, String> bDatecategory;

	@FXML
	private TableColumn<BookVO, Integer> bPricecategory;

	@FXML
	private TableColumn<BookVO, String> rentalCheckcategory;

	@FXML
	private TableColumn<BookVO, String> bTitlecategory;

	@FXML
	private TableColumn<BookVO, String> rentalDatecategory;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'bookRental.fxml'.";
		assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'bookRental.fxml'.";
		assert bookListTable != null
				: "fx:id=\"bookListTable\" was not injected: check your FXML file 'bookRental.fxml'.";
		assert btnReturn != null : "fx:id=\"btnReturn\" was not injected: check your FXML file 'bookRental.fxml'.";
		assert btnRental != null : "fx:id=\"btnRental\" was not injected: check your FXML file 'bookRental.fxml'.";
		assert btnRentalList != null
				: "fx:id=\"btnRentalList\" was not injected: check your FXML file 'bookRental.fxml'.";
		bIsbncategory.setCellValueFactory(new PropertyValueFactory<BookVO, String>("bisbn"));
		bTitlecategory.setCellValueFactory(new PropertyValueFactory<BookVO, String>("btitle"));
		bAuthorcategory.setCellValueFactory(new PropertyValueFactory<BookVO, String>("bauthor"));
		bDatecategory.setCellValueFactory(new PropertyValueFactory<BookVO, String>("bdate"));
		rentalDatecategory.setCellValueFactory(new PropertyValueFactory<BookVO, String>("rentalDate"));
		rentalCheckcategory.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRentalchecked()));
		bPricecategory.setCellValueFactory(new PropertyValueFactory<BookVO, Integer>("bprice"));
		
	}

	@FXML
	void keywordBook(ActionEvent event) {
		btnSearch.setOnAction(e -> {
			bookService service = new bookService();
			ArrayList<BookVO> list = service.selectBooksByKeyword(txtBookName.getText().toString());
			ObservableList<BookVO> olist = FXCollections.observableArrayList(list);
			bookListTable.setItems(olist);
			System.out.println(olist);
		});
	}

}
