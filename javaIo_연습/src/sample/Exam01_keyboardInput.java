package sample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Exam01_keyboardInput {
	
	public static void main(String[] args) {
		System.out.println("키보드를 한줄을 입력하세요.");
		
		//키보드로부터 입력으로 받으려면, 데이터 연결통로(Stream)이 있어야 함
		//System.in 표준 입력 스트림이 제공되는데 . inputStream class 객체예요. 사용하기 너무 불편함.
		//문자 기반의 데이터를 받기를 원하니 Reader를 하나 를 만들아야함 . inputstream을 마
		new InputStreamReader(System.in);// 문자열 기반 input stream. 이렇게 스트림을 결합해서 조금더 편한 스트림을 결합해서 조금 더 편한 문자 기반의
										// 통로을 열었지만, 그럼에도 불편함 => 문자열 한줄을 그대로 읽어주는 것을 원함.
		//조금더 편한 문자 기반의 데이터 입력 연결통로를 만들어 볼거임.
		// 
		//BufferedInputStream br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //데이터를 받는 통로.
		try {
			String s = br.readLine();// 엔터까지 포함헤서 한 라인을 읽어드림. //blocking 메서드
			System.out.println("입력받은 데이터는 : " + s);
		} catch (IOException e) {
			
		}// 엔터까지 포함헤서 한 라인을 읽어드림.
	}
}
