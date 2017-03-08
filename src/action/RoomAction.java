package action;

import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Hotel;
import model.Order;
import model.Room;
import model.RoomType;
import model.User;
import service.RoomService;
import sun.launcher.resources.launcher;

public class RoomAction extends ActionSupport{
	private static final long serialVersionUID = -6366655009809047154L;
	private RoomService roomService;
	private Date inDate;
	private Date outDate;


	private int tid;
	private int hid;
	private String phone;
	
	public String reserveRoom(){
		Map session = ActionContext.getContext().getSession();
		Hotel hotel = (Hotel) session.get("hotel");		
		if (hotel == null) {
			return ERROR;
		}else {
			if(inDate == null || outDate == null){
				inDate = (Date) session.get("inDate");
				outDate = (Date) session.get("outDate");
			}
			RoomType roomType = null;
			for(RoomType type : hotel.getRoomTypes()){
				if (type.getTid() == tid) {
					roomType = type;
					break;
				}
			}
			List<Room> rooms = roomType.getEmptyRooms(1);
			User user = (User) session.get("user");
			Order order = createOrder(user, inDate, outDate,rooms.get(0));
			session.put("order", order);
			session.put("customerNum", roomType.getCapacity());
		}
		return SUCCESS;
	}
	
	private Order createOrder(User user,Date inDate ,Date outDate,Room room){
		Order order = new Order();
		Date today = Calendar.getInstance().getTime();
		RoomType roomType = room.getRoomType();
		order.setCreate_date(today);
		order.setHotel(roomType.getHotel());
		order.setIn_date(inDate);
		if (user.isVIP()) {
			order.setIs_vip(1);
		}else {
			order.setIs_vip(0);
		}
		order.setOut_date(outDate);
		order.setPrice(roomType.getPrice());
		order.setVip_price(roomType.getVip_price());
		order.setRoom(room);
		order.setUser(user);
		return order;
	}

	public String searchRoom(){
		Map session = ActionContext.getContext().getSession();
		Hotel hotel = (Hotel) session.get("hotel");
		Hotel h = roomService.getReservedRooms(hotel,phone);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("hotel",h);
		request.put("phone",phone);
		return SUCCESS;
	}
	
	public String manageRoom(){
		//默认显示当天的房间信息
		Map session = ActionContext.getContext().getSession();
		if (inDate == null || outDate == null) {
			inDate = (Date)session.get("inDate");
			outDate = (Date)session.get("outDate");
			if (inDate == null || outDate == null ) {
				Calendar calendar = Calendar.getInstance();
				inDate = calendar.getTime();
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				outDate = calendar.getTime();
				session.put("inDate", inDate);
				session.put("outDate", outDate);
			}
		}else {
			session.put("inDate", inDate);
			session.put("outDate", outDate);
		}
		User user = (User)session.get("user");
		Hotel hotel = roomService.getRoomByOwner(user.getUid(),inDate,outDate);
		session.put("hotel", hotel);
		return SUCCESS;
	}

	public String chooseRoom(){
		Map session = ActionContext.getContext().getSession();
		if (inDate == null || outDate == null) {
			inDate = (Date) session.get("inDate");
			outDate = (Date)session.get("outDate");
		}
		Hotel hotel = roomService.getRoomByHid(hid, inDate, outDate);
		if (hotel != null) {
			session.put("inDate", inDate);
			session.put("outDate", outDate);
			session.put("hotel", hotel);
			return SUCCESS;
		}
		return ERROR;
	}


	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
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

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
