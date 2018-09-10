package kr.or.kosta.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 서버와의 통신 대행자
 * 
 * @author 박호준
 */
public class ChatClient{
	public static final String SERVER = "127.0.0.1";
	public static final int PORT = 7777;

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	private boolean running;
	
	public void connectServer() throws Exception {
		try {
			System.out.println(0);
			socket = new Socket(SERVER, PORT);
			System.out.println(1);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(2);
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(3);
			running = true;
			System.out.println(4);
		} catch (Exception e) {
			throw new Exception("서버를 찾을 수 없습니다..");
		}

	}

	public void stopClient() {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}

	public void sendMessage(String message) {
		if(out != null) out.println(message);
	}
	
	public String readMessage() {
		String message = null;
		if(in != null) {
			try {
				message = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return message;
	}

	public void receiveMessage() {
		//---- 별도로 스레드 클래스를 구현
		// 익명으로 처리하기 딱 좋다.
		new Thread() {
			@Override
			public void run() {
				while(running) {
					String serverMessage = null;
					try {
						serverMessage = in.readLine();
						System.out.println("클라이언트 수신 데이터: " + serverMessage);
						if(serverMessage.equalsIgnoreCase("quit")) {
							break;
						}
//						out.println(serverMessage);
						process(serverMessage);
					} catch (IOException e) {
						System.out.println("네트워크가 단절되었습니다..");
						break;
					} 
				}
				if(socket != null) {
					try {
						socket.close();
					} catch (IOException e) {}
				}
				
			}
		}.start();
		
	}
	
	public void process(String message) {
		System.out.println(message);
	}
}
