package system.book.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSessionFactory;

import javafx.collections.ObservableList;
import system.book.DAO.BookDAO;
import system.book.DAO.MemberDAO;
import system.book.mybatis.MyBatiesConnectionFactory;
import system.book.vo.BookVO;
import system.book.vo.MemberVO;

public class bookService {

	public int getRegister(MemberVO member) {
		SqlSessionFactory factory = MyBatiesConnectionFactory.getSqlSessionFactory();

		MemberDAO dao = new MemberDAO(factory);
		
		int result = dao.memberRegister(member);
		return result;
	}

	public MemberVO getLogin(String inID, String inPassword) {
		SqlSessionFactory factory = MyBatiesConnectionFactory.getSqlSessionFactory();
		MemberDAO dao = new MemberDAO(factory);
		
		MemberVO member = dao.memberLogin(inID,inPassword);
		return member;
	}

	public ArrayList<BookVO> selectBooksByKeyword(String BookTitle) {
		SqlSessionFactory factory = MyBatiesConnectionFactory.getSqlSessionFactory();
		BookDAO dao = new BookDAO(factory);
		ArrayList<BookVO> list = dao.bookSearch(BookTitle);
		return list;
	}

	public int bookRental(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

}
