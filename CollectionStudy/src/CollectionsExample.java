import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsExample {

	public static void main(String[] args) {
		List<Account> list = new ArrayList<>();
		list.add(new Account("3333", "박호준", 1111, 2000));
		list.add(new Account("1111", "박호준", 1111, 1000));
		list.add(new Account("2222", "박호준", 1111, 5000));
		
//		Collections.sort(list, new NumberCompare());
		Collections.sort(list, new MoneyCompare());
		for (Account account : list) {
			System.out.println(account);
		}
		
	}

}
