package service.impl;

import dao.VisaDAO;
import service.TestService;

public class TestServiceImpl implements TestService{
	private VisaDAO visaDAO;
	@Override
	public String getName() {
		return null;
	}
	public VisaDAO getVisaDAO() {
		return visaDAO;
	}
	public void setVisaDAO(VisaDAO visaDAO) {
		this.visaDAO = visaDAO;
	}
	
}
