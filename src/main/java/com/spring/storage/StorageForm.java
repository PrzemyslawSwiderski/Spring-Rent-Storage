package com.spring.storage;

import com.spring.location.Location;
import com.spring.location.LocationBuilder;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class StorageForm {

  private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
  private static final String EMAIL_MESSAGE = "{email.message}";

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String freeSpace;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String overallSpace;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String description;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String price;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String city;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String street;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String country;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String latitude;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String longitude;

  public Storage createStorage() {
    Storage storage = StorageBuilder.aStorage().setFreeSpace(new BigDecimal(freeSpace))
        .setOverallSpace(new BigDecimal(overallSpace))
        .setDescription(description)
        .setPrice(new BigDecimal(price)).build();
    Location location = LocationBuilder.aLocation().setCity(city)
        .setStreet(street)
        .setCountry(country)
        .setLatitude(new BigDecimal(latitude))
        .setLongitude(new BigDecimal(longitude)).build();
    storage.setLocation(location);
    location.setStorage(storage);
    return storage;
  }
}

