import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ChatFrame extends Frame {
	
	Label serverL;
	TextField serverTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userList;
	
	MenuBar menuBar;
	Menu menu;
	MenuItem newMI,exitMI;

// UserFrame과 Frame의 복합(Composition)관계
//	String title;

	public ChatFrame() {
		this("이름없음");
	}

	public ChatFrame(String title) {
		super(title);
		serverL = new Label("SERVER");
		serverTF = new TextField();
		inputTF = new TextField();
		connectB = new Button("CONNECT") {
			// 이름없는 지역 내부 클래스
			@Override
			public void paint(Graphics g) {
				g.drawLine(0, 0, 50, 0);
			}
		};
		sendB = new Button("SEND");
		messageTA = new TextArea();
		userList = new List();
		userList.add("말미잘");
		userList.add("꼴뚜기");
		userList.add("머저리");
		
		menuBar = new MenuBar();
		menu = new Menu("File");
		newMI = new MenuItem("New");
		newMI.setShortcut(new MenuShortcut(KeyEvent.VK_N, true));
		exitMI = new MenuItem("Exit");
		exitMI.setShortcut(new MenuShortcut(KeyEvent.VK_X));

	}

	// 화면 배치
	public void setContents() {
//		connectB.setEnabled(false);
//		connectB.setBackground(new Color(100,50,122));
//		connectB.setBackground(Color.BLUE);
//		connectB.setForeground(Color.WHITE);

//		setColorAll(Color.BLUE);
//		Panel이 떨어진다. getComponents의 경우
		connectB.setFont(new Font("궁서", Font.BOLD, 20));
		// 1번째 폰트, 글자 속성, 크기

		Panel northP = new Panel();
		northP.setLayout(new BorderLayout());
		northP.add(serverL, BorderLayout.WEST);
		northP.add(serverTF, BorderLayout.CENTER);
		northP.add(connectB, BorderLayout.EAST);

		Panel southP = new Panel();
		southP.setLayout(new BorderLayout());
		southP.add(inputTF, BorderLayout.CENTER);
		southP.add(sendB, BorderLayout.EAST);

		add(northP, BorderLayout.NORTH);
		add(messageTA, BorderLayout.CENTER);
		add(userList, BorderLayout.EAST);
		add(southP, BorderLayout.SOUTH);

		setLocation(100, 100);
//		setColorAll(Color.BLUE);
		// 전체에서 창 크기를 뺀 것 나머지의 반
		
		setMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(newMI);
		menu.addSeparator();
		menu.add(exitMI);

	}

	public void setCenter() {
		// Runtime.getRuntime();
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim); // 확인하면 지워라

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
		// 팩토리 메소드
	}

	public void setColorAll(Color bg) {
		Component[] components = getComponents();
		for (Component component : components) {
			if (component instanceof Panel) {
				Component[] cs = ((Panel) component).getComponents();
				for (Component c : cs) {
					c.setBackground(bg);
				}
				// component 타입으로 떨어지기에 다운캐스팅
			}
			component.setBackground(bg);
		}
	}

	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void appendMessage() {
		String message = inputTF.getText();
		if (message == null || message.trim().length() == 0) {
			return;
		}
		messageTA.append(message + "\n");
		inputTF.setText("");

	}

	public void eventRegist() {
		/* 이름있는 지역 내부 클래스 */
		/*
		 * class Exiter extends WindowAdapter{
		 * 
		 * @Override public void windowClosing(WindowEvent e) { finish(); // finalize();
		 * 가비지 컬렉터가 수거해 가기 전에 특수한 기능을 하는것. } }
		 */

		/* 이름 없는 지역 내부 클래스 */

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		inputTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appendMessage();
			}
		});

		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appendMessage();
			}
		});
		
		serverTF.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyCode());
				System.out.println(e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
		inputTF.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				System.out.println(inputTF.getText());
			}
		});
		
		userList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String name = userList.getSelectedItem();
					// 내부클래스라 this가 안된다!
					JOptionPane.showMessageDialog(null, name + "님 선택이요..", "알림", JOptionPane.INFORMATION_MESSAGE);
//					JOptionPane.showMessageDialog(null, name + "님 선택이요..", "알림", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		exitMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
	}

	/** 멤버 내부 클래스를 이용한 이벤트 처리 */
	/*
	 * class Exiter extends WindowAdapter{
	 * 
	 * @Override public void windowClosing(WindowEvent e) { finish(); // finalize();
	 * 가비지 컬렉터가 수거해 가기 전에 특수한 기능을 하는것. } }
	 */

	// 프로그램 끝날 때 까지 살아있음 --> 이름있는 지역 내부 클래스

	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("Kotalk");
		frame.setContents(); // setContentsView와 비슷
		frame.setSize(400, 500);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
	}

}
