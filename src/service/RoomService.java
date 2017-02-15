package service;


import java.util.Date;

import model.Hotel;
import model.Order;

public interface RoomService {
	public Hotel getRoomByOwner(int uid,Date inDate,Date outDate);
	public Hotel getRoomByHid(int hid,Date inDate,Date outDate);
	public boolean checkIn(Order order);
	public boolean checkOut(int oid);
	public boolean cancelReserve(int oid);
}
