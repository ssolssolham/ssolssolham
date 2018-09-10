package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Frame;

public class MainFrame extends Frame{

	ChatPanel chatPanel;
	FirstPanel firstPanel;
	SecondPanel secondPanel;
	
	/**
	 * 디폴트 생성자
	 */
	public MainFrame() {
		super("KOSTA 메인화면");
	}
	
	public MainFrame(String title) {
		super(title);
		chatPanel = new ChatPanel(this);
		firstPanel = new FirstPanel(this);
		secondPanel = new SecondPanel(this);
	}
	
	public void setContents() {
		add(firstPanel, BorderLayout.CENTER)
	}
	
}
