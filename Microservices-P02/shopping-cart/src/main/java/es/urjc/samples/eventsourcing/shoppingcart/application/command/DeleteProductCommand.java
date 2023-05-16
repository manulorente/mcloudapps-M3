package es.urjc.samples.eventsourcing.shoppingcart.application.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private final String cartId;

    @TargetAggregateIdentifier
    private final String productId;

    public DeleteProductCommand(String cartId, String productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }
    
}
