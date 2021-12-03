package orderservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import orderservice.OrderDTO;
import orderservice.dto.repository.OrderRepository;
import orderservice.entity.OrderEntity;

@Service
public class OrderService {

	@Autowired
	private Environment env;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderDTO fetchOrder(String orderId) throws Exception {
		
		OrderEntity orderEntity =  orderRepository.findById(orderId).orElse(null);
    	
    	Claims claims = Jwts.parser()
		.setSigningKey(env.getProperty("app.jwtSecret"))
		.parseClaimsJws(orderEntity.getOrderToken())
		.getBody();
    	
		System.out.println("claims="+claims);
		
		if (!claims.get("issuer").equals(env.getProperty("spring.security.token.issuer"))) {
			throw new Exception("Invalid token issuer");
		}
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(orderId);
		orderDTO.setItemName((String)claims.get("itemName"));
		
		return orderDTO;
	}
	
}
