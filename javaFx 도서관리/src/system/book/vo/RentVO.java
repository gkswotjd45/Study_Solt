package system.book.vo;

public class RentVO {
	
	private String mId; // 대여한 ID 
	private String rIsbn; // 대여한 청구기호
	private String rTitle; // 대여한 도서제목
	private String rDate; // 대여한 날짜
	
	public RentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentVO(String mId, String rIsbn, String rTitle, String rDate) {
		super();
		this.mId = mId;
		this.rIsbn = rIsbn;
		this.rTitle = rTitle;
		this.rDate = rDate;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getrIsbn() {
		return rIsbn;
	}

	public void setrIsbn(String rIsbn) {
		this.rIsbn = rIsbn;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	
}
