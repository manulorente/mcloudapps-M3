package es.urjc.samples.eventsourcing.shoppingcart.application.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateShoppingCartCommand {

    @TargetAggregateIdentifier
    private final String cartId;

    private final String customerId;

    public CreateShoppingCartCommand(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public String getCartId() {
        return cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

}
