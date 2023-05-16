package es.urjc.samples.eventsourcing.shoppingcart.application.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteItemCommand {

    @TargetAggregateIdentifier
    private final String itemId;

    private final String cartId;

    private final String productId;

    private final int quantity;

    public DeleteItemCommand(String itemId, String cartId, String productId, int quantity) {
        this.itemId = itemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
