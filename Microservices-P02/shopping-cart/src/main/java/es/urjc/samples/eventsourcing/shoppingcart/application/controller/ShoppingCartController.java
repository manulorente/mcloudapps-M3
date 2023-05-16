package es.urjc.samples.eventsourcing.shoppingcart.application.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.AddItemCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.command.DeleteProductCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllShoppingCartsQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.ShoppingCartQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartItemInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.repository.ShoppingCartInfoRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final CommandGateway commandGateway;
    
    private final QueryGateway queryGateway;

    public ShoppingCartController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public CompletableFuture<List<ShoppingCartInfo>> listAllCarts(){
        return queryGateway.query(
            new AllShoppingCartsQuery(), 
            ResponseTypes.multipleInstancesOf(ShoppingCartInfo.class));
    }

    @GetMapping("/{cartId}")
    public CompletableFuture<ShoppingCartInfo> getCart(@PathVariable String cartId) {
        return queryGateway.query(
            new ShoppingCartQuery(cartId),
            ResponseTypes.instanceOf(ShoppingCartInfo.class));
    }

    @PostMapping("/{cartId}/product/{productId}")
    public CompletableFuture<ShoppingCartInfo> addItem(@PathVariable String cartId, @PathVariable String productId, @RequestParam int quantity) {
        return commandGateway.send(
            new AddItemCommand(cartId, productId, quantity));

            /* 
        ShoppingCartInfo shoppingCart = getShoppingCart(cartId);
        Optional<CartItemInfo> cartItem = getCartItem(shoppingCart, productId);

        cartItem.ifPresentOrElse(
                    item -> item.setQuantity(item.getQuantity() + quantity),
                    () -> shoppingCart.addItem(new CartItemInfo(productId, quantity))
                );

        repository.save(shoppingCart);

        return shoppingCart.getCartId();
        */
    }


    @DeleteMapping("/{cartId}/product/{productId}")
    public CompletableFuture<ShoppingCartInfo> removeItem(@PathVariable String cartId, @PathVariable String productId, @RequestParam int quantity) {
        return commandGateway.send(
            new DeleteProductCommand(cartId, productId));
            /* 
        ShoppingCartInfo shoppingCart = getShoppingCart(cartId);
        CartItemInfo cartItem = getCartItem(shoppingCart, productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + productId + " in cart " + cartId));

        if (quantity == 0 || quantity >= cartItem.getQuantity()) { //remove the product from cart
            shoppingCart.getItems().remove(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
        }
        
        repository.save(shoppingCart);

        return shoppingCart.getCartId();
        */
    }

    /* 
    private ShoppingCartInfo getShoppingCart(String cartId) {
        ShoppingCartInfo shoppingCart = repository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart " + cartId));
        return shoppingCart;
    }

    private Optional<CartItemInfo> getCartItem(ShoppingCartInfo shoppingCart, String productId) {
        Optional<CartItemInfo> cartItem = shoppingCart.getItems()
                .stream()
                .filter(item -> item.getProductId().equalsIgnoreCase(productId))
                .findFirst();
        return cartItem;
    }
    */

}
