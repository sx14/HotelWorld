package dao.impl;

import java.util.Set;

import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.RoomDAO;
import model.Room;

public class RoomDAOImpl implements RoomDAO{
	private SessionFactory sessionFactory;
	@Override
	public boolean save(Set<Room> rooms) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for(Room room : rooms){
			session.save(room);
		}
		transaction.commit();
		session.close();
		return true;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
