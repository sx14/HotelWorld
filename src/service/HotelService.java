package service;

import java.util.List;

import model.Hotel;
import model.HotelDraft;

public interface HotelService {
	public List<Hotel> getHotels(String city, int level);
	public boolean saveHotelDraft(Hotel hotel);
	public List<Hotel> getNewHotels();
	public List<HotelDraft> getModifyHotels();
	public boolean approveNewHotel(Hotel hotel);
	public boolean updateHotel(HotelDraft hotelDraft);
	public Hotel getHotelByUid(int uid);
	public boolean registerHotel(Hotel hotel);
	public String getStatisticData(Hotel hotel);
	public Hotel getHotelByHid(int hid);
}

