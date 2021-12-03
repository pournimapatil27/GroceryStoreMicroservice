package login.StoreLogin.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import login.StoreLogin.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

}


