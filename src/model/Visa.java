package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constant.Config;

@Entity
@Table(name="visa",schema="hotel_world")
public class Visa {
	private int vid;
	private int money;
	private String num;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public Visa() {
		money = 0;
		password = Config.DEFAULT_PASSWORD;
	}
	
	public Visa(String num , int money){
		this.num = num;
		this.money = money;
	}
}
