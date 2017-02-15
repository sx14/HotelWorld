package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.result.ResultSetOutput;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.regexp.internal.recompile;

import constant.OrderOperate;
import constant.OrderState;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Customer;
import model.Hotel;
import model.Order;
import model.Room;
import model.RoomType;
import model.User;
import service.HotelService;
import service.RoomService;

public class RoomAction extends ActionSupport{
	private static final long serialVersionUID = -6366655009809047154L;
	private RoomService roomService;
	private Date inDate;
	private Date outDate;
	private Order order;
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	private String operate;
	private int rid;
	
	private int hid;

	public String chooseRoom(){
		Hotel hotel = roomService.getRoomByHid(hid, inDate, outDate);
		if (hotel != null) {
			Map session = ActionContext.getContext().getSession();
			session.put("hotel", hotel);
			return SUCCESS;
		}
		return ERROR;
	}

	public String handleOrder(){
		boolean result = false;
		Map session = ActionContext.getContext().getSession();
		Hotel hotel = (Hotel) session.get("hotel");
		order.setHotel(hotel);
		if (operate.equals(OrderOperate.IN.getValue())) {
			Set<Customer> customers = new HashSet<>();
			customers.add(customer1);
			customers.add(customer2);
			customers.add(customer3);
			customer1.setOrder(order);
			customer2.setOrder(order);
			customer3.setOrder(order);
			order.setCustomers(customers);
			order.setRoom(hotel.getRoom(rid));
			result = roomService.checkIn(order);
		}else if (operate.equals(OrderOperate.OUT.getValue())) {
			result = roomService.checkOut(order.getOid());
		}else if (operate.equals(OrderOperate.CANCEL.getValue())) {
			result = roomService.cancelReserve(order.getOid());
		}else {
			result = false;
		}
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}

	
	public Customer getCustomer1() {
		return customer1;
	}

	public void setCustomer1(Customer customer1) {
		this.customer1 = customer1;
	}

	public Customer getCustomer2() {
		return customer2;
	}

	public void setCustomer2(Customer customer2) {
		this.customer2 = customer2;
	}

	public Customer getCustomer3() {
		return customer3;
	}

	public void setCustomer3(Customer customer3) {
		this.customer3 = customer3;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String manageRoom(){
		//默认显示当天的房间信息
		if (inDate == null) {
			inDate = Calendar.getInstance().getTime();
		}
		if (outDate == null){			
			outDate = Calendar.getInstance().getTime();
			outDate.setTime(outDate.getTime()+24*60*60*1000);
		}
		Map session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		Hotel hotel = roomService.getRoomByOwner(user.getUid(),inDate,outDate);
		session.put("hotel", hotel);
		Map request = (Map) ActionContext.getContext().get("request");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		request.put("inDate", format.format(inDate));
		request.put("outDate", format.format(outDate));
		return SUCCESS;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}
	
	
}
