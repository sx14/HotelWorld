package constant;

public enum OrderOperate {
	IN("入住"),OUT("退房"),CANCEL("取消预定");

	private String value;
	private OrderOperate(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
