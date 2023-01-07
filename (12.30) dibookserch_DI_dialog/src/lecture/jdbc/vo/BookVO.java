 package lecture.jdbc.vo;

// mvc 패턴에 Model에 해당. (데이터)ㄴ
public class BookVO {
	private String bisbn; // 컬럼명 필드명을 동일하게 만드는 형태
	private String btitle;
	private String bauthor;
	private int bprice;
	
	public BookVO() {
		// TODO Auto-generated constructor stub
	}

	public BookVO(String bisbn, String btitle, String bauthor, int bprice) {
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
	
}
