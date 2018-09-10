import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class GridFrame extends Frame {
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	Label acceptL,attachL,titleL;
	TextField acceptTF,attachTF,titleTF;
	Button findB,sendB,cancelB;
	TextArea messageTA;
	
	
	public GridFrame() {
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
		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout());
		panel1.add(acceptL);
		panel1.add(acceptTF);
		
		Panel panel2 = new Panel();
		panel2.setLayout(new GridLayout());
		panel2.add(attachL);
		panel2.add(attachTF);
		panel2.add(findB);
		
		Panel panel3 = new Panel();
		panel3.setLayout(new GridLayout());
		panel3.add(titleL);
		panel3.add(titleTF);
		
		Panel panel4 = new Panel();
		panel4.setLayout(new GridLayout());
		panel4.add(messageTA);
		
		Panel panel5 = new Panel();
		panel5.setLayout(new FlowLayout());
		panel5.add(sendB);
		panel5.add(cancelB);
		
		add(panel1,0,0,1,1,0,0); // 받는사람
		add(panel2,0,1,1,1,0,0); // 첨부파일
		add(panel3,0,2,1,1,0,0);
		add(panel4,0,3,1,1,0,0);
		add(panel5,0,6,1,1,0,0); // 보내기 취소
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
//		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		// margin
		// 격자 하나짜리

		gridBagLayout.setConstraints(component, gridBagConstraints);

		add(component);
	}
	
	public static void main(String[] args) {
		GridFrame gridFrame = new GridFrame();
		gridFrame.setContents();
		gridFrame.setSize(400,500);
		gridFrame.setVisible(true);
	}

}
