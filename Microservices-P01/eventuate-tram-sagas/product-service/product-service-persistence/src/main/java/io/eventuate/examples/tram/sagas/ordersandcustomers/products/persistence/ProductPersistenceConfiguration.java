package io.eventuate.examples.tram.sagas.ordersandcustomers.products.persistence;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ProductRepository.class})
@EntityScan(basePackageClasses = {Product.class})
public class ProductPersistenceConfiguration {
}
