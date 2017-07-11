package com.spring.location;

import com.spring.storage.Storage;
import com.spring.utils.IBuilder;

public interface ILocationBuilder extends IBuilder<Location> {
    ILocationBuilder setStorage(Storage storage);

    ILocationBuilder setCity(String city);

    ILocationBuilder setStreet(String street);

    ILocationBuilder setCountry(String country);

    ILocationBuilder setLatitude(Float latitude);

    ILocationBuilder setLongitude(Float longitude);
}
