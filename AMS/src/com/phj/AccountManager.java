import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * 해시테이블을 이용한 은행계좌 관리
 * 
 * @author 박호준
 *
 */
public class AccountManager {

	/**
	 * @param accounts Account를 저장하기 위한 배열
	 * @param count    accounts 내 Account의 개수
	 */
	private Hashtable<String, Account> accounts;

	/**
	 * 생성자 1. 매개변수가 없을 시 : 50개의 Account 객체를 가지고 있는 배열 생성 2. 매개변수 x가 있을 시 : x개의
	 * Account 객체를 가지고 있는 배열 생성
	 * 
	 * @param x 배열의 개수를 결정
	 */
	public AccountManager() {
		this(10);
	}

	public AccountManager(int capacity) {
		accounts = new Hashtable<String, Account>(capacity);
	}

	/**
	 * accounts 해시테이블에 Account를 더하기
	 * 
	 * @param account 추가하려는 계좌
	 * @throws AccountException 
	 */
	public void add(Account account) throws AccountException {
		// 중복체크 !, 예외 생성
		if (accounts.containsKey(account.getAccountNum())) {
			throw new AccountException("이미 등록되어 있는 계좌번호입니다", -200);
		}
		accounts.put(account.getAccountNum(), account);
	}

	// 호출한 곳에 주는것이라 동기처리 안해도 된다.

	/**
	 * @return 계좌를 모두 출력
	 */
	public List list() {
		// return accounts;
		// 제네릭 안쓰고 해보기
		// 실제 들어있는 것만 반환
		Collection<Account> iter = accounts.values();
		Iterator<Account> iter2 = iter.iterator();
		
		List tempAccounts = new ArrayList(accounts.size());
		while (iter2.hasNext()) {
			tempAccounts.add(iter2.next());
		}
		
		return tempAccounts;
		// sort 필요(정렬)

		// search Type 오버로딩
	}

	
	/**
	 * @param accountNum 계좌번호
	 * @return 주어진 계좌번호와 동일한 계좌
	 */
	public Account get(String accountNum) {
		Account result = accounts.get(accountNum);
		if (result == null) {
			System.out.println("찾는 계좌가 없습니다.");
			return null;
		}

		return result;

	}

	/**
	 * @param accountOwner 계좌 소유주
	 * @return 계좌 소유주가 동일한 계좌들
	 */
	public List search(String accountOwner) {
		List<Account> temp = new ArrayList<Account>();
		Collection<Account> blank = accounts.values();
		Iterator<Account> e = blank.iterator();

		while (e.hasNext()) {
			Account tempAccount = e.next();
			if (tempAccount.getAccountOwner().equals(accountOwner)) {
				temp.add(tempAccount);
			}
		}

		if (temp.size() == 0) {
			System.out.println("찾는 계좌가 없습니다.");
			return temp;
		} else {
			return temp;
		}

	}

	/**
	 * @param accountNum 계좌번호
	 * @return 계좌번호와 동일한 계좌 제거 성공시 true, 실패시 false;
	 */
	public boolean remove(String accountNum) {
		if (accounts.containsKey(accountNum)) {
			accounts.remove(accountNum);
			return true;
		} else {
			System.out.println("삭제 실패");
			return false;
		}
		
		// return accounts.remove(accountNum) != null;
		
		// 삭제완료.. true 삭제실패 false;
		// 주석달기
		// 지운 후 정렬
	}

}
