package model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import constant.ApplyState;
import constant.OrderState;
import vo.RoomVO;

@Entity
@Table(name="hotel",schema="hotel_world")
public class Hotel implements Serializable{
	private static final long serialVersionUID = -5414687446666094220L;
	private int hid;
	private String city;
	private double start_money;
	private Date register_date;
	private Set<Room> rooms;
	private Set<Order> orders;
	private User user;
	private int star;
	private String image_small;
	private String image_mid;
	private String image_big;
	private String name;
	private String description;
	private String hotel_name;
	private String id_num;
	private String email;
	private int state;
	

	@Transient
	public int getConsumeSum(){
		int sum = 0;
		for(Order order : orders){
			if (order.getState()== OrderState.OUT.getValue()) {
				if (order.getIs_vip() == 1) {
					sum += order.getVip_price();
				}else{
					sum += order.getPrice();
				}
			}
		}
		return sum;
	}
	
	private List<RoomVO> roomVOs;
	
	@Transient
	public List<RoomVO> getRoomVOs() {
		return roomVOs;
	}

	public void setRoomVOs(List<RoomVO> roomVOs) {
		this.roomVOs = roomVOs;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	@Transient
	public int getGoodCommentNum(){
		int sum = 0;
		return sum;
	}
	
	@Transient
	public int getCommentNum(){
		int sum = 0;
		return sum;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_small() {
		return image_small;
	}

	public void setImage_small(String image_small) {
		this.image_small = image_small;
	}

	public String getImage_mid() {
		return image_mid;
	}

	public void setImage_mid(String image_mid) {
		this.image_mid = image_mid;
	}

	public String getImage_big() {
		return image_big;
	}

	public void setImage_big(String image_big) {
		this.image_big = image_big;
	}

	@Transient
	public int getLowestPrice(){
		int lowest = Integer.MAX_VALUE;
		for(Room room : rooms){
			if (room.getPrice() < lowest) {
				lowest = room.getPrice();
			}
		}
		return lowest;
	}
	
	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	@Transient
	public double getAvgStar(){
		if (orders != null && orders.size() != 0) {
			double sum = 0;
			for(Order order : orders){
				sum += order.getStar();
			}
			double avgStar = sum/orders.size();
			return avgStar;
		}else {
			return 0;
		}
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//立即加载
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="hotel",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Order> getOrders(){
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@OneToMany(mappedBy="hotel", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getStart_money() {
		return start_money;
	}
	public void setStart_money(double start_money) {
		this.start_money = start_money;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	public Hotel(){
		this.state = ApplyState.WAIT.getValue();
	}
	
	@Transient
	public String getStarString(){
		switch (star) {
		case 1:
			return "A级";
		case 2:
			return "B级";
		default:
			return "C级";
		}
	}
	
	@Transient
	public int getRoomNumByType(int type){
		int sum = 0;
		for(Room room : rooms){
			if (room.getType() == type) {
				sum ++;
			}
		}
		return sum;
	}
	
	@Transient
	public int getRoomCapacityByType(int type){
		int capacity = 0;
		for(Room room : rooms){
			if (room.getType() == type) {
				capacity = room.getCapacity();
				break;
			}
		}
		return capacity;
	}
	

	public Hotel(Hotel hotel){
		this.city = hotel.getCity();
		this.description = hotel.getDescription();
		this.email = hotel.getEmail();
		this.hid = hotel.getHid();
		this.hotel_name = hotel.getHotel_name();
		this.id_num = hotel.getId_num();
		this.image_big = hotel.getImage_big();
		this.image_mid = hotel.getImage_mid();
		this.image_small = hotel.getImage_small();
		this.name = hotel.getName();
		this.orders = hotel.getOrders();
		this.register_date = hotel.getRegister_date();
		this.rooms = hotel.getRooms();
		this.roomVOs = hotel.getRoomVOs();
		this.star = hotel.getStar();
		this.start_money = hotel.getStart_money();
		this.state = hotel.getState();
		this.user = hotel.getUser();
	}
	
}
