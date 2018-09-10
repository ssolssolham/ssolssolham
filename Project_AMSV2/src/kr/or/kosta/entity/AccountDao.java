package kr.or.kosta.entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountDao는 Account 객체들을 파일로 처리하여 생성,수정,저장,삭제를 함.
 * 
 * @author 박호준
 *
 */
public class AccountDao {

	// 저장 파일 경로
	private static final String FILE_PATH = "accounts.dbf";

	// 레코드(계좌)수 저장을 위한 파일 컬럼 사이즈 고정
	private static final int RECORD_COUNT_LENGTH = 4;

	// 레코드(계좌번호,소유주,비밀번호,잔액,대출금액) 저장을 위한 컬럼별 사이즈 고정
	private static final int NUM_LENGTH = 30;
	private static final int OWNER_LENGTH = 10;
	private static final int PASSWD_LENGTH = 4;
	private static final int RESTMONEY_LENGTH = 20;
	private static final int BORROWMONEY_LENGTH = 20;

	// 계좌 저장을 위한 레코드 사이즈 : 84
	public static final int RECORD_LENGTH = NUM_LENGTH + OWNER_LENGTH + PASSWD_LENGTH + RESTMONEY_LENGTH
			+ BORROWMONEY_LENGTH;

	private RandomAccessFile file;

	// 저장된 계좌(레코드) 수
	private int recordCount = 0;

	public AccountDao() throws IOException {
		file = new RandomAccessFile(FILE_PATH, "rw");

		// 저장된 파일이 있는 경우
		if (file.length() != 0) {
			recordCount = file.readInt();
		} else {
			// 저장된 파일이 없는 경우
		}
	}

	// 계좌 정보 저장
	public void create(Account account) throws IOException {

		// 파일의 마지막 레코드끝으로 파일 포인터 이동.
		file.seek((recordCount * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		// 새로운 레코드(계좌) 추가
		String num = account.getAccountNum();
		String owner = account.getAccountOwner() + " " + " ";
		int passwd = account.getPasswd();
		long restMoney = account.getRestMoney();
		long borrowMoney = 0;

		if (account instanceof MinusAccount) {
			MinusAccount maccount = ((MinusAccount) account);
			borrowMoney = maccount.getBorrowMoney();
		}

		// 이름 저장
		int charCount = num.length();
		int charCount2 = owner.length();

		// 출력순서 계좌번호, 소유주, 비밀번호, 잔액
		for (int i = 0; i < (NUM_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? num.charAt(i) : ' '));
		}

		for (int i = 0; i < (OWNER_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? owner.charAt(i) : ' '));
		}

		// 비밀번호(4바이트)
		file.writeInt(passwd);
		// 잔액(8바이트)
		file.writeLong(restMoney);

		// MinusAccount인지 판단하고 파일에 출력
		if (account instanceof MinusAccount) {
			file.writeLong(borrowMoney);
		} else {
			file.writeLong(-1);
		}

		// 레코드 저장 후 파일포인터를 파일의 처음으로 이동시켜
		// 등록된 레코드(계좌) 수 1 증가
		file.seek(0);
		file.writeInt(++recordCount);
	}

	// 계좌 정보 저장
	public void update(Account account, int idx) throws IOException {
		// 파일의 마지막 레코드끝으로 파일 포인터 이동.
		file.seek((idx * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		// 새로운 레코드(계좌) 추가
		String num = account.getAccountNum();
		String owner = account.getAccountOwner() + " " + " ";
		int passwd = account.getPasswd();
		long restMoney = account.getRestMoney();
		long borrowMoney = 0;

		// MinusAccount인지 판단하고 대출금액 입력
		if (account instanceof MinusAccount) {
			MinusAccount maccount = ((MinusAccount) account);
			borrowMoney = maccount.getBorrowMoney();
		}

		// 이름 저장
		int charCount = num.length();
		int charCount2 = owner.length();

		// 출력순서 계좌번호, 소유주, 비밀번호, 잔액
		for (int i = 0; i < (NUM_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? num.charAt(i) : ' '));
		}

		for (int i = 0; i < (OWNER_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? owner.charAt(i) : ' '));
		}

		// 비밀번호(4바이트)
		file.writeInt(passwd);
		// 잔액(8바이트)
		file.writeLong(restMoney);

		// MinusAccount인지 판단하고 파일에 출력
		if (account instanceof MinusAccount) {
			file.writeLong(borrowMoney);
		} else {
			file.writeLong(-1);
		}
	}

	/** 등록된 전체리스트 반환 */
	public List<Account> listAll() throws IOException {
		List<Account> list = new ArrayList<Account>();
		for (int i = 0; i < recordCount; i++) {
			Account account = read(i);
			list.add(account);
		}
		return list;
	}

	/**
	 * 특정 레코드의 정보를 Account로 반환
	 * 
	 * @param index : 찾을 인덱스의 번호
	 * @return 인덱스를 통해 찾을 Account
	 * @throws IOException 입출력 오류
	 */
	private Account read(int index) throws IOException {

		Account account = null;

		String num = "";
		// 특정 인덱스의 레코드로 포인터를 이동
		file.seek((index * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		// 출력
		for (int i = 0; i < (NUM_LENGTH / 2); i++) {
			num += file.readChar();
		}
		num = num.trim();

		String owner = "";
		for (int i = 0; i < (OWNER_LENGTH / 2); i++) {
			owner += file.readChar();
		}
		owner = owner.trim();

		int passwd = 0;
		passwd = file.readInt();

		long restMoney = 0l;
		restMoney = file.readLong();

		long borrowMoney = 0l;
		borrowMoney = file.readLong();

		// Account와 MinusAccount를 판단하여 객체생성
		if (borrowMoney == -1) {
			account = new Account(num, owner, passwd, restMoney);
		} else {
			account = new MinusAccount(num, owner, passwd, restMoney, borrowMoney);
		}

		return account;

	}

	/**
	 * 계좌번호로 계좌를 찾는 메소드
	 * 
	 * @param accountNum : 찾을 계좌의 계좌번호
	 * @return 찾을 계좌
	 * @throws IOException
	 */
	public Account get(String accountNum) throws IOException {

		Account result = null;
		String num = "";
		String owner = "";
		int passwd = 0;
		long restMoney = 0l;
		long borrowMoney = 0l;

		for (int i = 0; i < recordCount; i++) {
			num = "";

			// 포인터를 인덱스(i) 만큼 움직임
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
			for (int j = 0; j < (NUM_LENGTH / 2); j++) {
				num += file.readChar();
			}
			num = num.trim();

			// 일치할 경우 출력
			if (num.equals(accountNum)) {
				for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
					owner += file.readChar();
				}
				owner = owner.trim();
				passwd = file.readInt();
				restMoney = file.readLong();
				borrowMoney = file.readLong();

				// 객체의 생성
				if (borrowMoney != -1) {
					result = new MinusAccount(num, owner, passwd, restMoney, borrowMoney);
					return result;
				} else {
					result = new Account(num, owner, passwd, restMoney);
					return result;
				}
			}
		}
		return result;
	}

	/**
	 * 계좌번호를 통해 찾는 계좌의 포인터 위치를 반환
	 * 
	 * @param accountNum : 계좌번호
	 * @return : 포인터 위치 반환
	 * @throws IOException : 입출력 오류
	 */
	public int getPoint(String accountNum) throws IOException {

		String num = "";

		for (int i = 0; i < recordCount; i++) {
			// 파일 작성을 위한 포인터 위치를 변경
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
			for (int j = 0; j < (NUM_LENGTH / 2); j++) {
				num += file.readChar();
			}
			num = num.trim();
			if (num.equals(accountNum)) {
				return i + 1;
			} else {
				num = "";
			}
		}

		return 0;
	}

	/**
	 * 소유주의 계좌들을 반환하는 메소드
	 * 
	 * @param accountOwner : 검색하고자 하는 계좌의 소유주
	 * @return List<Account> : 계좌의 모음
	 * @throws IOException
	 */
	public List<Account> search(String accountOwner) throws IOException {
		List<Account> result = new ArrayList<Account>();
		String num = "";
		String owner = "";
		int passwd = 0;
		long restMoney = 0l;
		long borrowMoney = 0l;

		for (int i = 0; i < recordCount; i++) {
			
			// owner값 초기화
			owner = "";
			// 파일 내에서 검색을 위해 포인터 위치 변경
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH + NUM_LENGTH);
			for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
				owner += file.readChar();
			}
			owner = owner.trim();

			if (owner.equals(accountOwner)) {
				// 검색하고자 하는 이름과 일치할 경우
				file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
				for (int j = 0; j < (NUM_LENGTH / 2); j++) {
					num += file.readChar();
				}
				num = num.trim();
				for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
					file.readChar();
				}
				passwd = file.readInt();
				restMoney = file.readLong();
				borrowMoney = file.readLong();

				// Account와 MinusAccount를 판단하여 생성
				if (borrowMoney != -1) {
					result.add(new MinusAccount(num, owner, passwd, restMoney, borrowMoney));
					owner = "";
					num = "";
				} else {
					result.add(new Account(num, owner, passwd, restMoney));
					owner = "";
					num = "";
				}
			}
		}
		return result;
	}

	/**
	 * @param accountNum : 지우고자 하는 계좌의 계좌번호
	 * @return 삭제여부를 반환
	 * @throws IOException : 입출력 오류
	 */
	public boolean remove(String accountNum) throws IOException {
		Account account = null;
		if ((account = get(accountNum)) != null) {

			if (getPoint(accountNum) == recordCount) {
				// 삭제하려는 곳이 끝일 때
				file.seek(0);
				file.writeInt(--recordCount);
			} else {
				// 삭제하려는 곳이 끝이 아닐 때
				for (int i = (getPoint(accountNum) - 1); i < recordCount - 1; i++) {
					account = read(i + 1);
					update(account, i);
				}
				file.seek(0);
				file.writeInt(--recordCount);
			}
			return true;
		} else {
			return false;
		}

	}

	// 스트림 닫기
	public void close() {
		try {
			if (file != null)
				file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
