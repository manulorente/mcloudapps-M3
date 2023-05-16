package es.urjc.samples.eventsourcing.shoppingcart.application.projection;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllShoppingCartsQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.ShoppingCartQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartCreatedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartItemCreatedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ShoppingCartItemDeletedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartItemInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.repository.ShoppingCartInfoRepository;

@Component
public class ShoppingCartProjection {

    private final ShoppingCartInfoRepository shoppingCartInfoRepository;

    public ShoppingCartProjection(ShoppingCartInfoRepository shoppingCartInfoRepository) {
        this.shoppingCartInfoRepository = shoppingCartInfoRepository;
    }

    @EventHandler
    public void on(ShoppingCartCreatedEvent event) {
        shoppingCartInfoRepository.save(
            new ShoppingCartInfo(event.getCartId(), 
                                event.getCustomerId()));
    }

    @QueryHandler
    public ShoppingCartInfo handle(ShoppingCartQuery query) {
        return shoppingCartInfoRepository.findById(query.getCartId()).orElse(null);
    }

    @QueryHandler
    public List<ShoppingCartInfo> handle(AllShoppingCartsQuery query) {
        return shoppingCartInfoRepository.findAll();
    }

    @EventHandler
    public void addProduct(ShoppingCartItemCreatedEvent event) {
        ShoppingCartInfo shoppingCart = shoppingCartInfoRepository.findById(event.getCartId()).orElse(null);
        ShoppingCartItemInfo shoppingCartItem = shoppingCart.getItems().stream()
            .filter(item -> item.getProductId().equals(event.getProductId()))
            .findFirst()
            .orElse(null);
        if (shoppingCartItem != null) {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + event.getQuantity());
        } else {
            shoppingCart.addItem(new ShoppingCartItemInfo(event.getItemId(), event.getProductId(), event.getQuantity()));
        }
        shoppingCartInfoRepository.save(shoppingCart);
    }

    @EventHandler
    public void deleteProduct(ShoppingCartItemDeletedEvent event) {
        ShoppingCartInfo shoppingCart = shoppingCartInfoRepository.findById(event.getCartId()).orElse(null);
        ShoppingCartItemInfo shoppingCartItem = shoppingCart.getItems().stream()
            .filter(item -> item.getProductId().equals(event.getProductId()))
            .findFirst()
            .orElse(null);
        if (event.getQuantity() == 0 || event.getQuantity() >= shoppingCartItem.getQuantity()) {
            shoppingCart.removeItem(shoppingCartItem);
        } else {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() - event.getQuantity());
        }
        shoppingCartInfoRepository.save(shoppingCart);
    }
    
}
