package es.codeurjc.deliveryservice.stream.kafka;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import es.codeurjc.deliveryservice.model.events.AllocateDeliveryRequest;
import es.codeurjc.deliveryservice.model.events.AllocateDeliveryResult;
import es.codeurjc.deliveryservice.model.events.DeallocateDeliveryRequest;
import es.codeurjc.deliveryservice.model.events.dto.OrderDTO;
import es.codeurjc.deliveryservice.service.AllocationDeliveryService;

@Component
@Transactional
public class DeliveryStreamListener {
	
	private Logger log = LoggerFactory.getLogger(DeliveryStreamListener.class);
	private final AllocationDeliveryService allocationDeliveryService;
	private final DeliveryStreamService deliveryStreamService;
	
	@Autowired
	public DeliveryStreamListener(AllocationDeliveryService allocationDeliveryService, DeliveryStreamService deliveryStreamService) {
		this.allocationDeliveryService = allocationDeliveryService;
		this.deliveryStreamService = deliveryStreamService;
	}
	
	@StreamListener(DeliveryStream.INPUT_ALLOCATE_DELIVERY_ORDER)
	public void handleAllocateDeliveryRequest(@Payload AllocateDeliveryRequest allocateDeliveryRequest) {
		final OrderDTO orderDTO =  allocateDeliveryRequest.getOrder();
		final Boolean result = allocationDeliveryService.allocateDeliveryOrder(orderDTO);
		final AllocateDeliveryResult allocateDeliveryResult = new AllocateDeliveryResult.Builder().withIsValid(result).withOrderId(orderDTO.getId()).withReason(Boolean.FALSE.equals(result) ? "INSUFFICIENT_LOGISTIC":null).build();
		deliveryStreamService.sendDeliveryAllocateResult(allocateDeliveryResult);
	}
	
	@StreamListener(DeliveryStream.INPUT_DEALLOCATE_DELIVERY_ORDER)
    public void handleDeallocateDeliveryRequest(@Payload DeallocateDeliveryRequest deallocateDeliveryRequest) {
		final OrderDTO orderDTO =  deallocateDeliveryRequest.getOrder();
		allocationDeliveryService.deallocateDeliveryOrder(orderDTO);
	}
}