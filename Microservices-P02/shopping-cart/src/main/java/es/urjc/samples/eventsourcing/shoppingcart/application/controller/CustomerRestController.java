package es.urjc.samples.eventsourcing.shoppingcart.application.controller;

import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateCustomerCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.command.CreateShoppingCartCommand;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllCustomersQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.CustomerQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.CustomerInfo;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CommandGateway commandGateway;
    
    private final QueryGateway queryGateway;

    public CustomerRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<Void> createCustomer(@RequestBody CustomerInfo customer) {
        return commandGateway.send(
                new CreateCustomerCommand(
                    customer.getCustomerId() != null ? customer.getCustomerId():UUID.randomUUID().toString(), 
                    customer.getFullName(),
                    customer.getAddress())
                    );  
    }

    @GetMapping
    public CompletableFuture<List<CustomerInfo>> listCustomers() {
        return queryGateway.query(
            new AllCustomersQuery(), 
            ResponseTypes.multipleInstancesOf(CustomerInfo.class));        
    }

    @GetMapping("/{customerId}")
    public CompletableFuture<CustomerInfo> getCustomer(@PathVariable String customerId) {
        return queryGateway.query(
            new CustomerQuery(customerId), 
            ResponseTypes.instanceOf(CustomerInfo.class));
    }

    @PostMapping("/{customerId}/cart")
    public CompletableFuture<Void> createCart(@PathVariable String customerId) {
        return commandGateway.send(
                new CreateShoppingCartCommand(
                    UUID.randomUUID().toString(),
                    customerId));
    }

}
