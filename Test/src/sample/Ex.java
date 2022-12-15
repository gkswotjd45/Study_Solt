package sample;

public class Ex {
	public static void main(String[] args) {
		//우리 프로그램을 실행시키려면
		
		// javac Ex.java => Compile 
		// 결과로 바이트 코드인 ExampleTest.class 파일 생성.
		// java ExampleTest 엔터로 눌러서 실행 
		
		// java Ex 10 20 2개의 값을 들고 가서 실행. => args에 들어감.
		// 프로그그램을 실행할때 넘겨주는 초기값 10, 20 넣어줌.
		System.out.println(args[0]+ " , "+ args[1]); //args 설정시 인자 구분시 띄움.
		
	}
}
