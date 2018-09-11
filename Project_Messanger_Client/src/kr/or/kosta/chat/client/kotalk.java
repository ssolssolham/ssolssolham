package kr.or.kosta.chat.client;

public class kotalk {

	public static void main(String[] args) {
		
		ChatUI frame = new ChatUI("::: kotalk :::");
		frame.setContents(); // setContentsView와 비슷
//		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(300, 100);
		frame.setCenter();
		frame.eventRegist(1);
		frame.setVisible(true);
		
		ChatClient chatClient = new ChatClient(frame);	
		frame.setChatClient(chatClient);
		
	}
}
