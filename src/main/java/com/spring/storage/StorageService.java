package com.spring.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;


    public StorageRepository getStorageRepository() {
        return storageRepository;
    }

}
