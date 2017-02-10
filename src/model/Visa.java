package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visa",schema="hotel_world")
public class Visa {
	private String vid;
	private double money;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public Visa() {
	}
	
	public Visa(String vid , double money){
		this.vid = vid;
		this.money = money;
	}
	
}
