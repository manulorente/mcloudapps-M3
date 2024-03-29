package es.codeurjc.deliveryservice.model.events.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {
	
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("reference")
    private String reference;

    @JsonProperty("codCity")
    private String codCity;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonIgnore
    private String state;
    
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public String getReference() {
		return reference;
	}

    public String getCodCity() {return codCity; }

    public String getState() {
        return state;
    }

    public static final class Builder {

        private final OrderDTO object;

        public Builder() {
            object = new OrderDTO();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }
        
        public Builder withName(String value) {
            object.name = value;
            return this;
        }

        public Builder withReference(String value) {
            object.reference = value;
            return this;
        }

        public Builder withCodCity(String value) {
            object.codCity = value;
            return this;
        }
        
        public Builder withQuantity(Integer value) {
            object.quantity = value;
            return this;
        }

        public Builder withState(String value) {
            object.state = value;
            return this;
        }

        public OrderDTO build() {
            return object;
        }

    }
}