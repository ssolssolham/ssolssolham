package kr.or.kosta.chat.client;

public class kotalk {

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		
		ChatFrame frame = new ChatFrame("::: kotalk :::");
		frame.setContents(); // setContentsView와 비슷
		frame.setSize(400, 500);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
		
		frame.setChatClient(chatClient);
		
	}
}
