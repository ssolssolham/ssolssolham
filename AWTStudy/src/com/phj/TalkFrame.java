package com.phj;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TalkFrame extends Frame implements WindowListener {

	LoginPanel loginPanel;
	MainPanel mainPanel;

	Panel cardPanel;
	CardLayout cardLayout;

	public TalkFrame() {
		this("이름");
	}

	public TalkFrame(String string) {
		super(string);
		loginPanel = new LoginPanel(this);
		mainPanel = new MainPanel(this);

		cardPanel = new Panel();
		cardLayout = new CardLayout();
	}

	public void setContents() {
		cardPanel.setLayout(cardLayout);
		cardPanel.add("LOGIN", loginPanel);
		cardPanel.add("MAIN", mainPanel);

		add(cardPanel, BorderLayout.CENTER);
	}

	public void setCenter() {

		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}

	public void changeCard(String name) {
		cardLayout.show(cardPanel, name);
	}

	private void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void eventRegist() {
		addWindowListener(this);
	}

	public static void main(String[] args) {
		TalkFrame frame = new TalkFrame("TalkFrame");
		frame.setContents();
		frame.setSize(500, 600);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		finish();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
