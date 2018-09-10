package com.phj;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends Panel implements ActionListener{

	Label tempL;
	Button logoutB;

	TalkFrame talkFrame;

	public MainPanel(TalkFrame talkFrame) {
		this.talkFrame = talkFrame;
		tempL = new Label("This is Test Panel", Label.CENTER);
		logoutB = new Button("LOGOUT");
		setContents();
		
		logoutB.addActionListener(this);

	}

	public void setContents() {
		setLayout(new BorderLayout());
		Panel panel = new Panel();
		panel.add(logoutB);
		
		add(tempL, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		talkFrame.changeCard("LOGIN");
	}

}
