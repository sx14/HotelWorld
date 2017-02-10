package constant;

public enum City {
	Beijing("北京"),Nanjing("南京"),Shanghai("上海"),Guangzhou("广州"),Shenzhen("深圳");
	private String value;
	private City(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
