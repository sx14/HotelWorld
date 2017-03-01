package constant;

public enum OrderState {
	RESERVE(0,"已预订"),IN(1,"已入住"),OUT(2,"已退房"),JUDGE(3,"已评价"),CANCEL(-1,"已取消");
	
	private String state;
	private int value;
	private OrderState(int value,String state){
		this.state = state;
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static String getRoomStateInChinese(int value){
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
	
	public String getStateName(){
		return state;
	}
}
