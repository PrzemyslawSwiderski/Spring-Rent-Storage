package com.spring.utils;

import com.spring.account.Account;
import com.spring.account.AccountBuilder;
import com.spring.location.Location;
import com.spring.location.LocationBuilder;
import com.spring.storage.Storage;
import com.spring.storage.StorageBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Builder;

@Builder
public class AccountGenerator implements IDataGenerator<Account> {

  private int howManyAccountsShouldBeGenerated = 1;

  private int minStoragesToGenerate = 1;

  private int maxStoragesToGenerate = 2;


  private void addStoragesWithLocationsToAccount(Account account) {
    Random rand = new Random();
    int upperBound =
        minStoragesToGenerate + rand.nextInt(maxStoragesToGenerate - minStoragesToGenerate);
    for (int j = 1; j <= upperBound; j++) {
      Storage storage = StorageBuilder.aStorage().generateExample();
      Location location = LocationBuilder.aLocation().generateExample();
      storage.setLocation(location);
      location.setStorage(storage);
      storage.setAccount(account);
      account.getStorages().add(storage);
    }
  }

  @Override
  public Account generateSingleEntity() {
    return AccountBuilder.anAccount().generateExample();
  }

  @Override
  public List<Account> generateMultipleEntities() {
    List<Account> accounts = new ArrayList<>();
    for (int i = 1; i <= howManyAccountsShouldBeGenerated; i++) {
      Account account = AccountBuilder.anAccount().generateExample();
      addStoragesWithLocationsToAccount(account);
      accounts.add(account);
    }
    return accounts;
  }
}
