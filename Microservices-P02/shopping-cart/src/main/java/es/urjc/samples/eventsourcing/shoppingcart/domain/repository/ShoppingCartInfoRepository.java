package es.urjc.samples.eventsourcing.shoppingcart.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartInfo;

public interface ShoppingCartInfoRepository extends JpaRepository<ShoppingCartInfo, String> {
}
