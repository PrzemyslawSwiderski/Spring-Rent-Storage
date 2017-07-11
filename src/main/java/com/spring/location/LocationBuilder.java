package com.spring.location;

import com.spring.storage.Storage;

public final class LocationBuilder implements ILocationBuilder{
    private String city;
    private String street;
    private String country;
    private Float latitude;
    private Float longitude;
    private Storage storage;

    private LocationBuilder() {
    }

    public static LocationBuilder aLocation() {
        return new LocationBuilder();
    }

    public LocationBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public LocationBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public LocationBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public LocationBuilder setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationBuilder setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationBuilder setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    public Location build() {
        Location location = new Location();
        location.setCity(city);
        location.setStreet(street);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setStorage(storage);
        return location;
    }
}
