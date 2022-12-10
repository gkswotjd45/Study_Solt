package mytest;

// Student 클래스에서 객체 만들기 위해 부서 class 생성.
public class Department {
	//constructor 
	
	public Department() {
		
	}
	public Department(String deptName, String deptNumber) {
		this.deptName  = deptName;
		this.deptNumber = deptNumber;
		
	}
	
	//field 

	//method
	//business method 
	//getter & setter 
	
	private String deptName; // 학과명
	private String deptNumber; // 학과 전화번호
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	
	
	

}
