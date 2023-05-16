package es.urjc.samples.eventsourcing.shoppingcart.domain.event;

public class ShoppingCartItemDeletedEvent {

    String itemId;

    String cartId;

    String productId;

    int quantity;

    public ShoppingCartItemDeletedEvent() {
    }

    public ShoppingCartItemDeletedEvent(String itemId, String cartId, String productId, int quantity) {
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
