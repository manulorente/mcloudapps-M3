package io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Stock;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

@Entity
@Table(name="Product")
@Access(AccessType.FIELD)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;

  @Embedded
  private Stock stock;

  @Embedded
  private String name;

  @ElementCollection
  private Map<Long, Stock> bookedStock;

  @Version
  private Long version;

  public Product() {
  }

  public Product(String name, Stock stock, String description) {
    this.name = name;
    this.stock = stock;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Stock getStock() {
    return stock;
  }

  public void updateStock(Long orderId, Stock stock) {
    if (bookedStock == null) {
      throw new ProductStockLimitExceededException();
    } else {
      bookedStock.put(orderId, stock);
    }
  }

  public Stock availableStock() {
    return stock.subtract(bookedStock.values().stream().reduce(new Stock(), Stock::add));
  }
}
