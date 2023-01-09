package example.main;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import example.dao.BookDAO;
import example.mybatis.MyBatiesConnectionFactory;
import example.vo.BookVO;

public class main {
	
	public static void main(String[] args) {
		
		SqlSessionFactory factory = MyBatiesConnectionFactory.getSqlSessionFactory();
		
		BookDAO dao = new BookDAO(factory);
		
		
		//dao에 메서드를 호출해서 수행하는 것을 초점.
		
		/*
		HashMap<String, Object> map = dao.selectByISBNHashMap("89-7914-206-4");
		
		for(String key: map.keySet()) {
			//hasSet key 값들의 목록을 다 가져옴,
			System.out.println(key + ", "+map.get(key));
			// get(key)을 통해 values 값을 가져옴.
		
		}
		*/
		// 2. 조건없이 모든 책에 데이터를 가져와서 출력하세요!
		// DAO부터 생성해서 처리하면 될 것 같아요!
		// 이번에는 dao에게 sqlsessionFactory를 넘겨줄 거에요!
		/*
		List<HashMap<String, Object>> list = dao.selectAllHashMap();
		for(HashMap<String, Object> map : list) {
			for(String key : map.keySet()) {
				System.out.println(key + ", "+map.get(key));
			}
		}
		*/
		
		// 3. 책의 isbn을 이용해서 책 1권의 데이터를 가져와서 출력하세요.
		// 이번에는 HashMap이 아니라, VO를 이용할 꺼임.
		/*
		BookVO book = dao.selectByISBNBookVO("89-7914-206-4");
		// DAO부터 생성해서 처리하면 될 것 같아요!
		// 이번에는 dao에게 sqlsessionFactory를 넘겨줄 거에요!
		
		System.out.println(book.getBtitle() + "," + book.getBauthor());
		*/
		
		/*
		// 4. 책의 isbn을 이용해서 책 1권의 데이터를 가져와서 출력. 단 출력을 map으로 
		BookVO book = dao.selectISBNResultMap("89-7914-206-4");
		System.out.println(book.getBtitle() + "," + book.getBauthor());
		
		*/
		
		// 5. 책의 isbn을 이용해서 책 1권의 데이터를 업데이트 할꺼임.
		// update하면.. 결과가 int로 나옴.
		// vo 하나에 책정보를 내가 업데이트할 키값 내용 vo로 만들어서 이 vo를 전달시키는 방식. => update "vo"를 주고 받는 형식.
		BookVO book = new BookVO();
		book.setBisbn("89-7914-206-4");
		book.setBtitle("자바30일완성!");
		book.setBauthor("신사임당");
		book.setBprice(3000);
		
		int result = dao.updateISBN(book);
		System.out.println("영향을 받은 행의수 :" +result);
		
	} 
	
}
