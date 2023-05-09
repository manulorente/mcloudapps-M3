package es.codeurjc.deliveryservice.repository;

import java.util.Optional;
import java.util.UUID;

import es.codeurjc.deliveryservice.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,UUID>{

	Optional<Delivery> findByOrderId(@Param("orderId") UUID orderId);

}