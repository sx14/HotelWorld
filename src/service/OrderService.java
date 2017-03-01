package service;

import java.util.List;


import model.Order;

public interface OrderService {
	public List<Order> getOrderByUid(int uid);
	public Order getOrder(int oid);
	public boolean cancelOrder(int oid);
	public boolean save(List<Order> orders);
	public boolean save(Order order);
}
