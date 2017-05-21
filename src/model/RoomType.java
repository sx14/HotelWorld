package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


import constant.OrderState;


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
	public int getTimes(){
		int sum = 0;
		if (rooms != null) {
			for(Room room : rooms){
				Set<Order> orders = room.getOrders();
				if (orders != null) {
					Calendar calendar = Calendar.getInstance();
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					calendar.add(Calendar.DAY_OF_MONTH, 0-day);
					Date monthStart = calendar.getTime();
					for(Order order : orders){
						if (order.getIn_date().before(monthStart)) {
							continue;
						}else {
							if (order.getState() == OrderState.OUT.getValue()) {
								sum ++;
							}
						}
					}
				}
			}
			
		}
		return sum;
	}
	
	public RoomType(RoomDraft draft,RoomType type) {
		this.capacity = type.getCapacity();
		this.hotel = type.getHotel();
		this.image = draft.getImage();
		this.num = type.getNum();
		this.price = draft.getPrice();
		this.tid = type.getTid();
		this.type = type.getType();
		this.vip_price = draft.getVip_price();
		this.rooms = type.getRooms();
	}
	
	public RoomType() {
	}
	
	@Transient
	public List<Room> getEmptyRooms(int num){
		ArrayList<Room> rooms = new ArrayList<>();
		for(Room room : this.rooms){
			if (room.getOrders().size() == 0) {
				rooms.add(room);
			}
		}
		return rooms;
	}
	
	
	@Transient
	public int getEmptyRoomNum(){
		int sum = 0;
		for(Room room : rooms){
			if (room.getState().equals("空闲")) {
				sum ++;
			}
		}
		return sum;
	}
	
	@OneToMany(mappedBy="roomType",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@OrderBy("num ASC")
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
		case 3:
			type = "三"+type;
			break;
		default:
			type = capacity + type;
			break;
		}
		return type;
	}
}
