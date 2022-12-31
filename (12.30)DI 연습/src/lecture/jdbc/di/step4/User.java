package lecture.jdbc.di.step4;

// vo 클래스 의미.
public class User {
	// 사용자 입력 조회 관련 처리
	
	private String id;
	private String name;
	private String password;
	
	public User() {
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
