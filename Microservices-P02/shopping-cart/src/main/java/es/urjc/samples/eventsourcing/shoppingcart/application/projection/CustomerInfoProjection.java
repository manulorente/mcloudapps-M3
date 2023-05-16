package es.urjc.samples.eventsourcing.shoppingcart.application.projection;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllCustomersQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.CustomerQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.CustomerCreatedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.CustomerInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.repository.CustomerInfoRepository;

@Component
public class CustomerInfoProjection {

    private final CustomerInfoRepository customerInfoRepository;

    public CustomerInfoProjection(CustomerInfoRepository customerInfoRepository) {
        this.customerInfoRepository = customerInfoRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        customerInfoRepository.save(
            new CustomerInfo(event.getCustomerId(), 
                                event.getFullName(), 
                                event.getAddress()));
    }

    @QueryHandler
    public CustomerInfo handle(CustomerQuery query) {
        return customerInfoRepository.findById(query.getCustomerId()).orElse(null);
    }

    @QueryHandler
    public List<CustomerInfo> handle(AllCustomersQuery query) {
        return customerInfoRepository.findAll();
    }
    
}
