package es.codeurjc.orderservice.model.events;

import java.util.UUID;

public class AllocateDeliveryResult {
	
    private UUID orderId;
    private Boolean isValid;
    private String reason;
    
    public UUID getOrderId() {
        return orderId;
    }

    public Boolean getIsValid() {
        return isValid;
    }
    
    public String getReason() {
		return reason;
	}

    public static final class Builder {

        private final AllocateDeliveryResult object;

        public Builder() {
            object = new AllocateDeliveryResult();
        }

        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }

        public Builder withIsValid(Boolean value) {
            object.isValid = value;
            return this;
        }
        
        public Builder withReason(String value) {
            object.reason = value;
            return this;
        }
        public AllocateDeliveryResult build() {
            return object;
        }

    }
}