import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP/IP 기반의 Socket 프로그래밍 원리
 * @author 박호준
 *
 */
public class SocketExample {

	public static final String domain = "localhost";
	public static final int port = 7777;
	
	public static void main(String[] args) {
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
//			Socket socket = new Socket(InetAddress.getByName(domain),port);
			 socket = new Socket(domain,port);
			 System.out.println("서버와 연결됨...");
			
			 in = socket.getInputStream();
			 out = socket.getOutputStream();
			// 바이트 스트림
			/*
			out.write(10);
			System.out.println("서버에 데이터 전송");
			int data = in.read();
			System.out.println("서버로부터 에코된 데이터 : " + data);
			*/
			 
			PrintWriter pw = new PrintWriter(out);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				String inputMessage = sc.nextLine();
				pw.println(inputMessage);
				pw.flush();
				// 순서를 잘 생각!
				if(inputMessage.equalsIgnoreCase("quit")) {					
					break;
				}
				String serverMessage = br.readLine();
				System.out.println("서버로부터 에코된 데이터 : " + serverMessage);	
			}
		} catch (IOException e) {
			System.out.println("서버를 연결할 수 없습니다..");
		} finally {
			try {
//				out.close();
//				소켓으로부터 만들어졌기에 소켓이 닫히면 같이 닫힌다.
//				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
