package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.or.kosta.entity.AccountDao;


/**
 * MainFrame의 목적
 *  1. AMS에 사용될 MainFrame 
 *  2. AccountPanel을 담아 놓을 Frame
 * 
 * MainFrame의 구조
 *  1. 인스턴스 변수 : AccountPanel, AccountManager 객체
 *  2. 생성자 : "KOSTA AMS - 메인화면" 제목을 가진 Frame 생성
 *  3. 인스턴스 메소드 : setContents(),setCenter(),finish(),eventRegist(),setManager(AccountManager manager),getManager()
 *    - setContents() : 화면구성
 *    - setCenter() : Frame의 가운데 표기
 *    - finish() : 내부클래스,WindowAdapter를 이용하기 위한 프로그램 종료 버튼 활성화 메소드
 *    - eventRegist() : WindowAdapter를 이용한 이벤트 리스너 연결
 *    - Setter & Getter : setManager(AccountManager manager),getManager()
 *    
 * @author 박호준
 *
 */
public class MainFrame extends Frame {

	AccountPanel accountPanel;
	AccountDao accountDao;
	
	/**
	 * 디폴트 생성자
	 */
	public MainFrame() {
		super("KOSTA AMS - 메인화면");
	}

	/**
	 * Frame의 이름을 title로 함.
	 * @param title : Frame의 제목
	 */
	public MainFrame(String title) {
		super(title);
		accountPanel = new AccountPanel(this);
	}

	/**
	 * Frame의 BorderLayout에 accountPanel 추가
	 */
	public void setContents() {
		add(accountPanel,BorderLayout.CENTER);
	}

	/**
	 * Frame의 중앙정렬
	 */
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}

	/**
	 * 프로그램 종료버튼 메소드화
	 */
	private void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	/**
	 * WindowAdapter를 활용한 프로그램 종료버튼 활성화 
	 */
	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}
	
	/**
	 * AccountManager의 Setter/Getter 
	 */

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}





}
