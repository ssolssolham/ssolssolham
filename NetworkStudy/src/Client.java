
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 클라이언트의 데이터 수신 및 처리
 * @author 박호준
 *
 */
public class Client extends Thread {
	
	private boolean running;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public Client(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
		running = true;
	}
	
	public void receive() {
		while(running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();
				System.out.println("클라이언트 수신 데이터: " + clientMessage);
				if(clientMessage.equalsIgnoreCase("quit")) {
					break;
				}
				out.println(clientMessage);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}
	
	@Override
	public void run() {
		receive();
	}
}
