import java.util.Enumeration;
import java.util.Hashtable;

public class MapExample2 {

	public static void main(String[] args) {
		Hashtable<String, Account> set = new Hashtable<>();

		Account account1 = new Account("1111", "박호준", 1111, 1000);
		Account account2 = new Account("2222", "박호준", 1111, 1000);
		set.put(account1.getAccountNum(), account1);
		set.put(account2.getAccountNum(), account2);

		System.out.println(set.get("1111"));

		Enumeration<String> e = set.keys();
		// key를 가져올 때는 keys()로 
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key);
		}
		
		Enumeration<Account> e2 = set.elements();
		// value를 가져올 때는 elements()로
		
		// 동적객체? 필요한 내용만 넣는것(인적사항)
		// Student{ Map }
	}

}
