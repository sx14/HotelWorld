package model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer",schema="hotel_world")
public class Customer {
	private int cid;
	private Order order;

	private String name;
	private String id_num;
	
	@ManyToOne
	@JoinColumn(name="oid")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
//	public int getOid() {
//		return oid;
//	}
//	public void setOid(int oid) {
//		this.oid = oid;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String id_num) {
		this.id_num = id_num;
	}
	
	
}
