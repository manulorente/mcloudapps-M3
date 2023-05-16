package es.urjc.samples.eventsourcing.shoppingcart.domain.model;

import javax.persistence.*;

@Entity
public class ShoppingCartItemInfo {

    @Id
    private String cartItemId;

    private String productId;
    
    private int quantity;

    public ShoppingCartItemInfo() {
    }

    public ShoppingCartItemInfo(String cartItemId, String productId, int quantity) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
