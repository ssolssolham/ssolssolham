import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DNS와의 통신을 통해 IP <-> Domain 정보제공
 * @author 박호준
 *
 */
public class InetAddressExample {

	public static void main(String[] args) throws UnknownHostException {

		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia.getHostAddress());
		
//		String domainName = "www.daum.net";
		String domainName = "www.naver.com";
		InetAddress ia2 = InetAddress.getByName(domainName);
		System.out.println(ia2.getHostAddress());
		
		InetAddress[] ias = InetAddress.getAllByName(domainName);
		for (InetAddress inetAddress : ias) {
			System.out.println(inetAddress);
		}
	}

}
