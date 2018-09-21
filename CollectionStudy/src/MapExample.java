import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {

		Map<String,String> map = new HashMap<>();
		map.put("920820", "박호준");
		map.put("920821", "김호준");
		map.put("520822", "이호준");
		map.put("430822", "홍호준");
		
		if(map.containsKey("430822")) {
			System.out.println("존재하는 키입니다..");
		}else {
			map.put("430822", "최호준"); 	
		}
		// 있는지 없는지 검사!
		
		System.out.println(map.get("430822"));
		// 같은 key값으로 저장하면 덮어씌운다.
		
		// key는 주민번호, value는 이름으로 저장 예시
		
		Set<String> keyList = map.keySet();
		for (String key : keyList) {
			System.out.println(key);
		}
		
		Collection<String> values = map.values();
		for (String name : values) {
			System.out.println(name);
		}
	}

}
