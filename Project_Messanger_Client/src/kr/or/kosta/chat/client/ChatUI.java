package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.or.kosta.chat.common.Protocol;

public class ChatUI extends Frame {

	private ChatClient chatClient;
	private String nickName;

	private Panel innerPanel;
	private EnrollPanel enrollPanel;
	private WaitingRoomPanel waitingRoomPanel;
	private ChattingRoomPanel chattingRoomPanel;
	private CardLayout cardLayout;
	private CreateFrame createFrame;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public EnrollPanel getEnrollPanel() {
		return enrollPanel;
	}

	public void setEnrollPanel(EnrollPanel enrollPanel) {
		this.enrollPanel = enrollPanel;
	}

	public WaitingRoomPanel getWaitingRoomPanel() {
		return waitingRoomPanel;
	}

	public void setWaitingRoomPanel(WaitingRoomPanel waitingRoomPanel) {
		this.waitingRoomPanel = waitingRoomPanel;
	}

	public ChattingRoomPanel getChattingRoomPanel() {
		return chattingRoomPanel;
	}

	public void setChattingRoomPanel(ChattingRoomPanel chattingRoomPanel) {
		this.chattingRoomPanel = chattingRoomPanel;
	}

	public Panel getInnerPanel() {
		return innerPanel;
	}

	public void setInnerPanel(Panel innerPanel) {
		this.innerPanel = innerPanel;
	}

	public ChatUI() {
		this("이름없음");
	}

	public ChatUI(String title) {
		super(title);
		innerPanel = new Panel();
		cardLayout = new CardLayout();
		waitingRoomPanel = new WaitingRoomPanel();
		enrollPanel = new EnrollPanel();
		chattingRoomPanel = new ChattingRoomPanel();
	}

	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	// 화면 배치
	public void setContents() {
		innerPanel.setLayout(cardLayout);
		innerPanel.add("enroll", enrollPanel);
		innerPanel.add("wait", waitingRoomPanel);
		innerPanel.add("chat", chattingRoomPanel);

		add(innerPanel, BorderLayout.CENTER);
		cardLayout.show(innerPanel, "enroll");
	}

	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}

	public void connect() {
		nickName = enrollPanel.nickNameTF.getText();
		try {
			chatClient.connectServer();

			// 최초 연결 메시지 전송
			chatClient.sendMessage(Protocol.CONNECT + Protocol.DELEMETER + nickName);

			// 기존 사용자 유지로 수정해야함
			// userList.add(nickName);

			chatClient.receiveMessage();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "연결 실패", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void showRoom() {
		chatClient.sendMessage(Protocol.ROOM_SEARCH + "");
	}
	public void createRoom() {
		createFrame = new CreateFrame(this);
		createFrame.setContents();
		createFrame.setSize(400,200);
		createFrame.setVisible(true);
		createFrame.eventRegist();
//		chatClient.sendMessage(Protocol.ROOM_CREATE + Protocol.DELEMETER + waitingRoomPanel.get);
	}	
	
	public void finish() {
		chatClient.sendMessage(Protocol.DISCONNECT + Protocol.DELEMETER + nickName);
		chatClient.stopClient();

		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void showCard(String cardName) {
		cardLayout.show(innerPanel, cardName);

	}

	public void eventRegist(int num) {
		switch (num) {
		case 1:
			enrollPanel.nickNameTF.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					connect();
				}
			});

			enrollPanel.confirmB.addActionListener(new ActionListener() {
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
		case 2:
			waitingRoomPanel.searchImageButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			waitingRoomPanel.createB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					createRoom();
				}
			});
			break;
		case 3: 
			
		}
	}
}