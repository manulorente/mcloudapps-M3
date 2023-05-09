package io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Stock;

import javax.transaction.Transactional;

public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public Product createProduct(String name, Stock stock, String description) {
    Product product  = new Product(name, stock, description);
    return productRepository.save(product);
  }

  public Product updateStock(long productId, Long orderId, Stock stock) throws ProductStockLimitExceededException {
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    product.updateStock(orderId, stock);
    return productRepository.save(product);
  }
}
