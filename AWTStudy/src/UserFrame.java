import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.w3c.dom.events.MouseEvent;

public class UserFrame extends Frame implements MouseListener,WindowListener {

// UserFrame과 Frame의 복합(Composition)관계
//	String title;
	Button nButton, eButton, wButton, sButton, cButton;

	public UserFrame() {
		this("이름없음");
	}

	public UserFrame(String title) {
		super(title);
		this.eButton = new Button("East");
		this.wButton = new Button("West");
		this.sButton = new Button("South");
		this.nButton = new Button("North");
		this.cButton = new Button("Center");
	}

	// 화면 배치
	public void setContents() {
		// 레이아웃매니저 교체
		// LayoutManager
		setLayout(new FlowLayout());
		add(eButton, BorderLayout.EAST);
		add(wButton, BorderLayout.WEST);
		add(sButton, BorderLayout.SOUTH);
		add(nButton, BorderLayout.NORTH);
		add(cButton, BorderLayout.CENTER);

		// 이벤트소스에 이벤트리스너 연결
		// eButton.addMouseListener(this);
		// this = UserFrame;
	}

	public void eventRegist() {
		eButton.addMouseListener(this);
		wButton.addMouseListener(this);
		sButton.addMouseListener(this);
		nButton.addMouseListener(this);
		cButton.addMouseListener(this);
		addWindowListener(this);
	}

	public static void main(String[] args) {
		UserFrame frame = new UserFrame("타이틀입니다.");
		frame.setContents(); // setContentsView와 비슷
		frame.eventRegist();
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	// JVM에 의해 메소드들을 자동호출 : Callback 메소드(안드로이드,hashcode());
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// System.out.println("마우스 클릭되었습니다.");
		Object eventSource = e.getSource();
		// 원인을 알아보기
		System.out.println(eventSource);
		System.out.println(e.getID());
		Button button = (Button) eventSource;
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		if(eventSource == eButton) {
			System.out.println("East 클릭입니다.");
		}else if(eventSource == wButton) {
			System.out.println("West 클릭입니다.");
		}
		
		button.setBackground(new Color(r, g, b));
//		if (eventSource.toString().equals("java.awt.Button[button0,323,43,42x23,label=West]")) {
//		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		System.out.println("mouseEntered() called...");
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		System.out.println("mouseExited() called...");
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		System.out.println("mousePressed() called...");
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		System.out.println("mouseReleased() called...");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		// 리소스 관련 메소드
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		dispose(); // os에 그래픽리소스 반납
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("창이 열렸습니다.");
	}

}
