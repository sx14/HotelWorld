package vo;

import java.io.File;

public class RoomVO {
	private int num;
	private int capacity;
	private String image;
	private int type;
	private int price;
	private int vip_price;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getVip_price() {
		return vip_price;
	}
	public void setVip_price(int vip_price) {
		this.vip_price = vip_price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
}
