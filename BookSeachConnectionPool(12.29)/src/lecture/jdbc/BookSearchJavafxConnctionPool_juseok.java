package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class BookSearchJavafxConnctionPool_juseok extends Application {
	private static BasicDataSource basicDS;
	// TableView안에 데이터를 표현할때 VO를 가져다가 한 줄씩 표현하게 되요!
	// 그 때 어떤 VO를 사용하는지 class이름을 generic으로 지정해야 해요!
	TableView<BookVo> tableView;	
	TextField textField;
	Button deleteBtn;
	
	String deleteISBN;
	String searchKeyword;
	
	static {
		//Connection pool을 만들 꺼임.
		basicDS = new BasicDataSource(); // new Connection pool 자체를 
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("jaenir3529");
		basicDS.setInitialSize(10); // pool 사이즈의 초기 크기. 데이터베이스 커넥션연결을 초기에 10개 만듬.
		basicDS.setMaxTotal(20); // 전체 커넥션 몇 개로 할지.
		
	}
	
	public static DataSource getDataSource() { // dataSource는 상위 타입으로 is-a관계성립 BaiscDataSoucre
		return basicDS;  // 이 메서드는 커녁션 풀을 얻을 수 있음 (외부 클래스가 접근하지 X static 으로 지정되어있게 때문에) 
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// 커넥션 풀을 가져와서 상위 타입의 클래스에 담음. // 상위타입인 풀부터 가져와서 순차적으로 진행.
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
				// 실행하기 전에.. ? 를 채워야 해요! , db정보 및 데이터 포트번에 저장되있는 커넥션 객체에 sql문을 포함하여 
				//preparedStatment 을 통해 전달.
				pstmt.setString(1, "%" + textField.getText() + "%");
				
				ResultSet rs = pstmt.executeQuery(); //select 문 실행시 executeQuery문을 통해 ResultSet 클래스에  객체 저장.
				
				
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
				
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
		
				con.setAutoCommit(false);  // transaction 시작, 자동적으로 한줄 단위의 commit 수행하는 것을 방지. 
				
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				// 실행하기 전에.. ? 를 채워야 해요!
				pstmt.setString(1, deleteISBN);
				
				int count = pstmt.executeUpdate(); //Delete 문 실행시, executeUpdate() 수행하여 정수 값을 반환.
				
				// 5. 결과처리!
				if(count == 1) {
					con.commit(); //commit을 진행하여 -> 데이터베이스 항목에 삭제를 반영.
					// 다시 검색해서 결과 가져와서 화면에 찍어야 해요!
					
					sql.append("SELECT bisbn, btitle, bauthor, bprice ");
					sql.append("FROM book ");
					sql.append("WHERE btitle like ?");
					sql.append("ORDER BY bprice DESC");
					
					try {
						
						PreparedStatement pstmt1 = con.prepareStatement(sql.toString());
						// 실행하기 전에.. ? 를 채워야 해요! , db정보 및 데이터 포트번에 저장되있는 커넥션 객체에 sql문을 포함하여 
						//preparedStatment 을 통해 전달.
						pstmt.setString(1, "%" + textField.getText() + "%");
						
						ResultSet rs = pstmt.executeQuery(); //select 문 실행시 executeQuery문을 통해 ResultSet 클래스에  객체 저장.
						
						
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

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
						con.rollback();
					}
			
				pstmt.close();
				con.close();
				
			}catch (Exception e1) {
					// TODO: handle exception
				}
			});
			
		

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ?");
		
		
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
		tableView.getColumns().addAll(isbnColumn,titleColumn, authorColumn, priceColumn);
		
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
		primaryStage.setTitle("Simple JavaFX TableView");
		
		primaryStage.setOnCloseRequest(e->{
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
