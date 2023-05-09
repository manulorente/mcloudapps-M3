package io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.messaging.commands;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Stock;
import io.eventuate.tram.commands.common.Command;

public class ReserveStockCommand implements Command {
  private Long orderId;
  private Stock stock;
  private Long productId;

  public ReserveStockCommand() {
  }

  public ReserveStockCommand(Long orderId, Stock stock, Long productId) {
    this.orderId = orderId;
    this.stock = stock;
    this.productId = productId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Stock getStock() {
    return stock;
  }

  public Long getProductId() {
    return productId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Override
  public String toString() {
    return "ReserveStockCommand{" +
            "orderId=" + orderId +
            ", stock=" + stock +
            ", productId=" + productId +
            '}';
  }

}
