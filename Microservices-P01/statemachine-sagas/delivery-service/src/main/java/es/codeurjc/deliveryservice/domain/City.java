package es.codeurjc.deliveryservice.domain;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "city")
@Access(AccessType.FIELD)
public class City {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;
    
    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String codCity;
	
    private Integer deliveryQuantity = 0;
    
    public UUID getId() {
        return id;
    }

	public String getName() {
		return name;
	}

	public String getCodCity() {
		return codCity;
	}

	public Integer getDeliveryQuantity() {
		return deliveryQuantity;
	}

	public void setDeliveryQuantity(Integer deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}

	public static final class Builder {

        private final City object;

        public Builder() {
            object = new City();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withName(String value) {
            object.name = value;
            return this;
        }

        public Builder withCodCity(String value) {
            object.codCity = value;
            return this;
        }
        
        public Builder withDeliveryQuantity(Integer value) {
            object.deliveryQuantity = value;
            return this;
        }

        public City build() {
            return object;
        }

    }
}