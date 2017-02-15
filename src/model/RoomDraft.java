package model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="room_draft",schema="hotel_world")
public class RoomDraft {
	private int rdid;
	private int tid;
	private int capacity;
	private int price;
	private int vip_price;
	private String image;
	private HotelDraft hotelDraft;
	private int type;
	private int num;
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRdid() {
		return rdid;
	}

	public void setRdid(int rdid) {
		this.rdid = rdid;
	}

	@Transient
	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	@Transient
	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	@Transient
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="hdid")
	public HotelDraft getHotelDraft() {
		return hotelDraft;
	}

	public void setHotelDraft(HotelDraft hotelDraft) {
		this.hotelDraft = hotelDraft;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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

	
	public RoomDraft(){}
	
	public RoomDraft(RoomType room){
		//this.hotel没设置
		this.capacity = room.getCapacity();
		this.image = room.getImage();
		this.img = room.getImg();
		this.imgContentType = room.getImgContentType();
		this.imgFileName = room.getImgFileName();
		this.num = room.getNum();
		this.price = room.getPrice();
		this.tid = room.getTid();
		this.type = room.getType();
		this.vip_price = room.getVip_price();
	}
	
}
