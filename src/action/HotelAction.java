package action;


import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import constant.City;
import constant.Config;
import model.Hotel;
import model.HotelDraft;
import model.Order;
import model.RoomType;
import model.User;
import service.HotelService;
import service.OrderService;
import service.RoomService;
import service.UserService;

public class HotelAction extends ActionSupport{
	private static final long serialVersionUID = -5785352294133817532L;
	private HotelService hotelService;
	private OrderService orderService;
	private RoomService roomService;
	private Date inDate;
	private Date outDate;
	private String expectedCity;
	private Hotel hotel;
	private RoomType room1;//用于注册
	private RoomType room2;//用于注册
	private RoomType room3;//用于注册
	
	private int hid;//用于显示酒店详细
	

	
	public String modifyHotel(){
		Map session = ActionContext.getContext().getSession();
		Hotel myHotel = (Hotel)session.get("myHotel");
		if (myHotel == null) {
			User user = (User)session.get("user");
			myHotel = hotelService.getHotelByUid(user.getUid());
			session.put("myHotel", hotel);
		}
		return SUCCESS;
	}
	
	public String personalHome(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		List<Order> orders = orderService.getOrder(user.getUid());
		int consumeSum = 0;
		for(Order order : orders){
			if (order.getIs_vip() == 1) {
				consumeSum += order.getPrice();
			}else {
				consumeSum += order.getVip_price();
			}
		}
		Hotel myHotel = hotelService.getHotelByUid(user.getUid());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orders", orders);
		request.put("consumeSum", consumeSum);
		session.put("myHotel",myHotel);
		return SUCCESS;
	}

	private Hotel handleHotel(Hotel hotel){
		Hotel hotel1 = new Hotel();
		hotel1.setCity(hotel.getCity());
		hotel1.setDescription(hotel.getDescription());
		hotel1.setEmail(hotel.getEmail());
		hotel1.setHotel_name(hotel.getHotel_name());
		hotel1.setId_num(hotel.getId_num());
		hotel1.setName(hotel.getName());
		Date date = Calendar.getInstance().getTime();
		hotel1.setRegister_date(date);
		hotel1.setStar(hotel.getStar());
		return hotel1;
	}
	
	public String updateHotel(){
		Set<RoomType> rooms = new HashSet<>();
		if (room1 != null) {
			rooms.add(room1);
		}
		if(room2 != null){
			rooms.add(room2);
		}
		if(room3 != null){
			rooms.add(room3);
		}
		hotel.setRoomTypes(rooms);
		Hotel h = new Hotel(hotel);
		Map session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		h.setUser(user);
		boolean result = hotelService.updateHotelDraft(h);
		if (result == true) {
			return SUCCESS;
		}
		return ERROR;
	}

	public String registerHotel(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Hotel hotel1 = handleHotel(hotel);
		hotel1.setUser(user);
		Set<RoomType> rooms = new HashSet<>();
		room1.setType(1);
		room2.setType(2);
		room3.setType(3);
		room1.setHotel(hotel1);
		room2.setHotel(hotel1);
		room3.setHotel(hotel1);
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		hotel1.setRoomTypes(rooms);
		boolean result = hotelService.registerHotel(hotel1);
		System.out.println("register hotel :" + result);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	


	public String approveNewHotel(){
		Map session = ActionContext.getContext().getSession();
		List<Hotel> hotels = (List<Hotel>) session.get("newHotels");
		Hotel newHotel = null;
		System.out.println("hotel hid" + this.hotel.getHid());
		for(Hotel hotel : hotels){
			if (hotel.getHid() == this.hotel.getHid()) {
				newHotel = hotel;
				break;
			}
		}
		if (newHotel != null) {
			boolean result = hotelService.approveNewHotel(newHotel);
			if (result == true) {
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	public String manageNewHotel(){
		List<Hotel> newHotels = hotelService.getNewHotels();
		Map session = (Map) ActionContext.getContext().getSession();
		session.put("newHotels", newHotels);
		return SUCCESS;
	}
	
	public String manageModifyHotel(){
		List<HotelDraft> hotelDrafts = hotelService.getModifyHotels();
		Map requst = (Map) ActionContext.getContext().get("request");
		requst.put("hotelDrafts", hotelDrafts);
		return SUCCESS;
	}
	
	public String hotelStatistic(){
		//TODO 统计数据
		return SUCCESS;
	}

	public String chooseHotel(){
		Date in,out;
		Date today = Calendar.getInstance().getTime();
		if (inDate != null && outDate != null) {
			in = inDate;
			out = outDate;
			if (in.after(out)) {
				in = today;
				out = today;
			}
		}else {
			in = today;
			out = today;
		}
		if (expectedCity == null) {
			expectedCity = Config.DEFAULT_CITY;
		}
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("expectedCity", expectedCity);
		session.put("inDate", in);
		session.put("outDate",out);
		Map<String,Object> request = (Map) ActionContext.getContext().get("request");
		request.put("cities", City.values());
		List<Hotel> hotels = hotelService.getHotels(expectedCity);
		if (hotels != null) {
			request.put("hotels", hotels);
			return SUCCESS;
		}else {
			return ERROR;
		}
	}


	public Hotel getMyHotel(int uid){
		return hotelService.getHotelByUid(uid);
	}



	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	public String getExpectedCity() {
		return expectedCity;
	}

	public void setExpectedCity(String expectedCity) {
		this.expectedCity = expectedCity;
	}
	
	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public OrderService getOrderService(){
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public RoomType getRoom1() {
		return room1;
	}

	public void setRoom1(RoomType room1) {
		this.room1 = room1;
	}

	public RoomType getRoom2() {
		return room2;
	}

	public void setRoom2(RoomType room2) {
		this.room2 = room2;
	}

	public RoomType getRoom3() {
		return room3;
	}

	public void setRoom3(RoomType room3) {
		this.room3 = room3;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	
}
