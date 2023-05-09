package es.codeurjc.deliveryservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import es.codeurjc.deliveryservice.stream.kafka.DeliveryStream;

@EnableBinding(DeliveryStream.class)
public class StreamsConfig {

}
