package es.codeurjc.deliveryservice.dto;

public class CreateDeliveryRequest {

    private String name;
    private String reference;
    private Integer stockQuantity;

    public CreateDeliveryRequest() {

    }

	public CreateDeliveryRequest(String name, String reference, Integer stockQuantity) {
		this.name = name;
		this.reference = reference;
		this.stockQuantity = stockQuantity;
	}

	public String getName() {
		return name;
	}
	public String getReference() {
		return reference;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
    
    
}