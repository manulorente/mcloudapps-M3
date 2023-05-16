package es.urjc.samples.eventsourcing.shoppingcart.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
}
