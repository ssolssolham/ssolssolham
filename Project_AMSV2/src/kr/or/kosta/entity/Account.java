package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의 은행계좌 객체
 * 
 *  @author 박호준
 */
public class Account {

	// 클래스(static) 변수 : 은행이름
	public final static String bankName = "KOSTA 은행";

	// 인스턴스 변수 : 계좌번호, 소유주, 비밀번호, 잔액
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;

	// 생성자 
	public Account() {
		this(null, null, 0, 0);
	}

	public Account(String accountNum) {
		this.accountNum = accountNum;
	}

	public Account(int passwd) {
		this.passwd = passwd;
	}

	public Account(String accountNum, String accountOwner) {
		this(accountNum, accountOwner, 0, 0);
	}

	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	// setter/getter 메소드
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountOwner() {
		return this.accountOwner;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public int getPasswd() {
		return this.passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	public long getRestMoney() {
		return this.restMoney;
	};

	
	/**
	 * @param money : 입금할 금액
	 * @return 
	 * 입금 후 금액
	 * @throws AccountException : 입금금액이 음수일 경우 오류
	 */
	long deposit(long money) throws AccountException{
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -1);
		}
		
		long blank = this.getRestMoney() + money;
		this.setRestMoney(blank);
		return this.getRestMoney();
	};

	/**
	 * @param money : 출금할 금액
	 * @return
	 * 출금 후 금액
	 * @throws AccountException : 출금금액이 음수일 경우 오류
	 */
	long withdraw(long money) throws AccountException {
		// 유효성검증 필요 잔액부족
		// 잔액부족, 음수값이 들어올 수 있다.

		if (money <= 0) {
			throw new AccountException("출금하고자 하는 금액은 음수일 수 없습니다.", -1);
		}
		
		if (money > restMoney) {
			throw new AccountException("잔액이 부족합니다.", -2);
		}

		this.restMoney -= money;
		return this.restMoney;
	};

	/**
	 * @param pwd : 확인할 비밀번호
	 * @return
	 * 비밀번호가 일치하면 true, 다르면 false;
	 */
	boolean checkPasswd(int pwd) {
		return this.passwd == pwd;
	};

	/* 
	 * toString 오버라이드
	 * Formatter 클래스를 통한 출력시 정렬
	 * 
	 * @return Frame에서 출력시 사용될 내용
	 */
	@Override
	public String toString() {
		
		// 시작 포맷
		Formatter startformater = new Formatter();
		startformater.format("%-16s", " 입출금");
		String startStr = startformater.toString();
		
		// 계좌번호 포맷
		Formatter accountNumStrformater = new Formatter();
		accountNumStrformater.format("%8s", accountNum);
		String accountNumStr = accountNumStrformater.toString();
		
		// 소유주 포맷
		Formatter accountOwnerStrformater = new Formatter();
		accountOwnerStrformater.format("%16s", accountOwner);
		String accountOwnerStr = accountOwnerStrformater.toString();
		
		// 잔액 포맷
		Formatter restStrformater = new Formatter();
		restStrformater.format("%,28d", restMoney);
		String restStr = restStrformater.toString();
		
		return startStr + accountNumStr + accountOwnerStr + restStr;
	}

	/**
	 * @param account : 계좌
	 * @return 비교하는 계좌가 일치하면 true, 다르면 false  
	 */
	public boolean equals(Account account) {
		boolean eq = false;
		if (account instanceof Account) {
			eq = this.toString().equals(account.toString());
		}
		return eq;
	}
	
}
