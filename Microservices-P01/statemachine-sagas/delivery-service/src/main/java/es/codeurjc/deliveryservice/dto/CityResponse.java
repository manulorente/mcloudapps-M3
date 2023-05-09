package es.codeurjc.deliveryservice.dto;

import java.util.UUID;

public class CityResponse {
	
	private UUID id;
    private String name;
    private String codCity;
    private Integer deliveryQuantity;
    
    public UUID getId() {
        return id;
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

    public static final class Builder {

        private final CityResponse object;

        public Builder() {
            object = new CityResponse();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withName(String value) {
            object.name = value;
            return this;
        }

        public Builder withCodCity(String value) {
            object.codCity = value;
            return this;
        }

        public Builder withDeliveryQuantity(Integer value) {
            object.deliveryQuantity = value;
            return this;
        }

        public CityResponse build() {
            return object;
        }

    }
    
    
}