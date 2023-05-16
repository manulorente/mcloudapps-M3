package es.urjc.samples.eventsourcing.shoppingcart.application.projection;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import es.urjc.samples.eventsourcing.shoppingcart.application.query.AllProductsQuery;
import es.urjc.samples.eventsourcing.shoppingcart.application.query.ProductQuery;
import es.urjc.samples.eventsourcing.shoppingcart.domain.event.ProductCreatedEvent;
import es.urjc.samples.eventsourcing.shoppingcart.domain.model.ProductInfo;
import es.urjc.samples.eventsourcing.shoppingcart.domain.repository.ProductInfoRepository;

@Component
public class ProductInfoProjection {

    private final ProductInfoRepository productInfoRepository;

    public ProductInfoProjection(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        productInfoRepository.save(
            new ProductInfo(event.getProductId(), 
                                event.getName(), 
                                event.getDescription(),
                                event.getPrice()));
    }

    @QueryHandler
    public ProductInfo handle(ProductQuery query) {
        return productInfoRepository.findById(query.getProductId()).orElse(null);
    }

    @QueryHandler
    public List<ProductInfo> handle(AllProductsQuery query) {
        return productInfoRepository.findAll();
    }
    
}
