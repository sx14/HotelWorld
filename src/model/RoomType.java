package model;

import java.io.File;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="room_type",schema="hotel_world")
public class RoomType {
	private int tid;
	private int capacity;
	private int price;
	private int vip_price;
	private String image;
	private Hotel hotel;
	private int type;
	private int num;
	private Set<Room> rooms;
	
	
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	
	@Transient
	public int getEmptyRoomNum(){
		int sum = 0;
		for(Room room : rooms){
			if (room.getOrders().size() == 0) {
				sum ++;
			}
		}
		return sum;
	}
	
	@OneToMany(mappedBy="roomType",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="tid")
	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
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
}