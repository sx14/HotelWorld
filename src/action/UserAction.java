package action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import constant.Config;
import constant.UserRole;
import model.Hotel;
import model.Order;
import model.User;
import model.Visa;
import service.HotelService;
import service.OrderService;
import service.UserService;

public class UserAction extends ActionSupport{
	private static final long serialVersionUID = -2992907193368305060L;
	private UserService userService;
	private User user;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private static final String headPath = "img/head";
	
	
	public String registerVIP(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String storePath = ServletActionContext.getServletContext().getRealPath("/"+headPath);
		System.out.println("real path:"+storePath);
		imageFileName = user.getUid()+imageFileName;
		try {
			File saveFile = new File(new File(storePath),imageFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(image,saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		user.setName(this.user.getName());
		user.setId_num(this.user.getId_num());
		user.setEmail(this.user.getEmail());
		user.setImage(headPath+"/"+imageFileName);
		Visa visa = user.getVisa();
		if (visa == null) {
			visa = new Visa();
		}
		visa.setNum(this.user.getVisa().getNum());
		visa.setMoney(Config.MONEY_IN_VISA);
		user.setVisa(visa);
		boolean userResult = userService.saveOrUpdate(user);
		if (userResult == true) {
			session.replace("user", user);
			return SUCCESS;
		}else {
			return ERROR;
		}
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
			}else{
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
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	
}
