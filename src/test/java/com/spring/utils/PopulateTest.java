package com.spring.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.account.Account;
import com.spring.account.AccountRepository;
import com.spring.config.WebAppConfigurationAware;
import com.spring.location.Location;
import com.spring.location.LocationRepository;
import com.spring.storage.Storage;
import com.spring.storage.StorageRepository;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class PopulateTest extends WebAppConfigurationAware {

  @Value("${test.config.database.howManyAccountsShouldBeGenerated}")
  private int howManyAccountsShouldBeGenerated;

  @Value("${test.config.database.minStoragesToGenerate}")
  private int minStoragesToGenerate;

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private StorageRepository storageRepository;
  @Autowired
  private LocationRepository locationsRepository;


  @Test
  public void shouldGetAllEntities() {
    List<Account> foundAccounts = accountRepository.findAll();
    List<Location> foundLocations = locationsRepository.findAll();
    List<Storage> foundStorages = storageRepository.findAll();
    log.debug(String.format("All accounts : %s", Arrays.toString(foundAccounts.toArray())));
    log.debug(String.format("All locations: %s", Arrays.toString(foundLocations.toArray())));
    log.debug(String.format("All storages: %s", Arrays.toString(foundStorages.toArray())));
    assertThat(foundAccounts.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);
    assertThat(foundLocations.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);
    assertThat(foundStorages.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);

  }

  @Test
  public void everyAccountShouldContainAtLeastThreeStorages() {
    List<Account> foundAccounts = accountRepository.findAll();
    assertThat(foundAccounts)
        .allMatch(account -> account.getStorages().size() >= minStoragesToGenerate);
  }

  @Test
  public void shouldGetLocationByStorage() {
    List<Storage> foundStorages = storageRepository.findAll();
    log.debug(String.format("All storages: %s", Arrays.toString(foundStorages.toArray())));

    assertThat(foundStorages.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);
    assertThat(foundStorages).allMatch(
        storage -> storage.getLocation() != null && storage.getLocation() instanceof Location);
  }

  @Test
  public void shouldGetStorageByLocation() {
    List<Location> foundLocations = locationsRepository.findAll();
    log.debug(String.format("All locations: %s", Arrays.toString(foundLocations.toArray())));

    assertThat(foundLocations.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);
    assertThat(foundLocations).allMatch(
        location -> location.getStorage() != null && location.getStorage() instanceof Storage);
  }

  @Test
  public void shouldGetAccountByStorage() {
    List<Storage> foundStorages = storageRepository.findAll();
    log.debug(String.format("All storages: %s", Arrays.toString(foundStorages.toArray())));

    assertThat(foundStorages.size()).isGreaterThanOrEqualTo(howManyAccountsShouldBeGenerated);
    assertThat(foundStorages).allMatch(
        storage -> storage.getAccount() != null && storage.getAccount() instanceof Account);
  }


}
