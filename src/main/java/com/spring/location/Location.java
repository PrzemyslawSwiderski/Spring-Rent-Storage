package com.spring.location;

import com.spring.storage.Storage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@SuppressWarnings("serial")
@Entity
@Table(name = "location")
public class Location implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "location_id", unique = true, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String city;

    private String street;

    private String country;

    @Column(scale = 4)
    private BigDecimal latitude;

    @Column(scale = 4)
    private BigDecimal longitude;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    private Instant created = Instant.now();

    public String toString() {
        return String.format("com.spring.location.Location(id=%d, city=%s, street=%s, country=%s, latitude=%s, longitude=%s, created=%s)", this.getId(), this.getCity(), this.getStreet(), this.getCountry(), this.getLatitude(), this.getLongitude(), this.getCreated());
    }
}
