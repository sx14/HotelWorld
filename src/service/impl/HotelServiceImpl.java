package service.impl;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import constant.ApplyState;
import constant.UserRole;
import dao.HotelDAO;
import dao.HotelDraftDAO;
import dao.RoomDAO;
import model.Hotel;
import model.HotelDraft;
import model.Room;
import model.RoomType;
import model.RoomDraft;
import service.HotelService;

public class HotelServiceImpl implements HotelService{
	private HotelDAO hotelDAO;
	private HotelDraftDAO hotelDraftDAO;
	private RoomDAO roomDAO;
	
	
	


	@Override
	public List<Hotel> getHotels(String city) {
		List<Hotel> allHotels = hotelDAO.get(city);
		List<Hotel> topHotels = sortByStar(allHotels);
		return topHotels;
	}
	
	private List<Hotel> sortByStar(List<Hotel> hotels){
		for(int i = 0 ; i < hotels.size() - 1; i ++){
			for(int j = 0 ; j < hotels.size() - 1 - i; j ++){
				Hotel front = hotels.get(j);
				Hotel back = hotels.get(j+1);
				if (front.getAvgStar() > back.getAvgStar()) {
					hotels.set(j, back);
					hotels.set(j+1, front);
				}
			}
		}
		return hotels;
	}
	
	public boolean registerHotel(Hotel hotel){
		return hotelDAO.saveOrUpdate(hotel);
	}

	@Override
	public boolean updateHotelDraft(Hotel hotel) {
		String roomPath = "img/room";
		HotelDraft hotelDraft = new HotelDraft(hotel);
		Set<RoomDraft> roomDrafts = new HashSet<>();
		for(RoomType roomType : hotel.getRoomTypes()){
			if (roomType.getImg() != null) {
				String storePath = ServletActionContext.getServletContext().getRealPath("/"+roomPath);
				roomType.setImgFileName(hotel.getHid()+roomType.getImgFileName());
				File img = new File(new File(storePath),roomType.getImgFileName());
				if (!img.getParentFile().exists()) {
					img.getParentFile().mkdirs();
				}
				try {
					FileUtils.copyFile(roomType.getImg(), img);
				} catch (IOException e) {
					e.printStackTrace();
				}
				roomType.setImage(roomPath+"/"+roomType.getImgFileName());
			}

			roomType.setHotel(hotel);
			RoomDraft roomDraft = new RoomDraft(roomType);
			roomDraft.setHotelDraft(hotelDraft);
			roomDrafts.add(roomDraft);
		}
		hotelDraft.setRoomDrafts(roomDrafts);
		return  hotelDraftDAO.save(hotelDraft);
	}

	@Override
	public List<Hotel> getNewHotels() {
		return hotelDAO.get(ApplyState.WAIT);
	}

	@Override
	public List<HotelDraft> getModifyHotels() {
		return hotelDraftDAO.get(ApplyState.WAIT);
	}

	@Override
	public boolean approveNewHotel(Hotel hotel) {
		Set<RoomType> roomTypes = hotel.getRoomTypes();
		for(RoomType roomType : roomTypes){
			Set<Room> rooms = new HashSet<>();
			for(int i = 0 ; i < roomType.getNum() ; i++){
				Room room = new Room();
//				room.setTid(roomType.getTid());
				room.setRoomType(roomType);
				room.setNum(i+1);
				rooms.add(room);
			}
			roomType.setRooms(rooms);
		}
		hotel.setRoomTypes(roomTypes);
		hotel.getUser().setRole(UserRole.OWNER.getValue());
		hotel.setState(ApplyState.PASS.getValue());
		boolean hotelResult =  hotelDAO.saveOrUpdate(hotel);
		if (hotelResult == true) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Hotel getHotelByUid(int uid) {
		List<Hotel> hotels = hotelDAO.getByUid(uid);
		if (hotels != null && hotels.size() != 0) {
			return hotels.get(0);
		}
		return null;
	}
	
	public HotelDraftDAO getHotelDraftDAO() {
		return hotelDraftDAO;
	}

	public void setHotelDraftDAO(HotelDraftDAO hotelDraftDAO) {
		this.hotelDraftDAO = hotelDraftDAO;
	}

	public HotelDAO getHotelDAO() {
		return hotelDAO;
	}

	public void setHotelDAO(HotelDAO hotelDAO) {
		this.hotelDAO = hotelDAO;
	}

	@Override
	public boolean approveUpdateHotel(Hotel hotel) {
		return false;
	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
	
}
