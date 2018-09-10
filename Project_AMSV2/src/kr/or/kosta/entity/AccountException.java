package kr.or.kosta.entity;

/**
 * 계좌 처리 간 발생하는 에러를 예외로 규정한 클래스
 * 
 * @author 박호준
 *
 */
public class AccountException extends Exception {
	// String message;
	private int errorCode;

	
	// 생성자 : 에러메시지와 에러코드 번호를 받아 생성
	public AccountException() {
		this("계좌처리중 예기치 않은 에러가 발생하였습니다.", -9);
	}


	public AccountException(String arg0, int errorCode) {
		super(arg0);
		this.errorCode = errorCode;
	}


	
	/**
	 * Getter 메소드
	 */
	public int getErrorCode() {
		return errorCode;
	}


	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}

	
}
