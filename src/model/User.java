package model;

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
//	private Set<Order> orders;
//	private Hotel hotel;
	private String image;
	private int role;
	

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
//	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
//	public Set<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
	
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
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)//立即加载
	@JoinColumn(name="vid")
	public Visa getVisa() {
		return visa;
	}
	public void setVisa(Visa visa) {
		this.visa = visa;
	}
	
	public User(){
		this.role = UserRole.USER.getValue();
	}
	
	
}
