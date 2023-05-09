package io.eventuate.examples.tram.sagas.ordersandcustomers.products.messaging;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.messaging.commands.ReserveCreditCommand;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.messaging.replies.ProductCreditLimitExceeded;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.messaging.replies.ProductCreditReserved;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.messaging.replies.ProductNotFound;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductCreditLimitExceededException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductService;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class ProductCommandHandler {

  private ProductService productService;

  public ProductCommandHandler(ProductService productService) {
    this.productService = productService;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("productService")
            .onMessage(ReserveStockCommand.class, this::updateStock)
            .build();
  }

  public Message updateStock(CommandMessage<ReserveStockCommand> cm) {
    ReserveStockCommand cmd = cm.getCommand();
    try {
      productService.updateStock(cmd.getProductId(), cmd.getOrderId(), cmd.getStock());
      return withSuccess(new ProductStockReserved());
    } catch (ProductNotFoundException e) {
      return withFailure(new ProductNotFound());
    } catch (ProductStockLimitExceededException e) {
      return withFailure(new ProductStockLimitExceeded());
    }
  }

}
