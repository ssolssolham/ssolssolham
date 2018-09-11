package kr.or.kosta.chat.client;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JOptionPane;

public class EnrollPanel extends Panel {
	Label nickNameL, explainL;
	TextField nickNameTF;
	Button confirmB;

	public EnrollPanel() {
		nickNameL = new Label("닉네임");
		nickNameTF = new TextField(20);
		confirmB = new Button("등록");
		explainL = new Label("닉네임을 등록해주세요 !");
		nickNameL.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		explainL.setForeground(new Color(244,30,30));
//		explainL
//		nickNameL.setBackground(new Color(255,83,13));
		setBackground(new Color(170, 178, 189));

		setLayout(new FlowLayout());
		add(nickNameL);
		add(nickNameTF);
		add(confirmB);
		add(explainL);

	}

}
