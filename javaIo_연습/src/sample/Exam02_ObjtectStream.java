

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Exam02_ObjtectStream {
	
	public static void main(String[] args) {
		//먼저 스트림을 통해 내보낼 해시 맵을 간단하게 만들어 보아요.
		HashMap<String, String> map = new HashMap<String,String>();
		
		
		map.put("1", "홍길동");
		map.put("2", "신사임당");
		map.put("3", "강감찬");
		
		// 살재 파일을 생성하려면 당연히 자바쪽에서 파일 객체를 만들어야함.
		File file = new File("readme.txt"); //os 안의 텍스트 파일을 자바프로그램쪽에서 객체로 만드러서 연결시킴.
		
		try {
			FileOutputStream fis =  new FileOutputStream(file); // 파일 객체를 내보내기 위한, 데이터를 내보내는 통로의 메서드 
			ObjectOutputStream oos = new  ObjectOutputStream(fis);	// 
			
			oos.writeObject(oos); // 우리 해시 맵을 그냥 작성하는 기능. 통로를 통해 객체를 내보냄. "해당 객체를 통로를 통해서 objoutputstream으로 OS로 전달.
			
		} catch (Exception e) { //모든 예외상황을 잡음. 어떤 오류가 발생해도 몽땅 다 잡아줌.
			
		} 
		
	}
}
