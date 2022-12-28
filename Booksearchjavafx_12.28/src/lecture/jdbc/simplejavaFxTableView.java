package lecture.jdbc;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.vo.BookVo;

public class simplejavaFxTableView extends Application {
	// 여러 개의 입력 상자. 한 줄짜리 입력상자.
	// Table View 안에 데이터를 표현할때 Vo를 가져다가 한줄씩 표현하게 됨. 그때 어떤 VO를 사용하는지에 때한 class 대한 class이름을 generic으로 지정해야 함.
	TableView<BookVo> tableView; // table view는 타입을 지정해야 사용함. table view 사용하는 VO 클래스를 지정해야 함.
	// 한줄 row 마다 하나의 VO(객체)를 의미 => 따라서 BookVO 클래스를 이용하여 수행한다는 의미.
	
	TextField textField;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//1. layout부터 설정.
		//boarderpane을 이용해서 (동서남북중앙) 
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); // 레이아웃 가로 세로 크기.
		
		//2. boaderpane 아래 쪽에 붙일 패널.(flowPane) 하나 생성., 속성 설정. (대략적인 정도만 알기)
		FlowPane flowpane = new FlowPane(); 
		flowpane.setPadding(new Insets(10,10,10,10)); // 여백 잡음
		flowpane.setColumnHalignment(HPos.CENTER); //가운데 정렬 옵션
		flowpane.setPrefSize(700, 40); // 패널 크기
		flowpane.setHgap(10); //컴포넌트 간격.
		
		// 3. 각각의 컴포넌트를 생성해서 붙임.
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		
		textField.setOnAction(e -> {
			String keyword = textField.getText();
			System.out.println("입력 되었습니다.");
		});
		flowpane.getChildren().add(textField);
		
		// 컬럼객체를 생성허여 tableview 붙임 .
		// TableColumn<해당 컬럼에 나오는 데이터를 어떤 VO에서 가져오는지 그 VO의 클래스를 명시., Vo에서 값을 가져올 때 사용하는 데이터 타입> 
		
		TableColumn<BookVo, String> isbnColumn = new TableColumn<>("ISBN"); // 안에 인자는 화면에 보여지는 컬럼의 이름.
		isbnColumn.setMinWidth(150); // 컬럼의 최소한의 크기 (최소 150 픽셀 크기)
		// 해당 컬럼에 데이터는 VO에 어떤 필드에서 값을 가져오지를 결정. // 
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		// 이렇게 사용하는 구나 라고 이해. bisbn vo에 필드에서 값을 가져와 세팅한다는 의미.
		
		TableColumn<BookVo, String> titleColumn = new TableColumn<>("TITLE"); // 안에 인자는 화면에 보여지는 컬럼의 이름.
		titleColumn.setMinWidth(150); // 컬럼의 최소한의 크기 (최소 150 픽셀 크기)
		
		// 해당 컬럼에 데이터는 VO에 어떤 필드에서 값을 가져오지를 결정. // 
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		// 이렇게 사용하는 구나 라고 이해. bisbn vo에 필드에서 값을 가져와 세팅한다는 의미.
		
		TableColumn<BookVo, String> authorColumn = new TableColumn<>("AUTHOR"); // 안에 인자는 화면에 보여지는 컬럼의 이름.
		authorColumn.setMinWidth(150); // 컬럼의 최소한의 크기 (최소 150 픽셀 크기)
		// 해당 컬럼에 데이터는 VO에 어떤 필드에서 값을 가져오지를 결정. // 
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		// 이렇게 사용하는 구나 라고 이해. bisbn vo에 필드에서 값을 가져와 세팅한다는 의미.
		
		TableColumn<BookVo, Integer> priceColumn = new TableColumn<>("PRICE"); // 안에 인자는 화면에 보여지는 컬럼의 이름.
		priceColumn.setMinWidth(150); // 컬럼의 최소한의 크기 (최소 150 픽셀 크기)
		// 해당 컬럼에 데이터는 VO에 어떤 필드에서 값을 가져오지를 결정. // 
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		// 이렇게 사용하는 구나 라고 이해. bisbn vo에 필드에서 값을 가져와 세팅한다는 의미.
		
		
		// tableView에 표현할 데이터를 만들어 봄.
		// tableview에 데이터를 밀어 넣을 때는, arraylist와 유사한 list를 사용
		ObservableList<BookVo> list = FXCollections.observableArrayList(); //Arraylist와 같은 기능.
		//ArrayList<BookVo> list = new ArrayList<BookVo>();
		list.add(new BookVo("123","java30일 완성","홍길동",2000)); // 책 한권이 vo 임, 따라서 여러 권의 책을 arraylist 안에 넣음.
		list.add(new BookVo("456","java 그만","신사임당",5000)); // 책 한권이 vo 임, 따라서 여러 권의 책을 arraylist 안에 넣음.
		list.add(new BookVo("789","java 그만","강감찬",8000)); // 책 한권이 vo 임, 따라서 여러 권의 책을 arraylist 안에 넣음.
	
		
		tableView =new TableView<BookVo>();
		
		//위에서 만들어진, 컬럼객체를 tableView에 붙임.
		tableView.getColumns().addAll(isbnColumn,titleColumn,authorColumn,priceColumn);

		
		// 데이터를 세팅하면 됨
		tableView.setItems(list);

		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root); //전체 root을 scene 에 붙임
		
		primaryStage.setScene(scene); // sence을 통해 전체 윈도우 창에 표시
		primaryStage.setTitle("simple javafx tableview");
		primaryStage.show();
		
		// 4. 
		
		
	}
	public static void main(String[] args) {
		launch();
	}

}
