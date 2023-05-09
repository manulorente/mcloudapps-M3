package es.codeurjc.deliveryservice.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "delivery")
@Access(AccessType.FIELD)
public class Delivery {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;
    
    private String path;
    
    private UUID orderId;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public static final class Builder {

        private final Delivery object;

        public Builder() {
            object = new Delivery();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withPath(String value) {
            object.path = value;
            return this;
        }

        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }
        
        public Delivery build() {
            return object;
        }

    }
}