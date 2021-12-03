package orderservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orderz")
public class OrderEntity {

	
	@Id
	private String orderId;

	private String username;

	private String orderToken;
	
	public OrderEntity() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", username=" + username + ", orderToken=" + orderToken + "]";
	}

	
	
	
	
}
