package es.codeurjc.deliveryservice.web;

import es.codeurjc.deliveryservice.service.DeliveryService;
import es.codeurjc.deliveryservice.dto.DeliveryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class DeliveryController {

	private DeliveryService deliveryService;

	@Autowired
	public DeliveryController(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

    @GetMapping("delivery/{deliveryId}")
    public ResponseEntity<DeliveryResponse> getDelivery(@PathVariable(value = "deliveryId") UUID deliveryId) {
        return new ResponseEntity<>(deliveryService.getDelivery(deliveryId), HttpStatus.OK);
    }	
    
}