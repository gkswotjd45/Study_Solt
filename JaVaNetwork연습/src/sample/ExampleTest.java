package sample;

public class ExampleTest {

	public static void main(String[] args) {
		
		// 우리프로그램을 실행시키려면..
		// javac ExampleTest.java  => compile
		// 결과로 bytecode인 ExampleTest.class 파일이 생성되요!
		// java ExampleTest  엔터눌러서 실행!
		// java ExampleTest 10 20
		System.out.println(args[0] + ", " + args[1]);
	}
}
