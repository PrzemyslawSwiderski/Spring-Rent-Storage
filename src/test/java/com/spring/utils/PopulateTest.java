package com.spring.utils;

import com.spring.account.Account;
import com.spring.account.AccountBuilder;
import com.spring.account.AccountRepository;
import com.spring.config.WebAppConfigurationAware;
import com.spring.location.Location;
import com.spring.location.LocationBuilder;
import com.spring.location.LocationRepository;
import com.spring.storage.Storage;
import com.spring.storage.StorageBuilder;
import com.spring.storage.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PopulateTest extends WebAppConfigurationAware {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private LocationRepository locationsRepository;

    private final int howManyAccountsShouldBeGenerated = 20;

    private Random rand = new Random();

    private void addStoragesWithLocationsToAccount(Account account) {
        int howManyMaximumStoragesShouldBeGenerated = 5;
        for (int j = 1; j <= rand.nextInt(howManyMaximumStoragesShouldBeGenerated); j++) {
            Storage storage = StorageBuilder.aStorage().generateExample();
            Location location = LocationBuilder.aLocation().generateExample();
            storage.setLocation(location);
            location.setStorage(storage);
            storage.setAccount(account);
            account.getStorages().add(storage);
        }
    }

    private List<Account> generateAccountsWithStoragesAndLocations() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 1; i <= howManyAccountsShouldBeGenerated; i++) {
            Account account = AccountBuilder.anAccount().generateExample();
            addStoragesWithLocationsToAccount(account);
            accounts.add(account);
        }
        return accounts;
    }


    @Before
    public void seedData() {
        List<Account> accounts = generateAccountsWithStoragesAndLocations();
        accountRepository.save(accounts);
    }

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

    @After
    public void deleteAllEntities() {
        storageRepository.deleteAll();
        accountRepository.deleteAll();
        locationsRepository.deleteAll();
    }

}
