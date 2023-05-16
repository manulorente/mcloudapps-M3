package es.urjc.samples.eventsourcing.shoppingcart.application.query;

public class ProductQuery {

    String productId;

    public ProductQuery() {
    }

    public ProductQuery(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
    
}
