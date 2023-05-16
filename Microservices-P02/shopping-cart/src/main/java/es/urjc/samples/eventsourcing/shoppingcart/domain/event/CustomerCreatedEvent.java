package es.urjc.samples.eventsourcing.shoppingcart.domain.event;

public class CustomerCreatedEvent {

    String customerId;
    String fullName;
    String address;

    public CustomerCreatedEvent() {
    }

    public CustomerCreatedEvent(String customerId, String fullName, String address) {
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
