package action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import constant.ApplyState;
import constant.City;
import constant.Config;
import constant.HotelStar;
import constant.OrderState;
import model.Hotel;
import model.HotelDraft;
import model.Order;
import model.RoomType;
import model.User;
import service.HotelService;
import service.OrderService;
import service.RoomService;

public class HotelAction extends ActionSupport{
	private static final long serialVersionUID = -5785352294133817532L;
	private HotelService hotelService;
	private OrderService orderService;
	private RoomService roomService;
	private Date inDate;
	private Date outDate;
	private String expectedCity;
	private Hotel hotel;
	private RoomType room1;//用于注册，修改
	private RoomType room2;//用于注册，修改
	private RoomType room3;//用于注册，修改
	
	private int level;//酒店星级，用于搜索酒店
	
	private int hid;//用于显示酒店详细
	
	private int hdid;//用于审批酒店修改
	private int state;//用于审批酒店修改，结果

	/**
	 * 加载修改酒店信息界面
	 * @return
	 */
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
	
	/**
	 * 加载个人主页界面
	 * @return
	 */
	public String personalHome(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		List<Order> orders = orderService.getOrderByUid(user.getUid());
		int consumeSum = 0;
		for(Order order : orders){
			if (order.getState() == OrderState.OUT.getValue()) {
				if (order.getIs_vip() == 1) {
					consumeSum += order.getPrice();
				}else {
					consumeSum += order.getVip_price();
				}
			}
		}
		Hotel myHotel = hotelService.getHotelByUid(user.getUid());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orders", orders);
		request.put("consumeSum", consumeSum);
		session.put("myHotel",myHotel);
		return SUCCESS;
	}

	/**
	 * 提交酒店信息修改草稿，等待总经理审批
	 * @return
	 */
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
//		Hotel h = new Hotel(hotel);
		Map session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		hotel.setUser(user);
		boolean result = hotelService.saveHotelDraft(hotel);
		if (result == true) {
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * 用户提交开店申请
	 * @return
	 */
	public String registerHotel(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Hotel hotel1 = new Hotel(hotel);
		hotel1.setRegister_date(Calendar.getInstance().getTime());
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
	
	/**
	 * 处理总经理审批的酒店信息修改申请
	 * @return
	 */
	public String handleModifyHotel(){
		HotelDraft hotelDraft = null;
		Map session = ActionContext.getContext().getSession();
		List<HotelDraft> hotelDrafts = (List<HotelDraft>) session.get("hotelDrafts");
		for(HotelDraft hDraft : hotelDrafts){
			if (hDraft.getHdid() == hdid) {
				hotelDraft = hDraft;
				break;
			}
		}
		if (state == 1) {
			hotelDraft.setState(1);
		}
		boolean result = hotelService.updateHotel(hotelDraft);
		session.remove("hotelDrafts");
		if ( result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}


	/**
	 * 处理总经理审批开店申请的结果
	 * @return
	 */
	public String handleNewHotel(){
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
			session.remove("newHotels");
			if (result == true) {
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	/**
	 * 加载总经理查看新开酒店界面
	 * @return
	 */
	public String manageNewHotel(){
		List<Hotel> newHotels = hotelService.getNewHotels();
		Map session = (Map) ActionContext.getContext().getSession();
		session.put("newHotels", newHotels);
		return SUCCESS;
	}
	
	/**
	 * 加载总经理查看酒店申请界面
	 * @return
	 */
	public String manageModifyHotel(){
		List<HotelDraft> hotelDrafts = hotelService.getModifyHotels();
		Map session = (Map) ActionContext.getContext().getSession();
		session.put("hotelDrafts", hotelDrafts);
		return SUCCESS;
	}
	
	/**
	 * 酒店经营数据统计界面
	 * @return
	 */
	public String hotelStatistic(){
		//TODO 统计数据
		return SUCCESS;
	}

	/**
	 * 用户选择酒店后，显示酒店房间信息
	 * @return
	 */
	public String chooseHotel(){
		Date in,out;
		if (inDate != null && outDate != null) {
			in = inDate;
			out = outDate;
		}else {
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date tomorrow = calendar.getTime();
			in = today;
			out = tomorrow;
		}
		if (expectedCity == null) {
			expectedCity = Config.DEFAULT_CITY;
		}
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("expectedCity", expectedCity);
		session.put("inDate", in);
		session.put("outDate",out);
		session.put("expectedLevel", level);
		Map<String,Object> request = (Map) ActionContext.getContext().get("request");
		request.put("cities", City.values());
		request.put("levels", HotelStar.values());
		List<Hotel> hotels = hotelService.getHotels(expectedCity,level);
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHdid() {
		return hdid;
	}

	public void setHdid(int hdid) {
		this.hdid = hdid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
