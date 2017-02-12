package service.impl;

import java.util.List;

import dao.OrderDAO;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDAO;
	@Override
	public List<Order> getOrder(int uid) {
		return orderDAO.get(uid);
	}
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
}
