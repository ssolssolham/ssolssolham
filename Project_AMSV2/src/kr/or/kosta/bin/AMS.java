package kr.or.kosta.bin;

import java.io.IOException;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.AccountDao;

/**
 * 은행 계좌 관리 애플리케이션
 *  1. AccountManager, Frame 객체 생성
 *  2. Frame의 Setter인 setManager로 Frame의 인스턴스 변수 초기화
 *  3. Frame의 크기 변경
 *  4. Frame의 중앙정렬
 *  5. Frame 보이기 
 *  
 * @author 박호준
 */
public class AMS {

	public static void main(String[] args) {
		
		// AccountManager 객체 생성
		MainFrame frame = new MainFrame("MainFrame");
		try {
			frame.setAccountDao(new AccountDao());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setContents();
		frame.setSize(800, 600);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
	}
}
