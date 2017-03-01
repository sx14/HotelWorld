package model;

import java.util.Set;

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
@Table(name="room")
public class Room {
//	private int tid;
	private int rid;
	private int num;
	private RoomType roomType;
	private Set<Order> orders;
	
	@Transient
	public Order getOrder(){
		Order o = null;
		for(Order order : orders){
			o =  order;
			break;
		}
		return o;
	}

	@Transient
	public String getState(){
		String state = "空闲";
		if (orders != null) {
			for(Order order : orders){
				state = OrderState.getRoomStateInChinese(order.getState());
				break;
			}
		}
		return state;
	}
	
	@Transient
	public String getRoomName(){
		String roomName = roomType.getType() + String.format("%03d", num);
		return roomName;
	}
	

	@OneToMany(mappedBy="room",fetch=FetchType.EAGER)
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@ManyToOne
	@JoinColumn(name="tid")
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
