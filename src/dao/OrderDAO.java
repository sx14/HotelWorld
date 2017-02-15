package dao;

import java.sql.Date;
import java.util.List;

import model.Order;

public interface OrderDAO {
	public List<Order> getByUser(int uid);
	public List<Order> getByHotel(int hid,Date inDate ,Date outDate);
	public boolean update(Order order);
	public boolean save(Order order);
	public boolean save(List<Order> orders);
	public Order getById(int oid);
}
