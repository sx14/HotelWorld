package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import constant.Config;
import constant.UserRole;
import model.User;
import model.Visa;
import service.UserService;

public class UserAction extends ActionSupport{
	private static final long serialVersionUID = -2992907193368305060L;
	private UserService userService;
	private User user;
	private File image;
	private String imageFileName;
	private String imageContentType;


	private static final String headPath = "img/head";
	
	//会员卡充值
	private int chargeMoney;
	private String password;
	
	private void ajax(String content){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			printWriter.write(content);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	

	
	public String logout(){
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
	
	public String chargeVIP(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (!password.equals(user.getVisa().getPassword())) {//密码错误
			return "wrong_password";
		}
		boolean result = false;
		int money = user.getVisa().getMoney();
		if (money >= chargeMoney) {
			user.getVisa().setMoney(money - chargeMoney);
			user.setMoney(user.getMoney() + chargeMoney);
			result = userService.saveOrUpdate(user);
		}else {
			return "not_enough";
		}
		if (result == true) {
			session.replace("user", user);
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String registerVIP(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String storePath = ServletActionContext.getServletContext().getRealPath("/"+headPath);
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
		visa.setPassword(Config.DEFAULT_PASSWORD);
		user.setVisa(visa);
		boolean userResult = userService.saveOrUpdate(user);
		if (userResult == true) {
			session.replace("user", user);
			return SUCCESS;
		}else {
			return ERROR;
		}
	}

	public void checkExists(){
		boolean isExists = userService.checkExists(user);
		if (isExists) {
			ajax("error");
		}else{
			ajax("success");
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
		User u = userService.registerQuickly(user1);
		if (u != null) {
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("user", u);
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
			}else if (userDetail.getRole() == UserRole.OWNER.getValue()) {
				return UserRole.OWNER.name();
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
	
	public int getChargeMoney() {
		return chargeMoney;
	}

	public void setChargeMoney(int chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
