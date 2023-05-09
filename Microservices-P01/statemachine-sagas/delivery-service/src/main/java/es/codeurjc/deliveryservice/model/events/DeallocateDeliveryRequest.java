package es.codeurjc.deliveryservice.model.events;

import es.codeurjc.deliveryservice.model.events.dto.OrderDTO;


public class DeallocateDeliveryRequest {

    private OrderDTO order;
    
    public OrderDTO getOrder() {
        return order;
    }

    public static final class Builder {

        private final DeallocateDeliveryRequest object;

        public Builder() {
            object = new DeallocateDeliveryRequest();
        }

        public Builder withOrder(OrderDTO value) {
            object.order = value;
            return this;
        }

        public DeallocateDeliveryRequest build() {
            return object;
        }

    }
}