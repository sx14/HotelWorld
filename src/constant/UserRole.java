package constant;

public enum UserRole {
	MANAGER(2),USER(0),STAFF(1),Owner(3);
	private int value;
	public int getValue(){
		return value;
	}
	
	private UserRole(int value){
		this.value = value;
	}
}
