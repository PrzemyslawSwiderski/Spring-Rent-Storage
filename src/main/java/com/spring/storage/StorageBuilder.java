package com.spring.storage;

import com.spring.account.Account;
import com.spring.location.Location;

public final class StorageBuilder implements IStorageBuilder {
    private Location location;
    private Float freeSpace;
    private Float overallSpace;
    private String description;
    private Float price;
    private Account account;

    private StorageBuilder() {
    }

    public static StorageBuilder aStorage() {
        return new StorageBuilder();
    }

    public StorageBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public StorageBuilder setFreeSpace(Float freeSpace) {
        this.freeSpace = freeSpace;
        return this;
    }

    public StorageBuilder setOverallSpace(Float overallSpace) {
        this.overallSpace = overallSpace;
        return this;
    }

    public StorageBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public StorageBuilder setPrice(Float price) {
        this.price = price;
        return this;
    }

    public StorageBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Storage build() {
        Storage storage = new Storage();
        storage.setLocation(location);
        storage.setFreeSpace(freeSpace);
        storage.setOverallSpace(overallSpace);
        storage.setDescription(description);
        storage.setPrice(price);
        storage.setAccount(account);
        return storage;
    }
}
