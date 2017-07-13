package com.spring.storage;

import com.spring.account.Account;
import com.spring.location.Location;
import com.spring.utils.IBuilder;
import java.math.BigDecimal;

public interface IStorageBuilder extends IBuilder<Storage> {

  IStorageBuilder setLocation(Location location);

  IStorageBuilder setAccount(Account account);

  IStorageBuilder setFreeSpace(BigDecimal freeSpace);

  IStorageBuilder setOverallSpace(BigDecimal overallSpace);

  IStorageBuilder setDescription(String description);

  IStorageBuilder setPrice(BigDecimal price);
}
