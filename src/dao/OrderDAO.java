package dao;

import java.util.List;

import model.Order;

public interface OrderDAO {
	public List<Order> get(int uid);
}
