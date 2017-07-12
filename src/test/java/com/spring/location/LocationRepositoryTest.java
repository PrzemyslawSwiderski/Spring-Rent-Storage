package com.spring.location;

import com.spring.config.WebAppConfigurationAware;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LocationRepositoryTest extends WebAppConfigurationAware {


    @Autowired
    private LocationRepository locationsRepository;


    @After
    public void deleteAllNewLocations() {
        List<Location> returnedLocations = locationsRepository.findAll();
        log.debug(String.format("Locations before deletion: %s", Arrays.toString(returnedLocations.toArray())));
        locationsRepository.deleteAll();
        returnedLocations = locationsRepository.findAll();
        log.debug(String.format("Locations after deletion: %s", Arrays.toString(returnedLocations.toArray())));
        assertThat(returnedLocations.size()).isEqualTo(0);
    }


    @Before
    public void createNewLocations() {
        Location location = LocationBuilder.aLocation().generateExample();
        Location returnedLocation = locationsRepository.save(location);
        assertThat(returnedLocation.getCity()).isEqualTo(location.getCity());
        assertThat(returnedLocation.getCountry()).isEqualTo(location.getCountry());
        assertThat(returnedLocation.getCreated()).isEqualTo(location.getCreated());

        Location location2 = LocationBuilder.aLocation().generateExample();
        Location returnedLocation2 = locationsRepository.save(location2);
        assertThat(returnedLocation2.getCity()).isEqualTo(location2.getCity());
        assertThat(returnedLocation2.getCountry()).isEqualTo(location2.getCountry());
        assertThat(returnedLocation2.getCreated()).isEqualTo(location2.getCreated());

    }

    @Test
    public void shouldReturnLocations() {
        List<Location> returnedLocations = locationsRepository.findAll();
        log.debug(String.format("All locations: %s", Arrays.toString(returnedLocations.toArray())));
        assertThat(returnedLocations.size()).isGreaterThanOrEqualTo(2);
    }


}