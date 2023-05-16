package es.urjc.samples.eventsourcing.shoppingcart.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.samples.eventsourcing.shoppingcart.domain.model.CustomerInfo;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, String> {
}
