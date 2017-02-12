package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="visa",schema="hotel_world")
public class Visa {
	private int vid;
	private double money;
	private String num;
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public Visa() {
		money = 0;
	}
	
	public Visa(String num , double money){
		this.num = num;
		this.money = money;
	}
}
