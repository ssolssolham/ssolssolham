package kr.or.kosta.chat.addition;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import kr.or.kosta.chat.client.ChatUI;

public class ImageButton extends JButton {

	public ImageButton(int width,int height, String imageIcon, Color backgroundColor,ChatUI component){
		
		setIcon(new ImageIcon(imageIcon));
		setBackground(backgroundColor);
		setBorderPainted(false); 
		setFocusPainted(false);
		setContentAreaFilled(false);
		
	}
}
