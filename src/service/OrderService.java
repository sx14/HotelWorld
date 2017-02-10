package service;

import java.util.List;

import model.Order;

public interface OrderService {
	public List<Order> getOrder(int uid);
}
