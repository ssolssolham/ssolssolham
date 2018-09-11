package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class ChattingRoomPanel extends Panel {

	private ChatClient chatClient;
	private String nickName;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	Label nickNameL,userListL;
	TextField nickNameTF, inputTF;
	Button sendB,inviteB,oneToOneB;
	TextArea messageTA;
	List userList;

	public ChattingRoomPanel() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		nickNameL = new Label("방 제목");
		userListL = new Label("접속자 목록");
		nickNameTF = new TextField(50);
		inputTF = new TextField();
		sendB = new Button("SEND");
		inviteB = new Button("초대");
		oneToOneB = new Button("1:1 대화");
		
		messageTA = new TextArea(30,10);
		userList = new List(30);
		setContents();

	}

	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	// 화면 배치
	public void setContents() {
		setLayout(gridBagLayout);
		setBackground(new Color(170, 178, 189));
		
		add(nickNameL, 	0, 0, 1, 1, 0, 0);
		add(nickNameTF, 1, 0, 1, 1, 0, 0);
		add(messageTA, 	0, 1, 4, 4, 0, 0);
		add(userListL, 	5, 0, 1, 1, 0, 0);
		
		add(userList, 	5, 1, 1, 4, 0, 0);
		add(inputTF, 	0, 5, 2, 1, 0, 0);
		add(sendB, 		2, 5, 1, 1, 0, 0);
		add(inviteB, 	3, 5, 1, 1, 0, 0);
		add(oneToOneB,  4, 5, 2, 1, 0, 0);
		
		setLocation(100, 100);

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
		System.exit(0);
	}

	/**
	 * gridBagConstraints를 이용해 gridBagLayout 바탕의 component에 넣기
	 * 
	 * @param component  : 넣으려는 컴포넌트 ex) Panel,Frame
	 * @param gridx      : x좌표
	 * @param gridy      : y좌표
	 * @param gridwidth  : x의 크기
	 * @param gridheight : y의 크기
	 * @param weightx    : x 가중치
	 * @param weighty    : y 가중치
	 */
	public void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		gridBagLayout.setConstraints(component, gridBagConstraints);
		add(component);
	}

	public void appendMessage(String message) {
		messageTA.append(message + "\n");
		inputTF.setText("");

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

	public void eventRegist() {
		nickNameTF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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

	}
}
