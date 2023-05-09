package es.codeurjc.deliveryservice.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DeliveryStream {

	public String INPUT_ALLOCATE_DELIVERY_ORDER = "allocate-delivery-order-request";
    public String OUTPUT_ALLOCATE_DELIVERY_ORDER = "allocate-delivery-order-response";
    public String INPUT_DEALLOCATE_DELIVERY_ORDER = "deallocate-delivery-order-request";
    
    @Input(INPUT_ALLOCATE_DELIVERY_ORDER)
    SubscribableChannel inboundAllocateDeliveryOrder();

    @Output(OUTPUT_ALLOCATE_DELIVERY_ORDER)
    MessageChannel outboundAllocateDeliveryOrder();
    
    @Output(INPUT_DEALLOCATE_DELIVERY_ORDER)
    SubscribableChannel inboundDeallocateDeliveryOrder();
}