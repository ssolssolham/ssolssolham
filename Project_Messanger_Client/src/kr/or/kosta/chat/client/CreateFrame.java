package kr.or.kosta.chat.client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import kr.or.kosta.chat.common.Protocol;

public class CreateFrame extends Frame {

	Label nickNameL, roomNameL, roomCountL, roomMaxL;
	TextField nickNameTF, roomNameTF, roomCountTF, roomMaxTF;
	Button confirmB, cancelB;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	ChatUI chatUI;

	public CreateFrame() {
		this(new ChatUI());
	}

	public CreateFrame(ChatUI chatUI) {
		this.chatUI = chatUI;
		roomNameL = new Label("방 제목");
		roomMaxL = new Label("수용 인원");

		roomNameTF = new TextField();
		roomMaxTF = new TextField();
		confirmB = new Button("방 만들기");
		cancelB = new Button("취소");

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		setLayout(gridBagLayout);
	}

	// 화면 배치
	public void setContents() {
		setLayout(gridBagLayout);
		setBackground(new Color(170, 178, 189));

		add(roomNameL, 0, 0, 1, 1, 1, 0);
		add(roomNameTF, 1, 0, 2, 1, 2, 0);
		add(roomMaxL, 0, 1, 2, 1, 1, 0);
		add(roomMaxTF, 1, 1, 2, 1, 2, 0);
		add(confirmB, 0, 2, 2, 1, 1, 0);
		add(cancelB, 1, 2, 2, 1, 1, 0);
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

	public static void main(String[] args) {
//		CreateFrame frame = new CreateFrame();
//		frame.setContents();
//		frame.setSize(400, 200);
//		frame.setVisible(true);
//		frame.eventRegist();
	}

	public void finish() {
		setVisible(false);
	}

	public void createRoom() {
		String Message = Protocol.ROOM_CREATE + Protocol.DELEMETER + chatUI.getNickName() + Protocol.DELEMETER + roomNameTF.getText() + Protocol.DELEMETER + "0"
				+ Protocol.DELEMETER + roomMaxTF.getText();
		chatUI.getChatClient().sendMessage(Message);
	}

	public void eventRegist() {
		confirmB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(1111);
				createRoom();
			}
		});
		cancelB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}
}
