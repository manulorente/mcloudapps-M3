package es.urjc.samples.eventsourcing.shoppingcart.domain.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateCustomerCommand;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.CustomerCreatedEvent;

@Aggregate
public class Customer {

    @AggregateIdentifier
    private String customerId;

    private String fullName;
    
    private String address;

    public Customer() {
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        Assert.notNull(command.getCustomerId(), () -> "Customer id should not be null");
        Assert.notNull(command.getFullName(), () -> "Customer Name is mandatory");
        Assert.notNull(command.getAddress(), () -> "Customer Address is mandatory");
        AggregateLifecycle.apply(
            new CustomerCreatedEvent(
                command.getCustomerId(), 
                command.getFullName(), 
                command.getAddress()));
    }
    
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.customerId = event.getCustomerId();
        this.fullName = event.getFullName();
        this.address = event.getAddress();
    }
}
