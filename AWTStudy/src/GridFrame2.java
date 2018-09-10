import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GridFrame2 extends Frame implements WindowListener {

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	Label acceptL, attachL, titleL;
	TextField acceptTF, attachTF, titleTF;
	Button findB, sendB, cancelB;
	TextArea messageTA;

	public GridFrame2() {
		super("화면 실습");
		acceptL = new Label("받는사람");
		attachL = new Label("첨부파일");
		titleL = new Label("제목");

		acceptTF = new TextField();
		attachTF = new TextField();
		titleTF = new TextField();

		findB = new Button("찾기");
		sendB = new Button("보내기");
		cancelB = new Button("취 소");

		messageTA = new TextArea();

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

	}

	public void setContents() {
		setLayout(gridBagLayout);
		add(acceptL, 0, 0, 1, 1, 0, 0);
		add(acceptTF, 1, 0, 2, 1, 1, 0);

		add(attachL, 0, 1, 1, 1, 0, 0);
		add(attachTF, 1, 1, 2, 1, 2, 0);
		add(findB, 3, 1, 1, 1, 0, 0);

		add(titleL, 0, 2, 1, 1, 0, 0);
		add(titleTF, 1, 2, 3, 1, 2, 0);

		add(messageTA, 0, 3, 7, 1, 1, 0);

		Panel panel = new Panel();
		panel.add(sendB);
		panel.add(cancelB);
		add(panel, 2, 4, 1, 1, 0, 0);
//		add(cancelB,3,4,1,1,0,0);

	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(3, 3, 3, 3);
		// margin
		// 격자 하나짜리
		gridBagLayout.setConstraints(component, gridBagConstraints);
		add(component);
	}

	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	public void eventRegist() {
		addWindowListener(this);
	}

	public static void main(String[] args) {
		GridFrame2 gridFrame = new GridFrame2();
		gridFrame.setContents();
		gridFrame.pack();
		gridFrame.eventRegist();
		gridFrame.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
