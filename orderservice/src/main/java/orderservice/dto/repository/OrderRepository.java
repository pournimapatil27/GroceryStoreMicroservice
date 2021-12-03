package orderservice.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderservice.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

}


