package io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.web;

import java.util.List;

public class GetProductsResponse {

    private List<GetProductResponse> products;

    public GetProductsResponse() {
    }

    public GetProductsResponse(List<GetProductResponse> products) {
        this.products = products;
    }

    public List<GetProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<GetProductResponse> products) {
        this.products = products;
    }

}
