package constant;

public enum OrderState {
	RESERVE(0),IN(1),OUT(2),CANCEL(-1);
	
	private int value;
	private OrderState(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static String getOrderStateInChinese(int value){
		String state;
		switch (value) {
		case 0:
			state = "已预订";
			break;
		case 1:
			state = "已入住";
			break;
		default:
			state = "空闲";
		}
		return state;
	}
}
