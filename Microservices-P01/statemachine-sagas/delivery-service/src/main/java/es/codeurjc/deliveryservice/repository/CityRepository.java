package es.codeurjc.deliveryservice.repository;

import es.codeurjc.deliveryservice.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City,UUID>{

	Optional<City> findByCodCity(@Param("codCity") String codCity);
}