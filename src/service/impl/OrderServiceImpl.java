package service.impl;

import java.util.List;

import constant.OrderState;
import dao.OrderDAO;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDAO;
	@Override
	public List<Order> getOrder(int uid) {
		return orderDAO.getByUser(uid);
	}

	@Override
	public boolean save(List<Order> orders) {
		return orderDAO.save(orders);
	}
	
	@Override
	public boolean cancelOrder(int oid) {
		Order order = orderDAO.getById(oid);
		order.setState(OrderState.CANCEL.getValue());
		return orderDAO.update(order);
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
}
