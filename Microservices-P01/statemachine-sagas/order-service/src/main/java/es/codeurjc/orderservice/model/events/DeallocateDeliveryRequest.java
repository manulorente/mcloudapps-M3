package es.codeurjc.orderservice.model.events;

import es.codeurjc.orderservice.model.events.dto.OrderDto;

public class DeallocateDeliveryRequest {

    private OrderDto order;
    
    public OrderDto getOrder() {
        return order;
    }

    public static final class Builder {

        private final DeallocateDeliveryRequest object;

        public Builder() {
            object = new DeallocateDeliveryRequest();
        }

        public Builder withOrder(OrderDto value) {
            object.order = value;
            return this;
        }

        public DeallocateDeliveryRequest build() {
            return object;
        }

    }
}