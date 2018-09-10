package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * Account를 확장한 마이너스 계좌
 * 
 * @author 박호준
 *
 */
public class MinusAccount extends Account {

	// 인스턴스 변수 : 대출금액
	private long borrowMoney;

	// 생성자
	public MinusAccount() {
		this(null, null, 0, 0, 0);
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	// Getter/Setter
	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	
	/**
	 * @return 잔액 - 대출금액
	 */
	public long getNetRestMoney() {
		return super.getRestMoney() - this.getBorrowMoney();
	}
	
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
		startformater.format("%-14s", " 마이너스");
		String startStr = startformater.toString();
		
		// 계좌번호 포맷
		Formatter accountNumStrformater = new Formatter();
		accountNumStrformater.format("%8s", super.getAccountNum());
		String accountNumStr = accountNumStrformater.toString();
		
		// 소유주 포맷
		Formatter accountOwnerStrformater = new Formatter();
		accountOwnerStrformater.format("%16s", super.getAccountOwner());
		String accountOwnerStr = accountOwnerStrformater.toString();
		
		// 잔액 포맷
		Formatter restStrformater = new Formatter();
		
		if(this.getRestMoney() >= 0) {
			restStrformater.format("%,28d", this.getNetRestMoney());	
		}else {
			restStrformater.format("%,27d", this.getNetRestMoney());
		}
		
		String restStr = restStrformater.toString();
		
		// 대출금액 포맷
		Formatter Borrowformater = new Formatter();
		Borrowformater.format("%,25d", this.getBorrowMoney());
		String borrowStr = Borrowformater.toString();
		
		return startStr + accountNumStr + accountOwnerStr + restStr + borrowStr;
	}
	
	
}
