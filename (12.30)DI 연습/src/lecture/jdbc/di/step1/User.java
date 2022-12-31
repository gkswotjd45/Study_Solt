package lecture.jdbc.di.step1;

// vo 클래스 의미. 단순히 데이터가 처리하는 형태를 으미ㅣ.
public class User {
	// 사용자 입력 조회 관련 처리
	
	private String id;
	private String name;
	private String password;
	
	public User() { // 초기 생성자.
		// TODO Auto-generated constructor stub
	}

	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
