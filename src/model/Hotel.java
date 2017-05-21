package model;
import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import constant.ApplyState;
import constant.Config;
import constant.HotelStar;
import constant.OrderState;

@Entity
@Table(name="hotel",schema="hotel_world")
public class Hotel implements Serializable{
	private static final long serialVersionUID = -5414687446666094220L;
	private int hid;
	private String city;
	private int start_money;
	private Date register_date;
	private Set<RoomType> roomTypes;
	private Set<Order> orders;
	private User user;
	private int star;
	private String image_small;	//酒店图片路径
	private String image_mid;	//酒店图片路径
	private String image_big;	//酒店图片路径
	private String name;
	private String description;
	private String hotel_name;
	private String id_num;
	private String email;
	private int state;
	private String position;
	private int money;
	
	private File imgS;
	private String imgSFileName;
	private String imgSContentType;
	private File imgM;
	private String imgMFileName;
	private String imgMContentType;
	private File imgB;
	private String imgBFileName;
	private String imgBContentType;
	
	
	public void modifyHotel(HotelDraft hotelDraft){
		if (hotelDraft.getState() == 1) {
			this.state = ApplyState.OPEN.getValue();
		}
		this.description = hotelDraft.getDescription();
		this.email = hotelDraft.getEmail();
		this.image_big = hotelDraft.getImage_big();
		this.image_mid = hotelDraft.getImage_mid();
		this.image_small = hotelDraft.getImage_small();
		Set<RoomType> types = new HashSet<>();
		for(RoomDraft roomDraft : hotelDraft.getRoomDrafts()){
			Iterator<RoomType> iterator = roomTypes.iterator();
			while (iterator.hasNext()) {
				RoomType roomType = (RoomType) iterator.next();
				if(roomType.getTid()== roomDraft.getTid()){
					RoomType roomType2 = new RoomType(roomDraft, roomType);
					Set<Room> rooms = roomType2.getRooms();
					if (rooms==null || rooms.size() == 0) {
						for(int i = 0 ; i < roomType2.getNum() ; i++){
							Room room = new Room();
							room.setNum(i+1);
							room.setRoomType(roomType2);
							rooms.add(room);
						}
						roomType2.setRooms(rooms);
					}
					types.add(roomType2);
					break;
				}
			}
		}
		this.roomTypes.clear();
		this.roomTypes.addAll(types);
	}
	
	@Transient
	public String getStateString(){
		switch (state) {
		case 0:
			return "待审批";
		case 1:
			return "待开业";
		case 2:
			return "已开业";
		default:
			return "未通过";
		}
	}
	
	@Transient
	public Room getRoom(int rid){
		Room room = null;
		for(RoomType roomType : roomTypes){
			for(Room r : roomType.getRooms()){
				if (r.getRid() == rid) {
					room = r;
					break;
				}
			}
		}
		return room;
	}
	
	@Transient
	public String getGoodComment(){
		for(Order order : orders){
			if (order.getStar() == Config.MAX_STAR) {
				return order.getComment_content();
			}
		}
		return Config.DEFAULT_COMMENT;
	}
	
	@Transient
	public int getEmptyRoomNum(int roomType){
		int sum = 0;
		for(RoomType roomT : roomTypes){
			if (roomT.getType() == roomType) {
				for(Room room : roomT.getRooms()){
					if (room.getOrders().size() == 0) {
						sum ++;
					}
				}
				break;
			}
		}
		return sum;
	}
	
	@Transient
	public int getAllEmptyRoomNum(){
		int sum = 0;
		for(RoomType roomType : roomTypes){
			for(Room room : roomType.getRooms()){
				if (room.getOrders().size() == 0) {
					sum ++;
				}
			}
		}
		return sum;
	}
	

	@Transient
	public int getConsumeSum(){
		int sum = 0;
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DAY_OF_MONTH, 0-day);
		Date monthStart = calendar.getTime();
		for(Order order : orders){
			if (order.getIn_date().before(monthStart)) {
				continue;
			}else{
				if (order.getState()== OrderState.OUT.getValue()) {
					if (order.getIs_vip() == 1) {
						sum += order.getVip_price();
					}else{
						sum += order.getPrice();
					}
				}
			}
		}
		return sum;
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
		for(Order order : orders){
			if (order.getState() == OrderState.JUDGE.getValue() &&(order.getStar() >= 4)) {
				sum ++;
			}
		}
		return sum;
	}
	
	@Transient
	public String getID(){
		return String.format("%07d", hid);
	}
	
	@Transient
	public int getCommentNum(){
		int sum = 0;
		for(Order order : orders){
			if (order.getState() == OrderState.JUDGE.getValue()) {
				sum ++;
			}
		}
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
		for(RoomType room : roomTypes){
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
			int count = 0;
			for(Order order : orders){
				if (order.getComment_content() != null) {
					sum += order.getStar();
					count ++;
				}
			}
			if (count != 0) {
				double avgStar = sum/count;
				return (avgStar);
			}else {
				return 0;
			}
			
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
	public Set<RoomType> getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(Set<RoomType> rooms) {
		this.roomTypes = rooms;
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

	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	public Hotel(){
		this.state = ApplyState.WAIT.getValue();
		this.description = "";
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
	
	public int getStart_money() {
		return start_money;
	}

	public void setStart_money(int start_money) {
		this.start_money = start_money;
	}

	@Transient
	public int getRoomNumByType(int type){
		int sum = 0;
		for(RoomType room : roomTypes){
			if (room.getType() == type) {
				sum += room.getNum();
			}
		}
		return sum;
	}
	
	@Transient
	public int getRoomCapacityByType(int type){
		int capacity = 0;
		for(RoomType room : roomTypes){
			if (room.getType() == type) {
				capacity = room.getCapacity();
				break;
			}
		}
		return capacity;
	}
	
	@Transient
	public String getLevel(){
		HotelStar[] stars = HotelStar.values();
		for(int i = 0 ; i < stars.length ; i++){
			if (stars[i].getValue() == star) {
				return stars[i].getName();
			}
		}
		return "未知";
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
		this.roomTypes = hotel.getRoomTypes();
		this.star = hotel.getStar();
		this.start_money = hotel.getStart_money();
		this.state = hotel.getState();
		this.user = hotel.getUser();
		this.position = hotel.getPosition();
	}

	@Transient
	public File getImgS() {
		return imgS;
	}

	public void setImgS(File imgS) {
		this.imgS = imgS;
	}

	@Transient
	public String getImgSFileName() {
		return imgSFileName;
	}

	public void setImgSFileName(String imgSFileName) {
		this.imgSFileName = imgSFileName;
	}

	@Transient
	public String getImgSContentType() {
		return imgSContentType;
	}

	public void setImgSContentType(String imgSContentType) {
		this.imgSContentType = imgSContentType;
	}

	@Transient
	public File getImgM() {
		return imgM;
	}

	public void setImgM(File imgM) {
		this.imgM = imgM;
	}

	@Transient
	public String getImgMFileName() {
		return imgMFileName;
	}

	public void setImgMFileName(String imgMFileName) {
		this.imgMFileName = imgMFileName;
	}

	@Transient
	public String getImgMContentType() {
		return imgMContentType;
	}

	public void setImgMContentType(String imgMContentType) {
		this.imgMContentType = imgMContentType;
	}

	@Transient
	public File getImgB() {
		return imgB;
	}

	public void setImgB(File imgB) {
		this.imgB = imgB;
	}

	@Transient
	public String getImgBFileName() {
		return imgBFileName;
	}

	public void setImgBFileName(String imgBFileName) {
		this.imgBFileName = imgBFileName;
	}

	@Transient
	public String getImgBContentType() {
		return imgBContentType;
	}

	public void setImgBContentType(String imgBContentType) {
		this.imgBContentType = imgBContentType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
