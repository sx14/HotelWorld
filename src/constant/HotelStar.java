package constant;
public enum HotelStar {
	
	N(0,"不限",0),
	A(1,"A级",500),B(2,"B级",300),C(3,"C级",100);
	
	private int value;
	private String name;
	private int lowestPrice;
	private String lowestPriceDesc;
	private HotelStar(int value,String name,int lowestPrice){
		this.name = name;
		this.value = value;
		this.lowestPrice = lowestPrice;
		if (lowestPrice != 0) {
			lowestPriceDesc = "("+lowestPrice+"￥"+"起)";
		}else {
			lowestPriceDesc = "";
		}
	}
	
	public int getLowestPrice(){return lowestPrice;}
	public int getValue(){return value;}
	public String getName(){return name;}
	public String getLowestPriceDesc(){return lowestPriceDesc;}
}
