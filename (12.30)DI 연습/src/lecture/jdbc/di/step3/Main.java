package lecture.jdbc.di.step3;

// 여기서 프로그램이 시작하도록 하는 클래스.
public class Main {
	public static void main(String[] args) {
		
		User user = new User("hong","홍길동","1234"); // 사용자 객체 생성
		
		
		UserDAO dao = new KosaUserDAO(); // 데이터 베이스 처리. 
		dao.insert(user); // dao에 vo을 넘겨서 처리.
		System.out.println("새로운 사용자 등록! ");
		
		User user2 = dao.select("hong");
		System.out.println(user2.getName() +", " +user2.getPassword() );
		System.out.println("조회성공");
		
	}
}
