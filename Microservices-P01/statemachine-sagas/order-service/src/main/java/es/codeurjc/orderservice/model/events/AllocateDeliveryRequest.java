package es.codeurjc.orderservice.model.events;

import es.codeurjc.orderservice.model.events.dto.OrderDto;

public class AllocateDeliveryRequest {

    private OrderDto order;
    
    public OrderDto getOrder() {
        return order;
    }

    public static final class Builder {

        private final AllocateDeliveryRequest object;

        public Builder() {
            object = new AllocateDeliveryRequest();
        }

        public Builder withOrder(OrderDto value) {
            object.order = value;
            return this;
        }

        public AllocateDeliveryRequest build() {
            return object;
        }

    }
}