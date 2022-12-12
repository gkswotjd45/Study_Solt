package Exam01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;

public class Examl_DataServer { // 프로그램을 시작하면 일단 대기. 소켓에게 날짜 전송.
	public static void main(String[] args) {
		
		// 현재 서버측 틀 만드는 중. 일단 서버 소켓
		try {//3000번 번호를 할당한 포트 객체를 생. // 포트가 한번 생성되고 나서 재 실행하면 오류가 뜸. (포트가 이미 만들어진 경우)
			ServerSocket server = new ServerSocket(3001); // 객체를 생성할 때 문제가 발생 여지 있음.// 포트번호는 객체에 할당. 
			System.out.println("서버 소켓이 생성되었어요 - 포트번호 3001");// 아직 객체만 있는 상태 해당 포트번호를 점유한 
			// 이서버 소켓을 통해 클라이언트의 접속을 기다려야 함.
			Socket s  = server.accept(); // 서버소켓에게 대기해라 (클라이언트 접속이 들어올 때 까지) _어떤 형태로 접속을 하려하기 전까지 일단 대기.
										 // 클라이언트 측에서 성공하면 소켓이 같이 생성.
			System.out.println("Client 접속이 들어왔어요.");
			 // s.getOutputStream(); // 나가는 스트림 (소켓을로부터 나가는 소켓을 뽑아냄) // but 문자열로 보내기 위한 적합한 스트림이 아님.
			// 클라이언트와 연결된 output stream(데이터르 내보내기 위한 스트림)을 사용하기 편한 printwriter로 만듬.!
			PrintWriter  out = new PrintWriter(s.getOutputStream());// printWriter 스트림 생성 (좀 더 좋은 통로를 생성) 
			//안 좋은 스트림을 갖고 좀 더 좋은 스트림을 만들어짐. 
			
			//현재 시간을 보내줄 것.
			DateFormat dateformat = DateFormat.getInstance(); //getInstance를 통해 현재 날짜와 정보를 만들도록 함.
			String currentDate = dateformat.format(new java.util.Date());
			
			out.println(currentDate); // 소켓의 out을 통해 날짜 정보를 보냄. // 실제로는 데이터로 가는 걸로 인식하지만, 안감 why? 일단 데이터를 버퍼(마차)에 탑승 후 아직은 실린 상태임
			out.flush(); // 버퍼를 출발시키는 메서드 
			
			// 이후 사용한 리소스 (자원)들을 반드시 해제해야지 메모리를 효율적으로 사용할 수 있음.
			// 1. 사용한 스트림 해제 (닫아야 함) , 리소스를 서로 공유한 상태이므로 반드시 해제 시켜야 함.
			
			out.close(); // 통로를 닫음
			s.close(); // 소켓을 닫음. (소켓이 논리적으로 사라짐 그럼 클라이언트 소켓도 예외상황이 발생하여 처리해야 함)
			server.close();	// 서버의 소켓 (3000) 닫아야 함.
			
			System.out.println("서버 프로그램이 종료!!");
			
			
			
		} catch (IOException e) {  //포트 번호가 동일하는 것을 방지.
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
