package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Hotel;
import model.Order;
import model.Room;
import model.RoomType;
import model.User;
import service.RoomService;

public class RoomAction extends ActionSupport{
	private static final long serialVersionUID = -6366655009809047154L;
	private RoomService roomService;
	private Date inDate;
	private Date outDate;


	private int tid;
	private int hid;
	private int num;//房间个数
	
	public String reserveRoom(){
		Map session = ActionContext.getContext().getSession();
		Hotel hotel = (Hotel) session.get("hotel");		
		if (hotel == null) {
			return ERROR;
		}else {
			inDate = (Date) session.get("inDate");
			outDate = (Date) session.get("outDate");
			if (num == 0) {
				num = 1;
			}
			RoomType roomType = null;
			for(RoomType type : hotel.getRoomTypes()){
				if (type.getTid() == tid) {
					roomType = type;
					break;
				}
			}
			List<Room> rooms = roomType.getEmptyRooms(num);
 			ArrayList<Order> orders = new ArrayList<>();
			User user = (User) session.get("user");
			for(int i = 0 ; i < rooms.size() ; i++){
				orders.add(createOrder(user, i, inDate, outDate,rooms.get(i)));
			}
			session.put("orders", orders);
		}
		return SUCCESS;
	}
	
	private Order createOrder(User user,int rid,Date inDate ,Date outDate,Room room){
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
			}
		}
		User user = (User)session.get("user");
		session.replace("inDate", inDate);
		session.replace("outDate", outDate);
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
	
	
}
