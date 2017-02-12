package dao;

import java.util.List;

import constant.ApplyState;
import model.Hotel;

public interface HotelDAO {
	public List<Hotel> get(String city);
	public List<Hotel> get(int uid);
	public boolean saveOrUpdate(Hotel hotel);
	public List<Hotel> get(ApplyState state);
}
