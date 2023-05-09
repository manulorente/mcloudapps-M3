package es.codeurjc.deliveryservice.service;

import java.util.UUID;

import javax.transaction.Transactional;

import es.codeurjc.deliveryservice.domain.Delivery;
import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.deliveryservice.exception.EntityNotFoundException;
import es.codeurjc.deliveryservice.repository.DeliveryRepository;

@Service
@Transactional
public class DeliveryService {
	
	private final DeliveryRepository deliveryRepository;
	
	@Autowired
	public DeliveryService(DeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}

	public DeliveryResponse getDelivery(UUID deliveryId) {
		final Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() ->new EntityNotFoundException());
		return toDeliveryResponse(delivery);
	}

	private DeliveryResponse toDeliveryResponse(final Delivery delivery) {
		return new DeliveryResponse.Builder()
        .withId(delivery.getId())
        .withPath(delivery.getPath())
        .withOrderId(delivery.getOrderId())
        .build();
	}
}