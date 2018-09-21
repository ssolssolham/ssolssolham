import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		Set<String> set = new TreeSet<String>();
		set.add("김기정");
		set.add("홍기정");
		set.add("이기정");
		set.add("박기정");
		set.add("최기정");
		set.add("bbbbb");
		set.add("aaaaa");
		
		for (String string : set) {
			System.out.println(string);
			// 알파벳이 우선순위에서 상위이다.
		}
		
		// 담을 때 정렬하기에 속도가 느리다.
	}

}
