package service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import constant.OrderState;
import dao.HotelDAO;
import dao.OrderDAO;
import dao.RoomDAO;
import dao.UserDAO;
import model.Hotel;
import model.Order;
import model.Room;
import model.RoomType;
import model.User;
import service.RoomService;

public class RoomServiceImpl implements RoomService{
	private RoomDAO roomDAO;
	private OrderDAO orderDAO;
	private HotelDAO hotelDAO;
	private UserDAO userDAO;

	@Override
	public boolean checkIn(Order order) {
		Order o = orderDAO.getById(order.getOid());
		boolean result = false;
		if (o != null) {
			o.setState(OrderState.IN.getValue());			
			result = orderDAO.update(order);
		}else {
			order.setState(OrderState.IN.getValue());
			if (order.getIs_vip() == 1) {//设置价格
				order.setPrice(order.getRoom().getRoomType().getVip_price());
			}else {
				order.setPrice(order.getRoom().getRoomType().getPrice());
			}
			//给用户注册临时账户
			User user = order.getUser();
			user.setUsername(user.getName());
			user.setRegister_date(Calendar.getInstance().getTime());
			userDAO.saveOrUpdate(order.getUser());
			User u = userDAO.get(order.getUser());
			order.setUser(u);
			result = orderDAO.save(order);
		}
		return result;
	}

	@Override
	public boolean checkOut(int oid) {
		Order o = orderDAO.getById(oid);
		o.setState(OrderState.OUT.getValue());
		return orderDAO.update(o);
	}

	@Override
	public boolean cancelReserve(int oid) {
		Order order = orderDAO.getById(oid);
		order.setState(OrderState.CANCEL.getValue());
		return orderDAO.update(order);
	}
	
	@Override
	public Hotel getRoomByHid(int hid, Date inDate, Date outDate) {
		Hotel hotelRow = hotelDAO.getByHid(hid).get(0);
		return removeUselessOrders(hotelRow, inDate, outDate);
	}

	@Override
	public Hotel getRoomByOwner(int uid,Date inDate ,Date outDate) {
		Hotel hotelRow = hotelDAO.getByUid(uid).get(0);
		return removeUselessOrders(hotelRow, inDate, outDate);
	}
	
	private Hotel removeUselessOrders(Hotel hotel,Date inDate,Date outDate){
		for(RoomType roomType : hotel.getRoomTypes()){
			for(Room room : roomType.getRooms()){
				Set<Order> orders = room.getOrders();
				for(Order order : orders){
					if (order.getOut_date().compareTo(inDate) <= 0) {
						orders.remove(order);
					}else if (order.getIn_date().compareTo(outDate) >= 0) {
						orders.remove(order);
					}
				}
				room.setOrders(orders);
			}
		}
		return hotel;
	}
	
	public RoomDAO getRoomDAO() {
		return roomDAO;
	}
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	public HotelDAO getHotelDAO() {
		return hotelDAO;
	}

	public void setHotelDAO(HotelDAO hotelDAO) {
		this.hotelDAO = hotelDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	

}
