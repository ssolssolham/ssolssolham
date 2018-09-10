import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;

public class GridBagLayoutPanel extends Panel {

	Button button1, button2, button3;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	public GridBagLayoutPanel() {
		button1 = new Button("Button1");
		button2 = new Button("Button2");
		button3 = new Button("Button3");

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

	}

	public void setContents() {
		setLayout(gridBagLayout);
		/*
		 * gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
		 * gridBagConstraints.gridwidth = 1; gridBagConstraints.gridheight = 1;
		 * gridBagConstraints.weightx = 0; gridBagConstraints.weighty = 0; // 격자 하나짜리 //
		 * gridBagConstraints의 weightx / 총 weightx gridBagLayout.setConstraints(button1,
		 * gridBagConstraints);
		 * 
		 * add(button1);
		 * 
		 * gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
		 * gridBagConstraints.gridwidth = 1; gridBagConstraints.gridheight = 1;
		 * gridBagConstraints.weightx = 1; gridBagConstraints.weighty = 1;
		 * gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		 * gridBagConstraints.insets = new Insets(5, 5, 5, 5); // margin // 격자 하나짜리
		 * 
		 * gridBagLayout.setConstraints(button2, gridBagConstraints);
		 * 
		 * add(button2);
		 */

		add(button1, 0, 0, 1, 1, 0, 0);
		add(button2, 1, 0, 1, 1, 1, 0);
		add(button3, 0, 1, 2, 1, 1, 0);

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
		Frame frame = new Frame("GridLayout Example");

		GridBagLayoutPanel panel = new GridBagLayoutPanel();
		panel.setContents();

		frame.add(panel);
//		frame.setSize(400, 400);
		frame.pack();
		frame.setVisible(true);
	}

}
