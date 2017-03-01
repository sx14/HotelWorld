package dao;

import java.util.List;

import constant.ApplyState;
import model.Hotel;

public interface HotelDAO {
	public List<Hotel> get(String city, int level);
	public List<Hotel> getByUid(int uid);
	public List<Hotel> getByHid(int hid);
	public boolean saveOrUpdate(Hotel hotel);
	public List<Hotel> get(ApplyState state);
}

