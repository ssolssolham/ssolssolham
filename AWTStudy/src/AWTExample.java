import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class AWTExample {

	public static void main(String[] args) {
		Frame frame = new Frame("처음으로 만든 프레임");
		frame.setSize(1200, 800);
		frame.setVisible(true);

		Button button1 = new Button("AWT 버튼1");
		Button button2 = new Button("AWT 버튼2");

		frame.setLayout(new FlowLayout());
		frame.add(button1);
		frame.add(button2);

		Label label = new Label("AWT Label");
		frame.add(label);

		TextField textField = new TextField("아이디", 10);
		frame.add(textField);
		// frame.setResizable(false);
		// 크기 변경 불가

		TextArea textArea = new TextArea(5, 50);
		frame.add(textArea);

		Checkbox checkbox = new Checkbox("남자", true);
		frame.add(checkbox);

		CheckboxGroup cg = new CheckboxGroup();
		Checkbox cb1 = new Checkbox("Male", true, cg);
		Checkbox cb2 = new Checkbox("Female", false, cg);
		frame.add(cb1);
		frame.add(cb2);
		
		Choice selectBox = new Choice();
		selectBox.add("박찬호");
		selectBox.add("박지성");
		selectBox.add("박찬숙");
		
		frame.add(selectBox);
		
	}

}
