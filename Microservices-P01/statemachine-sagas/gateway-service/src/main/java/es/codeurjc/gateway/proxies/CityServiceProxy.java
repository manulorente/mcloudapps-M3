package es.codeurjc.gateway.proxies;

import es.codeurjc.gateway.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CityServiceProxy {

	private final WebClient client;


	@Value("#{'${product.url:http://localhost:8084}'}")
	private String cityServiceUrl;

	@Autowired
	public CityServiceProxy(WebClient client) {
		this.client = client;
	}

	public Mono<CityInfo> findCityById(String cityId) {
		Mono<ClientResponse> response = client.get()
				.uri(cityServiceUrl + "/api/v1/city/{cityId}", cityId).exchange();
		return response.flatMap(resp -> {
			switch (resp.statusCode()) {
			case OK:
				return resp.bodyToMono(CityInfo.class);
			case NOT_FOUND:
				return Mono.error(new EntityNotFoundException());
			default:
				return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
			}
		});
	}
}