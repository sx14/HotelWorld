package action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import constant.OrderOperate;
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
	
	public String cancelOrder(){
		boolean result = orderService.cancelOrder(oid);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String saveOrders(){
		Map session = ActionContext.getContext().getSession();
		List<Order> orders = (List<Order>)session.get("orders");
		if (customer1 != null) {
			Order o = getOrder(orders, customer1.getOid());
			customer1.setOrder(o);
			addCustomer(customer1,o);
			
		}
		if (customer2 != null) {
			Order o = getOrder(orders, customer2.getOid());
			customer2.setOrder(o);
			addCustomer(customer2,o);
		}
		if (customer3 != null) {
			Order o = getOrder(orders, customer3.getOid());
			customer3.setOrder(o);
			addCustomer(customer3,o);
		}
		boolean result = orderService.save(orders);
		if (result == true) {
			return SUCCESS;
		}else {
			return ERROR;
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
		if (customer != null) {
			Set<Customer> customers = new HashSet<>();
			customers.add(customer);
			order.setCustomers(customers);
		}
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
	
	

}
