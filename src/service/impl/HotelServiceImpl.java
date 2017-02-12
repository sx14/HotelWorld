package service.impl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import constant.ApplyState;
import dao.HotelDAO;
import dao.HotelDraftDAO;
import model.Hotel;
import model.HotelDraft;
import model.Room;
import service.HotelService;
import vo.RoomVO;

public class HotelServiceImpl implements HotelService{
	private HotelDAO hotelDAO;
	private HotelDraftDAO hotelDraftDAO;
	
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

	@Override
	public boolean registerOrUpdateHotel(Hotel hotel) {
		Set<Room> rooms = new HashSet<>();
		for(RoomVO roomVO : hotel.getRoomVOs()){
			int num = roomVO.getNum();
			int capacity = roomVO.getCapacity();
			int type = roomVO.getType();
			for(int i = 0 ; i < num ; i++){//根据注册时的房间数量，给每个房间建立一条记录
				Room room = new Room();
				room.setRid(i+1);
				room.setCapacity(capacity);
				room.setHotel(hotel);
				room.setType(type);
				rooms.add(room);
			}
		}
		hotel.setRooms(rooms);
		return  hotelDAO.saveOrUpdate(hotel);
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
		hotel.setState(ApplyState.PASS.getValue());
		return hotelDAO.saveOrUpdate(hotel);
	}

	@Override
	public Hotel getHotel(int uid) {
		List<Hotel> hotels = hotelDAO.get(uid);
		if (hotels != null && hotels.size() != 0) {
			return hotels.get(0);
		}
		return null;
	}
	
	
//	private void temp(){
//		List<HotelDraft> hotelDrafts =  hotelDraftDAO.get(ApplyState.WAIT);
//		List<Hotel> hotels = new ArrayList<>();
//		for(HotelDraft hotelDraft : hotelDrafts){
//			Hotel hotel = new Hotel();
//			hotel.setCity(hotelDraft.getCity());
//			hotel.setDescription(hotelDraft.getDescription());
//			hotel.setEmail(hotelDraft.getEmail());
//			hotel.setHid(hotelDraft.getHid());
//			hotel.setHotel_name(hotelDraft.getHotel_name());
//			hotel.setId_num(hotelDraft.getId_num());
//			hotel.setName(hotelDraft.getName());
//			hotel.setRegister_date(hotelDraft.getRegister_date());
//			hotel.setStar(hotelDraft.getStar());
//			hotel.setStart_money(hotelDraft.getStart_money());
//			hotel.setState(hotelDraft.getState());
//			hotel.setUser(hotelDraft.getUser());
//		}
//	}
}
