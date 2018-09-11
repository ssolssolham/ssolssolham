package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class TablePanel extends Panel {
	JLabel roomNumberL, titleL, numOfPeopleL,idL,nickNameL;
	BevelBorder border;
	Panel superFrame;

	public TablePanel(int panelType) {
		switch (panelType) {
		case 1:
			this.roomNumberL = new JLabel("방번호", JLabel.CENTER);
			this.titleL = new JLabel("제목", JLabel.CENTER);
			this.numOfPeopleL = new JLabel("인원", JLabel.CENTER);
			
			border = new BevelBorder(BevelBorder.RAISED);
			roomNumberL.setBorder(border);
			titleL.setBorder(border);
			numOfPeopleL.setBorder(border);

			setLayout(new BorderLayout());
			this.add(roomNumberL, BorderLayout.WEST);
			this.add(titleL, BorderLayout.CENTER);
			this.add(numOfPeopleL, BorderLayout.EAST);
			setBackground(Color.WHITE);
			break;
		case 2:
			this.idL = new JLabel("아이디", JLabel.CENTER);
			this.nickNameL = new JLabel("닉네임", JLabel.CENTER);
			
			border = new BevelBorder(BevelBorder.RAISED);
			idL.setBorder(border);
			nickNameL.setBorder(border);

			setLayout(new FlowLayout());
			this.add(idL);
			this.add(nickNameL);
			setBackground(Color.WHITE);
			break;
		}
	}

	public TablePanel(Panel superFrame, String roomNumber, String title, String numOfPeople) {
		this.superFrame = superFrame;
		this.roomNumberL = new JLabel(roomNumber, JLabel.CENTER);
		this.titleL = new JLabel(title, JLabel.CENTER);
		this.numOfPeopleL = new JLabel(numOfPeople, JLabel.CENTER);
	}
}
