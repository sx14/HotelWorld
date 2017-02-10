package action;

import com.opensymphony.xwork2.ActionSupport;

import service.TestService;

public class TestAction extends ActionSupport {
	TestService testService;
	public TestService getTestService() {
		return testService;
	}
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	@Override
	public String execute(){
		System.out.println(testService.getName());
		return SUCCESS;
	}
}
