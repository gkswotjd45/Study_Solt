package example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import example.vo.BookVO;

public class BookDAO {
	private SqlSessionFactory factory;
	
	public BookDAO(SqlSessionFactory factory) { // 생성자 주입방식.
		this.factory = factory; // 생성자 주입
		
		// 메인에서 dao한테 뭔가 넘겨 줌. 그래서 BookDAO가 facotry를 담음.
		// TODO Auto-generated constructor stub
	}

	public HashMap<String, Object> selectByISBNHashMap(String isbn) {
		
		HashMap<String, Object> map = null;
		SqlSession session = factory.openSession(); // 커녁센으로 이해하면됨 커넥션 풀을 커낵션 객체로 가져온다느 식으로 이해.
		map = session.selectOne("example.myBook.selectBookByISBNHashMap", isbn);// select 조회에서 가져오라는 의미 1개만 가져오라는 것.
		
		// 
		return map;
	}

	public List<HashMap<String, Object>> selectAllHashMap() {
		
		List<HashMap<String, Object>> list = null;
		SqlSession session = factory.openSession(); 
		
	
		list = session.selectList("example.myBook.selectAllHashMap");
		
		return list;
	}

	public BookVO selectByISBNBookVO(String isbn) {
		
		BookVO book  =null;
		SqlSession session = factory.openSession(); 
		book = session.selectOne("example.myBook.selectBookbyISBNBookVO",isbn);
		
		return book;
	}

	public BookVO selectISBNResultMap(String isbn) {
		
		BookVO book  =null;
		SqlSession session = factory.openSession(); 
		
		try {
			book = session.selectOne("example.myBook.selectBookbyISBNResultMap",isbn);
		}finally {
			
		}

		return book;
	}

	public int updateISBN(BookVO book) {
		int result = 0; // 업데이트 받은 영향 받은 개순
		SqlSession session = factory.openSession(); 
		
		try {
			result = session.update("example.myBook.update",book);
		}
		catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.commit(); // update, delete 문은 커밋을 반드시 수행.
			session.close();
		}
		//세션이 갖고 있는 update 시킴.
		
		return result;
	}
}
