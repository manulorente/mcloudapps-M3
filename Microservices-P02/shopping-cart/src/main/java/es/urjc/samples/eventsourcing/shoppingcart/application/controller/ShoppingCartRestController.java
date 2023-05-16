package es.urjc.samples.eventsourcing.shoppingcart.application.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.AddItemCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.command.DeleteItemCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllShoppingCartsQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.ShoppingCartQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartItemInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ShoppingCartInfo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/carts")
public class ShoppingCartRestController {

    private final CommandGateway commandGateway;
    
    private final QueryGateway queryGateway;

    public ShoppingCartRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
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
    public CompletableFuture<ShoppingCartItemInfo> addItem(@PathVariable String cartId, @PathVariable String productId, @RequestParam("quantity") int quantity) {
        return commandGateway.send(
            new AddItemCommand(
                UUID.randomUUID().toString(),
                cartId, 
                productId, 
                quantity));
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public CompletableFuture<ShoppingCartInfo> removeItem(@PathVariable String cartId, @PathVariable String productId, @RequestParam("quantity") int quantity) {
        return commandGateway.send(
            new DeleteItemCommand(
                    UUID.randomUUID().toString(),
                    cartId, 
                    productId,
                    quantity));
    }

    @GetMapping("/{cartId}/removed-items")
    public CompletableFuture<ShoppingCartInfo> getRemovedItems(@PathVariable String cartId) {
        return queryGateway.query(
            new ShoppingCartQuery(cartId),
            ResponseTypes.instanceOf(ShoppingCartInfo.class));
    }

}
