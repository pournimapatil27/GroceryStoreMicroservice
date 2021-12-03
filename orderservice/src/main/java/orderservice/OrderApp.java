package orderservice;


import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import orderservice.service.OrderService;

@SpringBootApplication
@RestController //Enabling REST functionality. With this, we can now expose our own endpoints
@CrossOrigin
public class OrderApp {

	@Autowired
	OrderService orderService;
	
	public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
	
    @GetMapping("/fetchOrder/{orderId}")
    public ResponseEntity<OrderDTO> fetchOrder(@PathVariable String orderId){
    	OrderDTO orderDetails = new OrderDTO();
    	
    	try {
    	orderDetails = orderService.fetchOrder(orderId);
    	orderDetails.setOrderId("orderId");
    	} catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(orderDetails);

    }
    
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
}
