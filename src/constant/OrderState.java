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
}
