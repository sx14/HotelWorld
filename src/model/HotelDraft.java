package model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hotel_draft")
public class HotelDraft {
	private int hid;
//	private int uid;
	private String city;
	private double start_money;
	private Date register_date;
	private User user;
	private int star;
	private String image_small;
	private String image_mid;
	private String image_big;
	private String name;
	private String description;
	private String hotel_name;
	private String id_num;
	private String email;
	private int state;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_small() {
		return image_small;
	}

	public void setImage_small(String image_small) {
		this.image_small = image_small;
	}

	public String getImage_mid() {
		return image_mid;
	}

	public void setImage_mid(String image_mid) {
		this.image_mid = image_mid;
	}

	public String getImage_big() {
		return image_big;
	}

	public void setImage_big(String image_big) {
		this.image_big = image_big;
	}

	
	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	
	@OneToOne(cascade=CascadeType.ALL)//立即加载
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getStart_money() {
		return start_money;
	}
	public void setStart_money(double start_money) {
		this.start_money = start_money;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}	
}
