import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {

	public static final int port = 7777;
	public static void main(String[] args) {
		boolean running = true;

		try {
				ServerSocket serverSocket = new ServerSocket(port);
				System.out.println(port + "포트에서 서버 실행..");
				
			while(running) {				
				Socket socket = serverSocket.accept();
				System.out.println(socket);
				InetAddress ia = socket.getInetAddress();
				System.out.println(ia.getHostAddress() + "클라이언트가 연결해옴..");
				
				Client client = new Client(socket);
				client.start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ServerSocket은 port번호만 필요하다.
	}

}
