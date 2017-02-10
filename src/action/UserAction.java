package action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.operations.Or;

import constant.UserRole;
import model.Order;
import model.User;
import service.OrderService;
import service.UserService;

public class UserAction extends ActionSupport{
	private static final long serialVersionUID = -2992907193368305060L;
	private UserService userService;
	private OrderService orderService;
	private User user;
	
	public String personalHome(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		List<Order> orders = orderService.getOrder(user.getUid());
		int consumeSum = 0;
		for(Order order : orders){
			if (order.getIs_vip() == 1) {
				consumeSum += order.getPrice();
			}else {
				consumeSum += order.getVip_price();
			}
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orders", orders);
		request.put("consumeSum", consumeSum);
		return SUCCESS;
	}
	
	public String registerQuickly(){
		//TODO 读取表单数据，创建User
		User user1 = new User();
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		user1.setPhone(user.getPhone());
		Calendar today = Calendar.getInstance();
		Date date = today.getTime();
		user1.setRegister_date(date);
		boolean result = userService.registerQuickly(user1);
		System.out.println("do register!");
		if (result == true) {
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("user", user1);
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String login(){
		User user1 = new User();
		user1.setPhone(user.getPhone());
		user1.setPassword(user.getPassword());
		User userDetail = userService.login(user1);
		if (userDetail != null) {
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("user", userDetail);
			if (userDetail.getRole()== UserRole.MANAGER.getValue()) {
				return UserRole.MANAGER.name();
			}else if (userDetail.getRole() == UserRole.STAFF.getValue()) {
				return UserRole.STAFF.name();
			}{
				return UserRole.USER.name();
			}
		}else{
			return ERROR;
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
