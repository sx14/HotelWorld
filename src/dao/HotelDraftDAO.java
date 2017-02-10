package dao;

import java.util.List;

import constant.ApplyState;
import model.HotelDraft;

public interface HotelDraftDAO {
	public List<HotelDraft> get(ApplyState state);
}
