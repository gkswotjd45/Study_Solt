package mytest;

//getter와 Setter 내용 및 다른 클래스 통한 생성자 생성 및 main()에서 객체 생성
public class Student {

	// 1. constructor 
	
	public Student() {
		
	}
	
	public Student(String name ,String sNumber, String deptName, String deptNumber) {
		this.name = name;
		this.number = sNumber;
		this.dept = new Department(deptName,deptNumber); //heap 영역에 Department 객체 생성
		
	}
	
	// 2. field 
	private String name; // 학생이름
	private String number; // 학번
	private Department dept;// 학과
	
	
	
	public static void main(String args[]) {
		
		Student s = new Student("홍길동","1234","철학과","010-111-222");
	}
	// 3. method 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
	

}