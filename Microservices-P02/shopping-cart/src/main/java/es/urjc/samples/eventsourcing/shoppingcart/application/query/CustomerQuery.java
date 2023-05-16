package es.urjc.samples.eventsourcing.shoppingcart.application.query;

public class CustomerQuery {

    String customerId;

    public CustomerQuery() {
    }

    public CustomerQuery(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
    
}
