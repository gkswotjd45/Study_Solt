package lecture.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

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

import lecture.vo.BookVo;

public class BookSearchJavaFXConnectionPool_Good extends Application {

	private static BasicDataSource basicDS;
	
	// main이 호출되기 전에 특정 코드를 실행시키고 싶어요!
	// 일반적으로 프로그램에서 사용하는 resource같은거 로딩할때 사용해요!
	static {
		// Connection Pool을 만들꺼예요!
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("jaenir3529");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20);		
	}
	
	TableView<BookVo> tableView;	
	TextField textField;
	Button deleteBtn;
	
	String deleteISBN;
	String searchKeyword;
	
	public static DataSource getDataSource() {
		return basicDS;
	}
	
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

			DataSource ds = getDataSource();
			// 2. Database 접속
			Connection con = null;
			
			try {
				con = ds.getConnection();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT bisbn, btitle, bauthor, bprice ");
			sql.append("FROM book ");
			sql.append("WHERE btitle like ?");
			sql.append("ORDER BY bprice DESC");

			try {
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				// 실행하기 전에.. ? 를 채워야 해요!
				pstmt.setString(1, "%" + textField.getText() + "%");
				
				ResultSet rs = pstmt.executeQuery();
				
				ObservableList<BookVo> list = FXCollections.observableArrayList();
				
				// 5. 결과처리!
				while(rs.next()) {
					BookVo book = new BookVo(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice"));
					list.add(book);
				}
				
				tableView.setItems(list);
				
				// 6. 사용한 리소스 반납
				rs.close();
				pstmt.close();
				con.close();
				
				searchKeyword = textField.getText();
				
				textField.clear();
				
			} catch (Exception e1) {
				// TODO: handle exception
			}
		});
		
		// 삭제버튼도 만들어서 붙여요!
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			
			DataSource ds = getDataSource();
			Connection con = null;
			
			try {
				con = ds.getConnection();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM book ");
			sql.append("WHERE bisbn = ?");

			try {
				con.setAutoCommit(false);  // transaction 시작 
				
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				// 실행하기 전에.. ? 를 채워야 해요!
				pstmt.setString(1, deleteISBN);
				
				int count = pstmt.executeUpdate();
				
				// 5. 결과처리!
				if(count == 1) {
					con.commit();

					StringBuffer sql1 = new StringBuffer();
					sql1.append("SELECT bisbn, btitle, bauthor, bprice ");
					sql1.append("FROM book ");
					sql1.append("WHERE btitle like ?");
					sql1.append("ORDER BY bprice DESC");

					try {
						PreparedStatement pstmt1 = con.prepareStatement(sql1.toString());
						// 실행하기 전에.. ? 를 채워야 해요!
						pstmt1.setString(1, "%" + searchKeyword + "%");
						
						ResultSet rs = pstmt1.executeQuery();
						
						ObservableList<BookVo> list = FXCollections.observableArrayList();
						
						// 5. 결과처리!
						while(rs.next()) {
							BookVo book = new BookVo(rs.getString("bisbn"),
									rs.getString("btitle"),
									rs.getString("bauthor"),
									rs.getInt("bprice"));
							list.add(book);
						}
						
						tableView.setItems(list);
						
						// 6. 사용한 리소스 반납
						rs.close();
											
					} catch (Exception e1) {
						// TODO: handle exception
					}
					
				} else {
					con.rollback();
				}
				
				// 6. 사용한 자원 반납
				pstmt.close();
				con.close();
				
			} catch (Exception e1) {
				// TODO: handle exception
			}
		});
		
		
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		

		// 컬럼객체를 생성해요!
		// TableColumn<해당컬럼에 나오는 데이터를 어떤 VO에서 가져오지는 그 VO의 클래스를 명시,
        //		       VO에서 값을 가져올때 사용하는 데이터 타입>
		TableColumn<BookVo, String> isbnColumn = 
				new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVo, String> titleColumn = 
				new TableColumn<>("TITLE");  // ISBN은 화면에 보여지는 컬럼의 이름
		titleColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));

		TableColumn<BookVo, String> authorColumn = 
				new TableColumn<>("AUTHOR");  // ISBN은 화면에 보여지는 컬럼의 이름
		authorColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVo, Integer> priceColumn = 
				new TableColumn<>("PRICE");  // ISBN은 화면에 보여지는 컬럼의 이름
		priceColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		
		// TableView에 표현할 데이터를 만들어 보아요!
		// TableView에 데이터를 밀어넣을때는.. ArrayList와 유사한 List를 사용 
		
		tableView = new TableView<BookVo>();
		
		// 위에서 만들어진 컬럼객체를 TableView에 붙여요!
		tableView.getColumns().addAll(isbnColumn, 
				titleColumn, authorColumn, priceColumn);
		
		// 나중에 TableView에 데이터가 표현될꺼예요! 
		// 이때 각 행들에 대해서 이벤트를 설정할 수 있어요.
		// 정확하게 얘기하자면 각 행들에 대한 특정 설정을 할 수 있어요!		
		tableView.setRowFactory(e -> {
			// TableRow(테이블의 각 행)을 만들어서
			TableRow<BookVo> row = new TableRow<>();			
			// 해당 행에 이벤트 처리를 설정한 다음
			row.setOnMouseClicked(e1 -> {
				deleteBtn.setDisable(false); // 삭제버튼 활성화
				// 내가 어떤 행을 클릭했는지 확인을 해야 하니..
				BookVo book = row.getItem();
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
		primaryStage.setTitle("BookSearch JavaFX Connection Pool");
					
		primaryStage.setOnCloseRequest(e -> {
			
			try {
				((BasicDataSource)getDataSource()).close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}
	
}
