package NetWork_part4_chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class Shared {
	ArrayList<Socket> list = new ArrayList<Socket>();
	HashMap<Socket, PrintWriter> map = new HashMap<Socket,PrintWriter>();
	
	public void addClient(Socket socket) {
		list.add(socket);
		
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void broadcast(String msg) {
		for(Socket s : list) {
			(map.get(s)).println(msg);
			(map.get(s)).flush();
		}
	}
}

class MyRunnable implements Runnable{
	Shared shard;
	BufferedReader br;
	Socket socket;
	
	public MyRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	public MyRunnable(Socket socket, Shared shared) {
		super();
		this.shard = shared;
		this.socket = socket;
		
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		
			try {
				while(true) {
				String msg = br.readLine();
				shard.broadcast(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
}



public class Exam04_mulitTalk_server extends Application {

	TextArea textArea;
	Button startBtn;
	Button stopBtn; 
	
	
	Socket socket;
	ServerSocket server;
	Shared shard;
	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + " \n");
		
		});
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		
		
		startBtn = new Button("서버 시작");
		startBtn.setPrefSize(150, 40);
		
		startBtn.setOnAction(e ->{
			
			shard = new Shared();
			
		(new Thread(() -> {
			try {
				server = new ServerSocket(6666);
				
				while(true) {
				printMsg("새로운 크라이언트 접속");
				
				MyRunnable r = new MyRunnable(socket,shard);
				Thread t = new Thread(r);
				t.start();
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		})).start();
			
			
		});
		stopBtn = new Button("서버 중단");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e ->{
			
		});
		
		FlowPane flowPane = new FlowPane();
		
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);
		flowPane.setPrefSize(100,40);
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		root.setBottom(flowPane);
		
		Scene scen = new Scene(root);
		primaryStage.setScene(scen);
		primaryStage.setTitle("Server 모습");
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch();
	}
}