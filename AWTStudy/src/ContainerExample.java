import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window;

public class ContainerExample {

	public static void main(String[] args) {
		Frame owner = new Frame();
		owner.setSize(400, 200);
		owner.setVisible(true);
		// Modal 
		Dialog dialog = new Dialog(owner, "타이틀", false);
		dialog.setSize(200, 200);
		dialog.setVisible(true);
		
		Window window = new Window(owner);
		window.setSize(400, 200);
		window.setVisible(true);
		
//		FileDialog fd = new FileDialog(owner,"파일열기",FileDialog.LOAD);
		FileDialog fd = new FileDialog(owner,"파일열기",FileDialog.SAVE);
		fd.setVisible(true);
	}

}
