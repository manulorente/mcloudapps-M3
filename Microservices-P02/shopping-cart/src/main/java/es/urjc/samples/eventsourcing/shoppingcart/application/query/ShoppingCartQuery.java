package es.urjc.samples.eventsourcing.shoppingcart.application.query;

public class ShoppingCartQuery {

    String cartId;

    public ShoppingCartQuery() {
    }

    public ShoppingCartQuery(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
    
}
