import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CollectionQuiz {

	public static void main(String[] args) {
		Set<Account> set = new HashSet<Account>();
		set.add(new Account("1111", "박호준", 1111, 1000));
		set.add(new Account("2222", "김호준", 1111, 1000));
		set.add(new Account("1111", "박호준", 1111, 1000));
		System.out.println(set.size());

		String str1 = "박호준";
		String str2 = "김호준";
		String str3 = "박호준"; // String pool에 의해 참조
		System.out.println(str1.hashCode()); // 내용물 비교 ?
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());
		// 힌트는 ? 여기는 그대로?.. 다른곳에서 고쳐야 한다.
		// Object 클래스에 힌트가 있다.
		// equals?
		// 같은 종류의 객체?
		// 같으면 2차작업으로 equals 호출
		// 담을 때 해시코드 값이 다르면 equals 호출하지 않는다.

		Map<String, String> map = System.getenv();
		Set<String> keySet = map.keySet();
		for (String name : keySet) {
			String value = System.getenv(name);
			System.out.println(name + "=" + value);
		}
		
	}

}
