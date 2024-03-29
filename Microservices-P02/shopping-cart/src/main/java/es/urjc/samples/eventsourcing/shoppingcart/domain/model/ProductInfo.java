package es.urjc.samples.eventsourcing.shoppingcart.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductInfo {

    @Id
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String name, String description, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
