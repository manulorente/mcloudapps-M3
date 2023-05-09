package es.codeurjc.deliveryservice.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import es.codeurjc.deliveryservice.model.events.AllocateDeliveryResult;

@Service
public class DeliveryStreamService {
	
	private Logger log = LoggerFactory.getLogger(DeliveryStreamService.class);
	
	private final DeliveryStream deliveryStream;
	
	@Autowired
	public DeliveryStreamService(DeliveryStream deliveryStream) {
		this.deliveryStream = deliveryStream;
	}
	
	public void sendDeliveryAllocateResult(final AllocateDeliveryResult allocateDeliveryResult) {
		log.info("Sending allocateResult {}", allocateDeliveryResult);
		MessageChannel messageChannel = deliveryStream.outboundAllocateDeliveryOrder();
        messageChannel.send(MessageBuilder
                .withPayload(allocateDeliveryResult)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}