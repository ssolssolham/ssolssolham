package kr.or.kosta.chat.common;

public interface Protocol {
	
	// 일의자리수 1이면 성공, 2면 실패
	public static final String DELEMETER = ",";
	
	public static final int CONNECT = 1000;
	public static final int CONNECT_RESULT = 1001; 
	
	public static final int MULTI_CHAT = 2000;
	public static final int DISCONNECT = 3000;
	public static final int NEW_LIST = 4000;
	
	public static final int ROOM_CREATE = 5010;
	public static final int ROOM_CREATE_SUCCESS = 5011;
	public static final int ROOM_ENTER = 5000;
	public static final int ROOM_CHANGER = 5020;
	public static final int ROOM_SEARCH = 5030;

	// 초대 필요

}
