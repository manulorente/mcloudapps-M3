package es.urjc.samples.eventsourcing.shoppingcart.domain.event;

import java.math.BigDecimal;

public class ProductCreatedEvent {

    String productId;
    String name;
    String description;
    BigDecimal price;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String productId, String name, String description, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
}
