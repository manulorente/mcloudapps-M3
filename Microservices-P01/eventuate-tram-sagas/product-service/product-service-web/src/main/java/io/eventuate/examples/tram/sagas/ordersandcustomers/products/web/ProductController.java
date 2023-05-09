package io.eventuate.examples.tram.sagas.ordersandcustomers.products.web;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.web.CreateProductRequest;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.web.CreateProductResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.web.GetProductResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ProductController {

  private ProductService productService;
  private ProductRepository productRepository;

  @Autowired
  public ProductController(ProductService productService, ProductRepository productRepository) {
    this.productService = productService;
    this.productRepository = productRepository;
  }

  @RequestMapping(value = "/products", method = RequestMethod.POST)
  public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
    Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getStock(), createProductRequest.getDescription());
    return new CreateProductResponse(product.getId());
  }

  @RequestMapping(value="/products", method= RequestMethod.GET)
  public ResponseEntity<GetProductsResponse> getAll() {
    return ResponseEntity.ok(new GetProductResponse(StreamSupport.stream(productRepository.findAll().spliterator(), false)
            .map(p -> new GetProductResponse(p.getId(), p.getName(), p.getDescription(), p.getStock())).collect(Collectors.toList())));
  }

  @RequestMapping(value="/products/{productId}", method= RequestMethod.GET)
  public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
    return productRepository
            .findById(productId)
            .map(p -> new ResponseEntity<>(new GetProductResponse(p.getId(), p.getName(), p.getDescription(), p.getStock()), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
