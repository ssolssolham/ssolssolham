
public class Memo {

	public static void main(String[] args) {
		
		MemoUIFrame memo = new MemoUIFrame();
		memo.setContents();
		memo.setSize(800,600);
		memo.setCenter();
		memo.eventRegist();
		memo.setVisible(true);
	}
	
	
}
