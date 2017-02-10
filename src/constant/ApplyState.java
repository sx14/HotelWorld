package constant;

public enum ApplyState {
	PASS(1),WAIT(0),UNPASS(-1);
	private int value;
	private ApplyState(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
