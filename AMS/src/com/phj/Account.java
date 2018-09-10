package com.phj;
/**
 * 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의 은행계좌 객체
 */
public class Account {

	static int a, b, c;

	// static 초기화 블럭(특수한 목적의 명령어 실행)
	static {
		System.out.println("초기화 블럭 실행입니다..1");
		System.out.println("초기화 블럭 실행입니다..2");
	}
	// 클래스(static) 변수
	public final static String bankName = "하나은행";

	// 인스턴스 변수 선언 C언어의 구조체(UNION) - 구조체에서는 데이터밖에 없다.
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;

	// 생성자

	public Account() {
		this(null, null, 0, 0);
		/*
		 * accountNum = null; accountOwner = null; passwd = 0; restMoney = 0L;
		 */
	}

	public Account(String accountNum) {
		this.accountNum = accountNum;
	}

// 생성자 내 매개변수의 데이터 타입이나 개수가 달라야 한다.
	public Account(int passwd) {
		this.passwd = passwd;
	}

	/*
	 * Account(String accountOwner){ this.accountOwner = accountOwner; }
	 */

	public Account(String accountNum, String accountOwner) {
		// this.accountNum = accountNum;
		// this.accountOwner = accountOwner;
		this(accountNum, accountOwner, 0, 0);
	}

	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		// this(accountNum,accountOwner);
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

	// 인스턴스 메소드 선언
	// 자기 스코프?
	long deposit(long money) throws AccountException{
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -1);
		}
		
		long blank = this.getRestMoney() + money;
		this.setRestMoney(blank);
		return this.getRestMoney();
	};

	// 예외 간접처리 필요?
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

	// 설계도대로 해야한다.
	boolean checkPasswd(int pwd) {
		/*
		 * if(this.passwd == passwd){ return true; } else{ return false; }
		 */
		return this.passwd == pwd;

	};

	public String toString() {
		return "계좌번호 : " + accountNum + "\t" + "계좌 소유주 : " + accountOwner + "\t" + "비밀번호 : **** " + "\t" + "잔액 : "
				+ restMoney;
	}

	// 클래스(static) 메소드
	public static int sum(int a, int b) {
		return a + b;
	}

	public boolean equals(Account account) {
		// 위임형 비교(중요)
		boolean eq = false;
		if (account instanceof Account) {
			eq = this.toString().equals(account.toString());
		}
		return eq;
//		if(this.accountNum.equals(account.accountNum) && this.accountOwner.equals(accountOwner) && this.passwd == account.passwd && this.restMoney == account.restMoney) {
//			return true;		
//		}else {
//			return false;	
//		}

	}

}
