package kr.or.kosta.chat.client;

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

import kr.or.kosta.chat.common.Protocol;

public class ChatFrame extends Frame {

	private ChatClient chatClient;
	private String nickName;

	Label nickNameL;
	TextField nickNameTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userList;

	MenuBar menuBar;
	Menu menu;
	MenuItem newMI, exitMI;

	public ChatFrame() {
		this("이름없음");
	}

	public ChatFrame(String title) {
		super(title);
		nickNameL = new Label("대화명");
		nickNameTF = new TextField();
		inputTF = new TextField();
		connectB = new Button("CONNECT");
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

	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	// 화면 배치
	public void setContents() {
		connectB.setFont(new Font("궁서", Font.BOLD, 20));
		// 1번째 폰트, 글자 속성, 크기

		Panel northP = new Panel();
		northP.setLayout(new BorderLayout());
		northP.add(nickNameL, BorderLayout.WEST);
		northP.add(nickNameTF, BorderLayout.CENTER);
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

	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void connect() {
		nickName = nickNameTF.getText();
		try {
			chatClient.connectServer();
			// 최초 연결 메시지 전송
			chatClient.sendMessage(Protocol.CONNECT + Protocol.DELEMETER + nickName);
			// 구분자를 필요로 한다.
			appendMessage("@@@@@ ChatServer와 연결되었습니다 @@@@@@");

			String message = chatClient.readMessage();
			String[] tokens = message.split(Protocol.DELEMETER);
			if(tokens[1].equals("SUCCESS")) {
				chatClient.receiveMessage();
			}else{
				JOptionPane.showMessageDialog(this, "이미 사용중인 대화명입니다. \n 다른 대화명을 사용하세요", "에러", JOptionPane.ERROR_MESSAGE);
				chatClient.stopClient();
				return;
			};
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "연결실패", JOptionPane.ERROR_MESSAGE);
		}

		nickNameTF.setEnabled(false);
		connectB.setEnabled(false);

	}

	public void sendMessage() {
		String message = inputTF.getText();
		// 유효성 검사
		if (message == null || message.trim().length() == 0) {
			return;
		}
		inputTF.setText("");
		System.out.println(message);
	}

	public void appendMessage(String message) {
		messageTA.append(message + "\n");
		inputTF.setText("");

	}

	public void eventRegist() {
		nickNameTF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		connectB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		inputTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 서버로 메시지 전송...
				sendMessage();
			}
		});

		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 서버로 메시지 전송...
				sendMessage();
			}
		});

		userList.addItemListener(new ItemListener() {
			// 1:1 쪽지보내기
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String name = userList.getSelectedItem();
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
}
