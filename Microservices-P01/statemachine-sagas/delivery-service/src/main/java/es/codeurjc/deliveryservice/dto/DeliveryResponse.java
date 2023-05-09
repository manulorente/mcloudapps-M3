package es.codeurjc.deliveryservice.dto;

import java.util.UUID;

public class DeliveryResponse {
	
	private UUID id;
    private String path;
    private UUID orderId;

    public UUID getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public static final class Builder {

        private final DeliveryResponse object;

        public Builder() {
            object = new DeliveryResponse();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withPath(String value) {
            object.path = value;
            return this;
        }

        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }

        public DeliveryResponse build() {
            return object;
        }

    }
    
    
}