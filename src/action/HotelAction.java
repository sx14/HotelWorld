package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import constant.City;
import constant.Config;
import model.Hotel;
import model.HotelDraft;
import model.User;
import service.HotelService;
import vo.RoomVO;

public class HotelAction extends ActionSupport{
	private static final long serialVersionUID = -5785352294133817532L;
	private HotelService hotelService;
	private String inDate;
	private String outDate;
	private String expectedCity;
	private Hotel hotel;
	private RoomVO room1;
	private RoomVO room2;
	private RoomVO room3;
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	public String registerHotel(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Hotel hotel1 = handleHotel(hotel);
		hotel1.setUser(user);
		List<RoomVO> roomVOs = new ArrayList<>();
		room1.setType(1);
		room2.setType(2);
		room3.setType(3);
		roomVOs.add(room1);
		roomVOs.add(room2);
		roomVOs.add(room3);
		boolean result = hotelService.registerHotel(hotel1, roomVOs);
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
		System.out.println("new Hotel:" + newHotel);
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
		//统计数据
		return SUCCESS;
	}

	public String chooseHotel(){
		Date in,out;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();
		if (inDate != null && outDate != null) {
			try {
				in = format.parse(inDate);
				out = format.parse(outDate);
			} catch (ParseException e) {
				in = today;
				out = today;
				e.printStackTrace();
			}
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

	public RoomVO getRoom1() {
		return room1;
	}

	public void setRoom1(RoomVO room1) {
		this.room1 = room1;
	}

	public RoomVO getRoom2() {
		return room2;
	}

	public void setRoom2(RoomVO room2) {
		this.room2 = room2;
	}

	public RoomVO getRoom3() {
		return room3;
	}

	public void setRoom3(RoomVO room3) {
		this.room3 = room3;
	}

	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getExpectedCity() {
		return expectedCity;
	}

	public void setExpectedCity(String expectedCity) {
		this.expectedCity = expectedCity;
	}

}
