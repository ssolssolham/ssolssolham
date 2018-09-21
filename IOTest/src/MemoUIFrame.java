import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MemoUIFrame extends Frame {
	TextArea memoTextArea;
	MenuBar menuBar;
	Menu menu;
	MenuItem newMI, saveMI, openMI, exitMI;

	public MemoUIFrame() {
		memoTextArea = new TextArea();

		menuBar = new MenuBar();

		menu = new Menu("New");

		newMI = new MenuItem("New");
		saveMI = new MenuItem("Save");
		openMI = new MenuItem("Open");
		exitMI = new MenuItem("Exit");
	}

	public void eventRegist() {
		newMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		saveMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileSave();
			}
		});

		openMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileOpen();
			}
		});
		exitMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
	}
	public void setContents() {
		add(memoTextArea);

		setMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(newMI);
		menu.add(openMI);
		menu.add(saveMI);
		menu.addSeparator();
		menu.add(exitMI);
	}

	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x,y);
	}
	
	private void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	public void fileOpen() {
		BufferedReader in = null;
		FileDialog fileDialog = new FileDialog(this, "열기", FileDialog.LOAD);
		fileDialog.show();
		
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		
		if(fileDialog.getFile() == null) return;
		
		File selectedFile = new File(path);
		memoTextArea.setText("");
		
		String txt;
		try {
			txt = FileDao.openFile(selectedFile.getAbsolutePath());
			memoTextArea.append(txt);
			setTitle(selectedFile.getName());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void fileSave() {
		FileDialog fileDialog = new FileDialog(this, "저장", FileDialog.SAVE);
		fileDialog.show();
		File saveFile = new File(fileDialog.getDirectory() + fileDialog.getFile());
		String fileText = memoTextArea.getText();
		try {
			FileDao.saveFile(saveFile.getAbsolutePath(), fileText);
			setTitle(saveFile.getName());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "경고", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
