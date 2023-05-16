package es.urjc.samples.eventsourcing.shoppingcart.domain.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.AddItemCommand;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartItemCreatedEvent;

@Aggregate
public class ShoppingCartItem {

    @AggregateIdentifier
    private String shoppingCartId;

    private String productId;

    private int quantity;

    public ShoppingCartItem() {
    }

    @CommandHandler
    public ShoppingCartItem(AddItemCommand command) {
        Assert.notNull(command.getCartId(), () -> "Shopping cart id must exist");
        Assert.notNull(command.getProductId(), () -> "Product id must exist");
        Assert.isTrue(command.getQuantity() > 0, () -> "Quantity must be greater than 0");
        AggregateLifecycle.apply(
            new ShoppingCartItemCreatedEvent(
                command.getCartId(), 
                command.getProductId(), 
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void createItem(ShoppingCartItemCreatedEvent event) {
        this.shoppingCartId = event.getCartId();
        this.productId = event.getProductId();
        this.productId = event.getProductId();
        this.quantity = event.getQuantity();
    }
    
}
