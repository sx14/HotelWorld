package service;

import java.util.List;

import model.Hotel;
import model.HotelDraft;
import vo.RoomVO;

public interface HotelService {
	public List<Hotel> getHotels(String city);
	public boolean registerHotel(Hotel hotel,List<RoomVO> roomVOs);
	public List<Hotel> getNewHotels();
	public List<HotelDraft> getModifyHotels();
	public boolean approveNewHotel(Hotel hotel);
}
