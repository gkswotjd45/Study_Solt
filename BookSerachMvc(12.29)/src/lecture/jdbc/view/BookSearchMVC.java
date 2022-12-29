package lecture.jdbc.view;






import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.controller.BookserachByKeywordController;
import lecture.jdbc.controller.DeleteByisbnController;
import lecture.jdbc.vo.BookVO;

// tableView에서 row를 더블클릭하면 해당 책의 정보 (출판일, 페이지수, 출판사, 등등)를 dialog를 통해 화면에 창을 띄우기




// 코드가 중복되지 않아 유지보수가 용이.
// MVC에 View에 해당하는 형태.
public class BookSearchMVC extends Application {


	
	
	TableView<BookVO> tableView;	
	TextField textField;
	Button deleteBtn;
	
	String deleteISBN;
	String searchKeyword;
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 1. layout부터 설정해야 해요!
		// BorderPane을 이용할 꺼예요!(동서남북중앙)
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. BorderPane 아래쪽에 붙일 판자(FlowPane)를 하나 생성, 속성 설정
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 3. 각각의 component를 생성해서 붙여요!
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		textField.setOnAction(e -> {
			// 여기에 이벤트 처리가 들어와야 함.
			// Controller를 이용해서 service에 로직 실행 요청을 해야 함.
			// 먼저 Controller 객체를 생성할 꺼임.(검색 작업을 수행할 컨트롤러)
			BookserachByKeywordController controller = new BookserachByKeywordController();
			// 객체의 인스턴스의 메서드를 호출하여 수행.
			ObservableList<BookVO>list = 
					controller.getResult(textField.getText()); // 결과를 가져와달라고 요청. //컨트롤러를 만든다음 결과 값을 가져와달라고..
			// getResult안에 키워드 값을 전달하여 ObeserList 객체 얻어와 Tableview통해 객체 를 반환. 
			tableView.setItems(list);
			searchKeyword =textField.getText();
			textField.clear();
		});
		
		// 삭제버튼도 만들어서 붙여요!
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> { // 화면 구성하는 건 만 남김.ㄴ
			// 나중에 이번트 처리를 수행. 
			DeleteByisbnController controller = 
					new DeleteByisbnController();
			ObservableList<BookVO> list = 
					controller.getResult(deleteISBN, searchKeyword); 
			// 컨트롤러 자체가 별도에 있어서 각 클래스에 대한 작업을 수행.
			// view 입장에서 삭제된 리스트 완료 객체가 나와야 함.
			tableView.setItems(list);
			
		});
		
		
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		

		// 컬럼객체를 생성해요!
		// TableColumn<해당컬럼에 나오는 데이터를 어떤 VO에서 가져오지는 그 VO의 클래스를 명시,
        //		       VO에서 값을 가져올때 사용하는 데이터 타입>
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = 
				new TableColumn<>("TITLE");  // ISBN은 화면에 보여지는 컬럼의 이름
		titleColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));

		TableColumn<BookVO, String> authorColumn = 
				new TableColumn<>("AUTHOR");  // ISBN은 화면에 보여지는 컬럼의 이름
		authorColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> priceColumn = 
				new TableColumn<>("PRICE");  // ISBN은 화면에 보여지는 컬럼의 이름
		priceColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		
		// TableView에 표현할 데이터를 만들어 보아요!
		// TableView에 데이터를 밀어넣을때는.. ArrayList와 유사한 List를 사용 
		
		tableView = new TableView<BookVO>();
		
		// 위에서 만들어진 컬럼객체를 TableView에 붙여요!
		tableView.getColumns().addAll(isbnColumn, 
				titleColumn, authorColumn, priceColumn);
		
		// 나중에 TableView에 데이터가 표현될꺼예요! 
		// 이때 각 행들에 대해서 이벤트를 설정할 수 있어요.
		// 정확하게 얘기하자면 각 행들에 대한 특정 설정을 할 수 있어요!		
		tableView.setRowFactory(e -> {
			// TableRow(테이블의 각 행)을 만들어서
			TableRow<BookVO> row = new TableRow<>();			
			// 해당 행에 이벤트 처리를 설정한 다음
			row.setOnMouseClicked(e1 -> {
				deleteBtn.setDisable(false); // 삭제버튼 활성화
				// 내가 어떤 행을 클릭했는지 확인을 해야 하니..
				BookVO book = row.getItem();
				// 삭제할 책의 ISBN을 버튼이 클리되었을때 알아내야 해요!
				deleteISBN = book.getBisbn();
			});
			// 해당 행을 리턴하는 방식
			return row;
		});
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("BookSearch JavaFX MVC");
					
		primaryStage.setOnCloseRequest(e -> {
				// 이벤트 처리
		});
		
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}
	
}
