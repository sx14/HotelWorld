
package dao;

import model.Visa;

public interface VisaDAO {
	public Visa get(String vid);
	public boolean saveOrUpdate(Visa visa);
}
