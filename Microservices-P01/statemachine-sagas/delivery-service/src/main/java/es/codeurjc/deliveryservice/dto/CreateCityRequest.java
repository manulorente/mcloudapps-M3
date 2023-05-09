package es.codeurjc.deliveryservice.dto;

public class CreateCityRequest {
	
    private String name;
    private String codCity;
    private Integer deliveryQuantity;
    
    public CreateCityRequest() {
    }
    
	public CreateCityRequest(String name, String codCity, Integer deliveryQuantity) {
		this.name = name;
		this.codCity = codCity;
		this.deliveryQuantity = deliveryQuantity;
	}

	public String getName() {
		return name;
	}
	public String getCodCity() {
		return codCity;
	}
	public Integer getDeliveryQuantity() {
		return deliveryQuantity;
	}
    
    
}