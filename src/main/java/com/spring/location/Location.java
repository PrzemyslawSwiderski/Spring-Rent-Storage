package com.spring.location;

import com.spring.storage.Storage;

import javax.persistence.*;
import java.time.Instant;

@SuppressWarnings("serial")
@Entity
@Table(name = "location")
public class Location implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "location_id", unique = true, nullable = false)
    private Long id;

    private String city;

    private String street;

    private String country;

    @Column(precision = 6)
    private Float latitude;

    @Column(precision = 6)
    private Float longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    private Instant created = Instant.now();

    public Instant getCreated() {
        return created;
    }

    public Long getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
