package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends Panel{

	MainFrame mainFrame;
	
	Label nickName;
	TextField nickNameTF;
	Button enrollB; 
	
	public FirstPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		nickName = new Label("이름 : ");
		nickNameTF = new TextField();
		enrollB = new Button("등록하기");
		setContents();
		eventRegist();
	}
	
	public void setContents() {
		add(nickName);
		add(nickNameTF);
		add(enrollB);
	}
	
	public void eventRegist() {
		enrollB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enroll();
			}
		});
	}
	
	public void enroll() {
		if(mainFrame.)
	}
}
