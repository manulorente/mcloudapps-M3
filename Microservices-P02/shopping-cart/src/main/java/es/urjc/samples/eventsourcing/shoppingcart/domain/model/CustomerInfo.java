package es.urjc.samples.eventsourcing.shoppingcart.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerInfo {

    @Id
    private String customerId;
    private String fullName;
    private String address;

    public CustomerInfo(){

    }

    public CustomerInfo(String customerId, String fullName, String address) {
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
