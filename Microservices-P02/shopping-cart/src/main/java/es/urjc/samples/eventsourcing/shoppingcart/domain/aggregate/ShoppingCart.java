package es.urjc.samples.eventsourcing.shoppingcart.domain.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateShoppingCartCommand;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartCreatedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartItemInfo;

@Aggregate
public class ShoppingCart {

    @AggregateIdentifier
    private String shoppingCartId;

    private String customerId;

    private List<ShoppingCartItemInfo> items;

    public ShoppingCart() {
    }

    @CommandHandler
    public ShoppingCart(CreateShoppingCartCommand command) {
        Assert.notNull(command.getCartId(), () -> "Shopping cart id must exist");   
        Assert.notNull(command.getCustomerId(), () -> "Customer id must exist");
        AggregateLifecycle.apply(
            new ShoppingCartCreatedEvent(
                command.getCartId(), 
                command.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(ShoppingCartCreatedEvent event) {
        this.shoppingCartId = event.getCartId();
        this.customerId = event.getCustomerId();
        this.items = new ArrayList<>();
    }
    
}
