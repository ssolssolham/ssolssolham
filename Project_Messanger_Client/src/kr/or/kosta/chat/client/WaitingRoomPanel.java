package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import kr.or.kosta.chat.common.Protocol;

public class WaitingRoomPanel extends Panel {

	private ChatClient chatClient;
	private String nickName;

	Button roomListB, modifyB, exitB, searchB, disableRoomInfoB, enterB, createB;
	Choice SearchCategory;
	ArrayList<TablePanel> roomlistPanel;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	TextField searchTF;
	List roomList;

	Label nickNameL;

	TextArea infoTA;
	List userList;

	JButton searchImageButton, roomSearchImageButton, modifyButton;
	ChatUI chatFrame;

	public WaitingRoomPanel() {

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		roomListB = new Button("대화방 목록");
		modifyB = new Button("프로필 수정");
		exitB = new Button("종료");
		searchB = new Button();
		disableRoomInfoB = new Button("대화방 정보");
		disableRoomInfoB.setEnabled(false);
		enterB = new Button("채팅방 입장");
		createB = new Button("채팅방 생성");

		searchImageButton = new JButton("대화방 목록", new ImageIcon("src/images/icons4910.png"));
		searchImageButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		searchImageButton.setHorizontalTextPosition(SwingConstants.CENTER);
		searchImageButton.setBorderPainted(false);
		searchImageButton.setContentAreaFilled(false);
//      searchImageButton.setFocusPainted(false);

		modifyButton = new JButton("프로필 수정", new ImageIcon("src/images/icons6112.png"));
		modifyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		modifyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		modifyButton.setBorderPainted(false);
		modifyButton.setContentAreaFilled(false);
//      modifyButton.setFocusPainted(false);

		roomSearchImageButton = new JButton(new ImageIcon("src/images/icons5000.png"));
		roomSearchImageButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		roomSearchImageButton.setHorizontalTextPosition(SwingConstants.CENTER);
		roomSearchImageButton.setBackground(new Color(233, 233, 233));
		roomSearchImageButton.setFocusPainted(false);

		SearchCategory = new Choice();
		SearchCategory.add("대화방 제목");

		searchTF = new TextField();
		infoTA = new TextArea(12, 8);

		roomList = new List(15);
		roomList.add("실험");

		roomlistPanel = new ArrayList<TablePanel>();
		roomlistPanel.add(new TablePanel(1));
		roomlistPanel.add(new TablePanel(2));
		roomlistPanel.add(new TablePanel(this, "1000", "title", "10"));

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
		add(searchImageButton, 0, 0, 2, 2, 1, 0);
		add(modifyButton, 3, 0, 1, 2, 1, 0);

		add(SearchCategory, 0, 2, 2, 2, 0, 0);
		add(searchTF, 2, 2, 2, 2, 1, 0);
		add(roomSearchImageButton, 4, 2, 1, 2, 0, 0);
		add(disableRoomInfoB, 5, 2, 1, 1, 0, 0);
		add(roomlistPanel.get(0), 0, 4, 4, 1, 4, 0);
		add(roomlistPanel.get(1), 5, 4, 1, 1, 1, 0);
		add(roomList, 0, 5, 4, 2, 4, 0);
		add(infoTA, 5, 5, 1, 2, 1, 0);
		add(enterB, 4, 8, 1, 1, 0, 0);
		add(createB, 5, 8, 1, 1, 0, 0);
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

}