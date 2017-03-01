package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import constant.OrderOperate;
import constant.OrderState;
import model.Customer;
import model.Hotel;
import model.Order;
import service.OrderService;
import service.RoomService;

public class OrderAction extends ActionSupport{
	private static final long serialVersionUID = -2757512622255404058L;
	private OrderService orderService;
	private RoomService roomService;
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	private Order order;
	private String operate;
	private int rid;
	
	private int oid;
	private InputStream ajax;
	
	public String cancelOrder(){
		boolean result = orderService.cancelOrder(oid);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String showOrder(){
		Order order = orderService.getOrder(oid);
		Map session = ActionContext.getContext().getSession();
		session.put("order", order);
		return SUCCESS;
	}
	
	public String saveComment(){
		Map session = ActionContext.getContext().getSession();
		Order order = (Order) session.get("order");
		order.setComment_head(this.order.getComment_head());
		order.setComment_content(this.order.getComment_content());
		order.setStar(this.order.getStar());
		order.setState(OrderState.JUDGE.getValue());
		boolean result = orderService.save(order);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String saveOrder(){
		Map session = ActionContext.getContext().getSession();
		Order order = (Order)session.get("order");
		if (customer1 != null) {
			customer1.setOrder(order);
			addCustomer(customer1,order);
			
		}
		if (customer2 != null) {
			customer2.setOrder(order);
			addCustomer(customer2,order);
		}
		if (customer3 != null) {
			customer3.setOrder(order);
			addCustomer(customer3,order);
		}
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		boolean result = orderService.save(orders);
		if (result == true) {
			session.remove("order");
			ajax("success");
			return SUCCESS;
		}else {
			ajax("error");
			return SUCCESS;
		}
		
	}
	
	private Order getOrder(List<Order> orders , int oid){
		for(Order order:orders){
			if (order.getOid() == oid) {
				return order;
			}
		}
		return null;
	}
	
	private void addCustomer(Customer customer,Order order){
		order.addCustomer(customer);
	}
	
	public String handleOrder(){
		boolean result = false;
		Map session = ActionContext.getContext().getSession();
		Hotel hotel = (Hotel) session.get("hotel");
		order.setHotel(hotel);
		if (operate.equals(OrderOperate.IN.getValue())) {
			Set<Customer> customers = new HashSet<>();
			if (customer1 != null) {
				customers.add(customer1);
				customer1.setOrder(order);
			}
			if (customer2 != null) {
				customers.add(customer2);
				customer2.setOrder(order);
			}
			if (customer3 != null) {
				customers.add(customer3);
				customer3.setOrder(order);
			}
			order.setCustomers(customers);
			order.setRoom(hotel.getRoom(rid));
			result = roomService.checkIn(order);
		}else if (operate.equals(OrderOperate.OUT.getValue())) {
			result = roomService.checkOut(order.getOid(), 0);
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
	
	private void ajax(String content){
		try {
			ajax = new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public InputStream getAjax() {
		return ajax;
	}

	public void setAjax(InputStream ajax) {
		this.ajax = ajax;
	}
	

}
