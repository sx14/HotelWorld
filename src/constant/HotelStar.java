package constant;
public enum HotelStar {
	
	A(1,"A级"),B(2,"B级"),C(3,"C级");
	
	private int value;
	private String name;
	private HotelStar(int value,String name){
		this.name = name;
		this.value = value;
	}
}
