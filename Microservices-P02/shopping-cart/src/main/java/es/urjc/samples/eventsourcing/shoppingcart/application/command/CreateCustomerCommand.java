package es.urjc.samples.eventsourcing.shoppingcart.application.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateCustomerCommand {

    @TargetAggregateIdentifier
    private final String customerId;

    private final String fullName;

    private final String address;

    public CreateCustomerCommand(String customerId, String fullName, String address) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }
    
}
