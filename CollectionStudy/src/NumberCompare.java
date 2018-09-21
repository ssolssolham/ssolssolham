import java.util.Comparator;

public class NumberCompare implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		"aa".compareTo("bbb");  
		// 위임형
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
