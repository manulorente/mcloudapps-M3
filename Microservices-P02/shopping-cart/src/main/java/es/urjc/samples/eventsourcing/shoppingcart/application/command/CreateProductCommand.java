package es.urjc.samples.eventsourcing.shoppingcart.application.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String productId;

    private final String name;

    private final String description;

    private final BigDecimal price;

    public CreateProductCommand(String productId, String name, String description, BigDecimal price) {
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
