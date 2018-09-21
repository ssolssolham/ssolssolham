import java.util.Comparator;

public class MoneyCompare implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		"aa".compareTo("bbb");  
		// 위임형
		return (int)(o1.getRestMoney() - o2.getRestMoney());
	}

}
