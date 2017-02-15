package service;

import java.util.List;

import model.Order;

public interface OrderService {
	public List<Order> getOrder(int uid);
	public boolean cancelOrder(int oid);
	public boolean save(List<Order> orders);
}
