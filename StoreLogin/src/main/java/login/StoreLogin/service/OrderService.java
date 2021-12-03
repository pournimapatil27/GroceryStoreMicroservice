package login.StoreLogin.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import login.StoreLogin.OrderDTO;
import login.StoreLogin.dto.repository.OrderRepository;
import login.StoreLogin.entity.OrderEntity;

@Service
public class OrderService {

	@Autowired
	private Environment env;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public String generateOrderToken (OrderDTO orderDTO) {
		
    	String token = Jwts.builder()
    			.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("app.jwtExpiration"))))
    			.claim("issuer", env.getProperty("spring.security.token.issuer"))
    			.claim("orderId", orderDTO.getOrderId())
    			.claim("itemName", orderDTO.getItemName())
    			.signWith(SignatureAlgorithm.HS512, env.getProperty("app.jwtSecret"))
    			.compact();
    	System.out.println("token="+token);
    	
    	OrderEntity orderEntity = new OrderEntity();
    	orderEntity.setOrderId(orderDTO.getOrderId());
    	orderEntity.setItemName(orderDTO.getItemName());
    	orderEntity.setUsername(orderDTO.getUserName());
    	orderEntity.setOrderToken(token);
    	 
    	orderRepository.save(orderEntity);
    	//httpResponse.sendRedirect("http://localhost:8090/fetchOrder?orderId=654");
		
		return token;
	}
	
}
