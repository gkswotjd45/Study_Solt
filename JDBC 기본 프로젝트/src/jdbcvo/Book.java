package jdbcvo;

//vo는 데이터를 표현, 이 안의 로직이 들어오면 안됨 , 값을 저장한고 값을 표현하는 클래스.
//business 로직이 나오면 X
// database  table을 참조해서 만듬.
public class Book { // 우리의 vo를 나타내는 클래스
	
	private String bisbn; // 테이블의 컬럼명을 기준으로 필드를 만듬.
	private String btitle;
	private String bauthor;  // 일반적으로 필드를 모두 선언하면 Do클래스
	private int bprice;		// 이름 규칙에 맞게 선언.
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String bisbn, String btitle, String bauthor, int bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
	}

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public int getBprice() {
		return bprice;
	}
	
	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return bisbn + ", "+ btitle + "," + bauthor + ", " + bprice;
	}

}
