package kr.or.kosta.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import kr.or.kosta.chat.common.Protocol;


public class ChatServer {
	public static final int PORT = 7777;
	private boolean running;
	private ServerSocket serverSocket;
	private Hashtable<String, Client> clients;
	
	// 외부에 제공할 기능?
	public boolean isRunning() {
		return running;
	}
	public Hashtable<String, Client> getClients() {
		return clients;
	}

	// main에서 돌아간다.
	// 1. PORT 충돌, 
	// 2. 네트워크 안됨
	// 3. 메시지만 보낸다면 있는거 그대로 쓰면 된다.
	// 4. 내가 원하는 메시지를 보내야한다.
	public void startUp() throws IOException {
		try {
				serverSocket = new ServerSocket(PORT);
			}catch(Exception e) {
				throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer를 구동할 수 없습니다.");
			}
		
		running = true;
		clients = new Hashtable<String, Client>();
		System.out.println("[" + PORT + "] ChatServer Start....");
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);
				System.out.println(client);
				if(addClient(client)) {
					client.sendMessage(Protocol.CONNECT_RESULT + Protocol.DELEMETER + "SUCCESS");
					client.start();
					System.out.println("#### [" + socket.getInetAddress() + "님께서 서버에 연결하셨습니다.");
				}else {
					// 클라이언트로 보내는 코드
					client.sendMessage("1001");
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void shutDown() {

	}

	public boolean addClient(Client client) {
		if(clients.containsKey(client.getNickName())){
			// 유효성 검증
			return false;
		}else {
			clients.put(client.getNickName(), client);
			return true;
		}
	}

	public void removeClient(Client client) {

	}

	public void sendAllMessage(String message) {

	}


}
