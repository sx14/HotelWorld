package model;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import constant.Config;
import constant.UserRole;

@Entity
@Table(name="user",schema="hotel_world")
public class User {
	private int uid;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String id_num;
	private String email;
	private Visa visa;
	private Date register_date;
	private Date last_charge_date;
	private Set<Order> orders;
	private String image;
	private int role;
	private int money;
	
	@Transient
	public boolean isVIP(){
//		if (visa != null && money>=Config.VIP_MONEY_LEAST) {
		if (visa != null && last_charge_date != null) {
			Date today = Calendar.getInstance().getTime();
			long interval = today.getTime() - last_charge_date.getTime();
			interval = interval - (1000L*60*60*24*365);
			if (interval <= 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	@Transient
	public Date getVIPFailDate(){
		if (isVIP()) {
			long d = last_charge_date.getTime();
			long oneYear = 1000L*60*60*24*365;
			d = d + oneYear;
			Date date = new Date(d);
			return date;
		}else {
			return null;
		}
	}
	
	
	@Transient
	public int getCommentNum(){
		if (orders != null) {
			return orders.size();
		}
		return 0;
	}
	

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
//	@OneToOne(mappedBy="user",fetch=FetchType.LAZY)//从表
////	@JoinColumn(name="uid")
//	public Hotel getHotel() {
//		return hotel;
//	}
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String id_num) {
		this.id_num = id_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getLast_charge_date() {
		return last_charge_date;
	}
	public void setLast_charge_date(Date last_charge_date) {
		this.last_charge_date = last_charge_date;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//立即加载
	@JoinColumn(name="vid")
	public Visa getVisa() {
		return visa;
	}
	public void setVisa(Visa visa) {
		this.visa = visa;
	}
	
	public User(){
		this.role = UserRole.USER.getValue();
		this.password = Config.DEFAULT_PASSWORD;
	}
	
	@Transient
	public String getHiddenName(){
		if(name != null){
			char[] hiddenName = new char[name.length()];
			for(int i = 0 ; i < name.length(); i++){
				if (i == 0) {
					hiddenName[i] = name.charAt(i);
				}else {
					hiddenName[i] = '*';
				}
			}
			return new String(hiddenName);
		}else {
			return null;
		}
	}
	
	@Transient
	public String getHiddenPhone(){
		char[] hiddenPhone = new char[phone.length()];
		for(int i = 0 ; i < phone.length() ; i++){
			if (i < 3 || i >= (phone.length()-4)) {
				hiddenPhone[i] = phone.charAt(i);
			}else{
				hiddenPhone[i] = '*';
			}
		}
		return new String(hiddenPhone);
	}
	
	@Transient
	public String getVIPNum(){
		return String.format("%07d", uid);
	}
}
