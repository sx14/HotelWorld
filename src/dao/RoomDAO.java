package dao;

import java.util.Set;

import model.Room;

public interface RoomDAO {
	public boolean save(Set<Room> rooms);
}
