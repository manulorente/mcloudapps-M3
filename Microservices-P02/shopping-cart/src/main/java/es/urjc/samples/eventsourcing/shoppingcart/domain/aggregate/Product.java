package es.urjc.samples.eventsourcing.shoppingcart.domain.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateProductCommand;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ProductCreatedEvent;

@Aggregate
public class Product {

    @AggregateIdentifier
    private String productId;

    private String name;
    
    private String description;

    private BigDecimal price;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        Assert.notNull(command.getProductId(), () -> "Product id should not be null");
        Assert.notNull(command.getName(), () -> "Product Name is mandatory");
        Assert.notNull(command.getDescription(), () -> "Product Description is mandatory");
        Assert.isTrue(command.getPrice().doubleValue() > 0, null);
        AggregateLifecycle.apply(
            new ProductCreatedEvent(
                command.getProductId(), 
                command.getName(), 
                command.getDescription(),
                command.getPrice()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();
    }
    
}
