package es.urjc.samples.eventsourcing.shoppingcart.domain.model;

import javax.persistence.*;

@Entity
public class ShoppingCartItemInfo {

    @Id
    private int cartItemId;

    private String productId;
    private int quantity;

    public ShoppingCartItemInfo() {
    }

    public ShoppingCartItemInfo(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
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
