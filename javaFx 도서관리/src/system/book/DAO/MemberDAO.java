package system.book.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import system.book.vo.MemberVO;

public class MemberDAO {

	private SqlSessionFactory factory;
	
	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDAO(SqlSessionFactory factory) {
		super();
		this.factory = factory;
	}


	public int memberRegister(MemberVO member) {
		int result = 0; // 업데이트 받은 영향 받은 개순

		
		MemberVO newMember = new  MemberVO(member.getmID(), 
				member.getmPassword(), 
				member.getmEMail(), member.getmPhone());
		
		SqlSession session = factory.openSession(); 
		try {
			result = session.insert("example.myLogin.Register",newMember);
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

	public MemberVO memberLogin(String inID, String inPassword) {
		MemberVO member = null;
		MemberVO checkmember = new MemberVO(inID,inPassword);
		SqlSession session = factory.openSession();
		
		try {
		member = session.selectOne("example.myLogin.selectMemberlogin",checkmember);
		}finally {
			
		}
		return member;
	}
	
	
}
