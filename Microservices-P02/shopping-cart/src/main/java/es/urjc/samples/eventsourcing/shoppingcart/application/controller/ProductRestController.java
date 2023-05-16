package es.urjc.samples.eventsourcing.shoppingcart.application.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateProductCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllProductsQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.ProductQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ProductInfo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final CommandGateway commandGateway;
    
    private final QueryGateway queryGateway;

    public ProductRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<Void> addProduct(@RequestBody ProductInfo product) {
        return commandGateway.send(
                new CreateProductCommand(
                    product.getProductId() != null ? product.getProductId():UUID.randomUUID().toString(), 
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()));
    }

    @GetMapping
    public CompletableFuture<List<ProductInfo>> listProducts() {
        return queryGateway.query(
            new AllProductsQuery(), 
            ResponseTypes.multipleInstancesOf(ProductInfo.class));
    }

    @GetMapping("/{productId}")
    public CompletableFuture<ProductInfo> getProduct(@PathVariable String productId) {
        return queryGateway.query(
            new ProductQuery(productId),
            ResponseTypes.instanceOf(ProductInfo.class));
    }


}
