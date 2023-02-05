package system.book.vo;


//mysql Member 테이블 필드, 항목을 구성한 VO

public class MemberVO {
	private String mID; // 회원 ID 
	private String mEMail; // 회원 이메일
	private String mPhone; // 회원 폰번호
	private String mPassword; // 회원 비밀번호
	private int mPoint = 500; // 초기 포인트
	public MemberVO() {
		
	}
	
	
	public MemberVO(String mId, String mPassword) {
		this.mID = mId;
		this.mPassword =mPassword;
	}

	public MemberVO(String mID, String mPassword,String mEMail, String mPhone) {
		super();
		this.mID = mID;
		this.mEMail = mEMail;
		this.mPhone = mPhone;
		this.mPassword = mPassword;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getmEMail() {
		return mEMail;
	}

	public void setmEMail(String mEMail) {
		this.mEMail = mEMail;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	
	
	
}
