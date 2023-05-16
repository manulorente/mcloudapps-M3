package es.urjc.samples.eventsourcing.shoppingcart.domain.event;

public class ShoppingCartCreatedEvent {
    
    String cartId;

    String customerId;

    public ShoppingCartCreatedEvent() {
    }

    public ShoppingCartCreatedEvent(String cartId, String customerId) {
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
