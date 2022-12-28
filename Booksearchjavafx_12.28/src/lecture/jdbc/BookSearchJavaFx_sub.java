package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.util.Callback;
import lecture.vo.BookVo;


public class BookSearchJavaFx_sub extends Application {

// 여러 개의 입력 상자. 한 줄짜리 입력상자.
	// Table View 안에 데이터를 표현할때 Vo를 가져다가 한줄씩 표현하게 됨. 그때 어떤 VO를 사용하는지에 때한 class 대한 class이름을 generic으로 지정해야 함.
	TableView<BookVo> tableView; // table view는 타입을 지정해야 사용함. table view 사용하는 VO 클래스를 지정해야 함.
	// 한줄 row 마다 하나의 VO(객체)를 의미 => 따라서 BookVO 클래스를 이용하여 수행한다는 의미.
	TextField textField;
	Connection con;
	Button deleteBtn;
	
	String deleteISBN; //삭제할 책번호.
	
	
	// jdbc 1 ~ 2 단계가 준비단계.
	// jdbc 3 ~ 5 단계가 실질적인 실행 단게. 
	
	// 생성자 초기화 단계 -> 마치 객체를 생성하기전에 준비 단계.(jdbc 연결 단계)
	// 생성자(초기화) , 준비단계./ 앗 여기에서 JDBC 준비단ㅖ를 수행.
	// Connectopn 객체는 지역변수가 되면 안되므로, 상위 필드에 지정. => 변수 선언.
	
	public BookSearchJavaFx_sub() { // 생성자가 만들어지고 나서 인스턴스 생성 -? 인스턴스에 대한 start 메서드 수행/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "jaenir3529";
			// 2. Database 접속
			con = DriverManager.getConnection(jdbc_url, id, pw); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { // start 메서드 호출하여 화면을 만들어 창을 띄움 -> 일반메서드 => 일반 객체가 있음(instance) O
		
		//1. layout부터 설정.
		//boarderpane을 이용해서 (동서남북중앙) 
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); // 레이아웃 가로 세로 크기.
		
		//2. boaderpane 아래 쪽에 붙일 패널.(flowPane) 하나 생성., 속성 설정. (대략적인 정도만 알기)
		FlowPane flowpane = new FlowPane(); 
		flowpane.setPadding(new Insets(10,10,10,10)); // 여백 잡음
		flowpane.setColumnHalignment(HPos.CENTER); //가운데 정렬 옵션
		flowpane.setPrefSize(700, 40); // 패널 크기
		flowpane.setHgap(10); //수평 간격 조절.
		
		// 3. 각각의 컴포넌트를 생성해서 붙임.
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		
		// 키워드를 입력하고 나서 table을 바꿔서 수행.
		textField.setOnAction(e -> {

			
			StringBuffer sql = new StringBuffer(); // sql 문장을 String buffer로 저장하나 한문장씩 저장.
			sql.append("SELECT bisbn, btitle, bauthor, bprice ");
			sql.append("FROM book ");
			sql.append("WHERE btitle like ? ");
			sql.append("ORDER BY bprice DESC");
			try {
				PreparedStatement pstmt = con.prepareStatement(sql.toString()); // con 객체 안에 DB 정보, 포트정보, db종류 포함.
				// 실행하기 전에.. ? 를 채워야 해요!
				pstmt.setString(1, "%" + textField.getText()+ "%");
				
				ResultSet rs = pstmt.executeQuery();
				
				ObservableList<BookVo> list = FXCollections.observableArrayList(); //Arraylist와 같은 기능.
				
				
				// 5. 결과처리!
				while(rs.next()) {
					BookVo book = new BookVo(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice")); // 순서대로 객체를 만들어서 대입.
					list.add(book);
				} // vo를 만들어서 list 안에 삽입하는 구조. (vo를 통해 자료 구조화 시킴)
				tableView.setItems(list);
			}catch (Exception e1) {
				// TODO: handle exception
			}
				
			System.out.println("입력 되었습니다.");
		});
		
		// 삭제 버튼도 만들어서 붙임.
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true); // 사용할 수 없도록 하는 메서드 (현재 바로 사용할 수 없도록)
		deleteBtn.setOnAction(e ->{// 버튼을 누르면 여기가 실행됨.

			try {
				String sql = "Delete From book where bisbn = ? ";
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, deleteISBN); // 첫번 째 물음표에 삽입.
			
				//4. 실행
				int count = pstmt.executeUpdate(); 
				System.out.println(count + "권 삭제되었습니다.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		});
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		
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

		
		tableView =new TableView<BookVo>();
		
		//위에서 만들어진, 컬럼객체를 tableView에 붙임.
		tableView.getColumns().addAll(isbnColumn,titleColumn,authorColumn,priceColumn);

		
		// 나중에 tableView에 데이터가 표현될 거예요.
		// 이때 각 행들에 대해서 이벤트를 설정할 수 있음 ex) 행을 클릭하면.
		//-> 정확하게 이야기 하자면 각 행들에 대한 특정 설정을 할 수 있음.
		tableView.setRowFactory(e ->{ // java.setRowfactory 메소드 통해 데이터 베이스 행을 선택할 경우.
			// TableRow을 만들어서 return 해야 함 -> table에 각 행들을 만들어서 
			TableRow<BookVo> row = new TableRow<>();
			// "해당 행의 이벤트 처리를 설정한 다음, 해당 행을 return 방식."
			row.setOnMouseClicked(e1->{ // 각 행을 클릭한다면,
				deleteBtn.setDisable(false); // delete 버튼 클릭은 활성화되어 띄움.
				BookVo book = row.getItem();// 각각의 행들은 vo(하나의 bookvo instance)로 되어있어서. 현재 row가 갖고 있는 vo 정보를 갖고옴.
				System.out.println(book.getBtitle()); // book의 책정보를 커맨드 창에 출력.
				
				deleteISBN = book.getBisbn(); // 선택한 row 행 (vo) 객체에 대한 isbn에 데한 값을 저장.
			}); // 테이블에 각각의 행들을 클릭하면 발생하는 이벤트.
			return row; // 해당 row 반환.
		});
		
		
		// 데이터를 세팅하면 됨

		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root); //전체 root을 scene 에 붙임
		
		primaryStage.setScene(scene); // sence을 통해 전체 윈도우 창에 표시
		primaryStage.setTitle("simple javafx tableview");
		// 6. 자원 정리.
		primaryStage.setOnCloseRequest(e ->{
			try {
				con.close(); //커넥션 (데이터 베이스 정보) 객체를 반환.
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // 닫을 때 데이터베이스 종료.
		}); // 전체 창을 닫을 때 이벤트 발생. => 창 닫을 때 안에 있는 코드 수행.
		primaryStage.show();
		
		// 4. 
		
		
		
	}
	public static void main(String[] args) {
		launch();  // lunch라는 메서드를 통해서 gui 쓰레드를 만들어서 거기서 실행.
	}
			
}
