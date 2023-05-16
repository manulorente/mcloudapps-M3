package es.urjc.samples.eventsourcing.shoppingcart.domain.model;

import javax.persistence.*;

import java.util.List;

@Entity
public class ShoppingCartInfo {

    @Id
    private String cartId;

    private String customerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<ShoppingCartItemInfo> items;

    public ShoppingCartInfo() {
    }

    public ShoppingCartInfo(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ShoppingCartItemInfo> getItems() {
        return items;
    }

    public void addItem(ShoppingCartItemInfo item) {
        this.items.add(item);
    }

    public void removeItem(ShoppingCartItemInfo item) {
        this.items.remove(item);
    }
    
}
