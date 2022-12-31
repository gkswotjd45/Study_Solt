package lecture.jdbc.di.step6;

// 여기서 프로그램이 시작하도록 하는 클래스.
public class Main {
	public static void main(String[] args) {
		
		User user = new User("hong","홍길동","1234"); // 사용자 객체 생성
		
		ConnectionMakeup cm = new KosaMakeUp_buyer(); // 의존성을 가지고 있지만, 클래스 간 관계가 아닌 객체 관계에서 숳애.
													 // kosamakeup 인스턴스를 생성하면 기본 생성자를 통헤서 생성되고 해당 인스턴스에서 
		// 오버라이딩 한 getconnection()올 커낵션을 반환?? -> getconnection을 호출을 안했는데 왜 그런가?
		UserDAO dao = new UserDAO(cm); // 데이터 베이스 처리. 
		// 객체 의존성이 남아 있게 됨 
		// 인스턴스와 인스턴스 관계
		
		dao.insert(user); // dao에 vo을 넘겨서 처리.
		System.out.println("새로운 사용자 등록! ");
		
		User user2 = dao.select("hong");
		System.out.println(user2.getName() +", " +user2.getPassword() );
		System.out.println("조회성공");
		
	}
}
