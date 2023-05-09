package es.codeurjc.deliveryservice.dto;

import java.util.UUID;

public class CreateDeliveryResponse {

	private UUID deliveryId;

	public CreateDeliveryResponse() {
	}

	public CreateDeliveryResponse(UUID deliveryId) {
		this.deliveryId = deliveryId;
	}

	public UUID getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(UUID deliveryId) {
		this.deliveryId = deliveryId;
	}

}