package dao;

import java.util.List;

import constant.ApplyState;
import model.Hotel;
import model.HotelDraft;

public interface HotelDraftDAO {
	public List<HotelDraft> get(ApplyState state);
	public boolean save(HotelDraft hotel);
}
