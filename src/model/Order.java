package model;

import java.text.SimpleDateFormat;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import constant.OrderState;

@Entity
@Table(name="order_o",schema="hotel_world")
public class Order {
	private int oid;
	private Date in_date;
	private Date out_date;
	private Date create_date;
	private int price;
	private int vip_price;
	private int is_vip;
	private Set<Customer> customers;
	private User user;
	private int star;
	private String comment_content;
	private String comment_head;
	private Hotel hotel;
	private Room room;
	private int state;
	
	@Transient
	public String getStateString(){
		String stateString = null;
		switch(state){
			case 0 : stateString = "等待入住";break;
			case 1 : stateString = "已入住";break;
			case 2 : stateString = "已退房";break;
			case 3 : stateString = "已取消";break;
			default : stateString = "未知";
		}
		return stateString;
	}
	
	public Order(){
		state = OrderState.RESERVE.getValue();
		create_date = Calendar.getInstance().getTime();
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getVip_price() {
		return vip_price;
	}
	public void setVip_price(int vip_price) {
		this.vip_price = vip_price;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
//	public int getHid() {
//		return hid;
//	}
//	public void setHid(int hid) {
//		this.hid = hid;
//	}
	public int getStar() {
		return star;
	}
	
	@ManyToOne(targetEntity=Hotel.class)
	@JoinColumn(name="hid")
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_head() {
		return comment_head;
	}

	public void setComment_head(String comment_head) {
		this.comment_head = comment_head;
	}

	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getIn_date() {
		return in_date;
	}
	
	
	
	@ManyToOne
	@JoinColumn(name="rid")
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public Date getOut_date() {
		return out_date;
	}
	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}
	public int getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(int is_vip) {
		this.is_vip = is_vip;
	}
	
	@Transient
	public String getInDateString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(in_date);
	}
	
	@Transient
	public String getOutDateString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(out_date);
	}
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="oid")
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}

	
}
