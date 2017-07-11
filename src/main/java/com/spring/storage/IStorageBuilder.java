package com.spring.storage;

import com.spring.account.Account;
import com.spring.location.Location;
import com.spring.utils.IBuilder;

public interface IStorageBuilder extends IBuilder<Storage> {

    IStorageBuilder setLocation(Location location);

    IStorageBuilder setAccount(Account account);

    IStorageBuilder setFreeSpace(Float freeSpace);

    IStorageBuilder setOverallSpace(Float overallSpace);

    IStorageBuilder setDescription(String description);

    IStorageBuilder setPrice(Float price);
}
