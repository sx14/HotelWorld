package service.impl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import util.FileHelper;

public class HotelServiceImpl implements HotelService{
	private HotelDAO hotelDAO;
	private HotelDraftDAO hotelDraftDAO;
	private RoomDAO roomDAO;
	
	@Override
	public List<Hotel> getHotels(String city, int level) {
		List<Hotel> allHotels = hotelDAO.get(city, level);
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
	public boolean saveHotelDraft(Hotel hotel) {
		String roomPath = "img/room";
		String hotelPath = "img/hotel";
		HotelDraft hotelDraft = new HotelDraft(hotel);
		hotelDraft.setState(ApplyState.WAIT.getValue());
		Set<RoomDraft> roomDrafts = new HashSet<>();
		String storeRoomPath = ServletActionContext.getServletContext().getRealPath("/"+roomPath);
		for(RoomType roomType : hotel.getRoomTypes()){
			if (roomType.getImg() != null) {
				roomType.setImgFileName(roomPath+"/"+roomType.getTid()+roomType.getImgFileName());
				FileHelper.saveFile(roomType.getImg(),roomType.getImgFileName(), storeRoomPath);
				roomType.setImage(roomType.getImgFileName());
			}
			RoomDraft roomDraft = new RoomDraft(roomType);
			roomDraft.setHotelDraft(hotelDraft);
			roomDrafts.add(roomDraft);
		}
		String storeHotelPath = ServletActionContext.getServletContext().getRealPath("/"+hotelPath);
		FileHelper.saveFile(hotel.getImgS(), hotel.getHid()+hotel.getImgSFileName(), storeHotelPath);
		FileHelper.saveFile(hotel.getImgM(), hotel.getHid()+hotel.getImgMFileName(), storeHotelPath);
		FileHelper.saveFile(hotel.getImgB(), hotel.getHid()+hotel.getImgBFileName(), storeHotelPath);
		hotelDraft.setImage_small(hotelPath + "/s" + hotel.getHid() + hotel.getImgSFileName());
		hotelDraft.setImage_mid(hotelPath + "/m" + hotel.getHid() + hotel.getImgMFileName());
		hotelDraft.setImage_big(hotelPath + "/b" + hotel.getHid() + hotel.getImgBFileName());
		hotelDraft.setRoomDrafts(roomDrafts);
		return  hotelDraftDAO.saveOrUpdate(hotelDraft);
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
	public boolean updateHotel(HotelDraft hotelDraft) {
		boolean result1 = true;
		boolean result2 = true;
		if (hotelDraft.getState() == ApplyState.PASS.getValue()) {
			Hotel hotel = hotelDAO.getByHid(hotelDraft.getHid()).get(0);
			hotel.modifyHotel(hotelDraft);
			result1 = hotelDAO.saveOrUpdate(hotel);
			result2 = hotelDraftDAO.saveOrUpdate(hotelDraft);
		}
		return (result1 && result2);
	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
}
