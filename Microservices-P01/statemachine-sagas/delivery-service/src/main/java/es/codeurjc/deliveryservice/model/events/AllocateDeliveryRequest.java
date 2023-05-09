package es.codeurjc.deliveryservice.model.events;

import es.codeurjc.deliveryservice.model.events.dto.OrderDTO;


public class AllocateDeliveryRequest {

    private OrderDTO order;
    
    public OrderDTO getOrder() {
        return order;
    }

    public static final class Builder {

        private final AllocateDeliveryRequest object;

        public Builder() {
            object = new AllocateDeliveryRequest();
        }

        public Builder withOrder(OrderDTO value) {
            object.order = value;
            return this;
        }

        public AllocateDeliveryRequest build() {
            return object;
        }

    }
}