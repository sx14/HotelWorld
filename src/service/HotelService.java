package service;

import java.util.List;

import model.Hotel;
import model.HotelDraft;

public interface HotelService {
	public List<Hotel> getHotels(String city);
	public boolean updateHotelDraft(Hotel hotel);
	public List<Hotel> getNewHotels();
	public List<HotelDraft> getModifyHotels();
	public boolean approveNewHotel(Hotel hotel);
	public boolean approveUpdateHotel(Hotel hotel);
	public Hotel getHotelByUid(int uid);
	public boolean registerHotel(Hotel hotel);
}

