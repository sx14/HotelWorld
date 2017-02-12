package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="room",schema="hotel_world")
public class Room {
	private int rid;
	private int capacity;
	private int price;
	private int vip_price;
	private String image;
	private Hotel hotel;
	private int type;
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="hid")
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
	
	@Transient
	public String getRoomType(){
		String type = "人间";
		switch (capacity) {
		case 1:
			type = "单"+type;
			break;
		case 2:
			type = "双"+type;
			break;
		default:
			type = capacity + type;
			break;
		}
		return type;
	}
	
	@Transient
	public String getRoomName(){
		return String.format("%04d", rid);
	}
	
}
