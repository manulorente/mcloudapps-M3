package es.codeurjc.orderservice.model.events.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.codeurjc.orderservice.types.OrderStatusEnum;

public class OrderDto {
	
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
    private OrderStatusEnum state;
    
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

    public String getCodCity() {return codCity;}

    public OrderStatusEnum getState() {
        return state;
    }

    public static final class Builder {

        private final OrderDto object;

        public Builder() {
            object = new OrderDto();
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

        public Builder withState(OrderStatusEnum value) {
            object.state = value;
            return this;
        }

        public OrderDto build() {
            return object;
        }

    }
}