import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * List는 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다. Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을
 * 가진다. ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다
 * 
 * @author 박호준
 *
 */
public class ListExample {
	public static void main(String[] args) {
		List list = null;
		list = new ArrayList();

		list.add("황의조");
		list.add("손흥민");
		list.add("바나나");
		list.add(100); // Object obj = 100; 오토박싱
		list.add(new Integer(100));
		list.add(Calendar.getInstance());
		list.add("황의조");

		System.out.println("담겨진 갯수 : " + list.size());
		System.out.println("비어있는지 여부 : " + list.isEmpty());

		Set boddari = new HashSet();
		boddari.add("AAAAA");
		boddari.add("BBBBB");
		boddari.add("CCCCC");

		list.addAll(boddari);

		System.out.println("담겨진 갯수 : " + list.size());

		boolean result = list.remove("바나나");
		System.out.println("삭제결과 : " + result);
		// CRUD처리(Create,Read,Update,Delete) Update는 없다.
		System.out.println(list.contains("황의조"));
		System.out.println(list.contains(Calendar.getInstance()));
		// 내용물을 비교

		Object[] lists = list.toArray();
		for (Object object : list) {
			if (object instanceof String) {
				System.out.println(((String) object).length());
				// DownCasting
			}
			System.out.println(object);
		}

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object object = iter.next();
			System.out.println(object);
		}

		for (Object object : list) {
			System.out.println(object);
		}

		// List에 추가된 규약 메소드들...

		// 0 에 넣으면 하나씩 밀린다.
		// stack의 pop처럼 이용;
		list.add(0, "김기정");
		System.out.println(list.get(0));
		System.out.println(list.remove(0));
		System.out.println(list.set(0, "황희찬"));

		System.out.println(list.size());
		List l = list.subList(0, 3);
		for (Object object : l) {
			System.out.println(object);
		}

	}

}
