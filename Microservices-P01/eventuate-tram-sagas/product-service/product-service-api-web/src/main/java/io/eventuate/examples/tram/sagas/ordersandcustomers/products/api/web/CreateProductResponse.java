package io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.web;


public class CreateProductResponse {
  private Long productId;

  public CreateProductResponse() {
  }

  public CreateProductResponse(Long productId) {
    this.productId = productId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }
}
