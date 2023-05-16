package es.urjc.samples.eventsourcing.shoppingcart.domain.event;

public class ShoppingCartItemCreatedEvent {

    String cartId;

    String productId;

    int quantity;

    public ShoppingCartItemCreatedEvent() {
    }

    public ShoppingCartItemCreatedEvent(String cartId, String productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
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
