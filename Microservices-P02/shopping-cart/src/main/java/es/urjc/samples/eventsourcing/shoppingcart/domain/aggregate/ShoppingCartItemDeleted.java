package es.urjc.samples.eventsourcing.shoppingcart.domain.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.DeleteItemCommand;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartItemDeletedEvent;

@Aggregate
public class ShoppingCartItemDeleted {
   
    @AggregateIdentifier
    private String itemId;

    private String shoppingCartId;

    private String productId;

    private int quantity;

    public ShoppingCartItemDeleted() {
    }

    @CommandHandler
    public ShoppingCartItemDeleted(DeleteItemCommand command){
        Assert.notNull(command.getCartId(), () -> "Shopping cart id must exist");
        Assert.notNull(command.getProductId(), () -> "Product id must exist");
        Assert.notNull(command.getQuantity(), () -> "Quantity must exist");
        AggregateLifecycle.apply(
            new ShoppingCartItemDeletedEvent(
                command.getItemId(),
                command.getCartId(), 
                command.getProductId(), 
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(ShoppingCartItemDeletedEvent event) {
        this.itemId = event.getItemId();
        this.shoppingCartId = event.getCartId();
        this.productId = event.getProductId();
        this.quantity = event.getQuantity();
    }
}
