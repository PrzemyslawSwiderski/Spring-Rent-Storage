package com.spring.storage;

import com.spring.account.Account;
import com.spring.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService {

  @Autowired
  private StorageRepository storageRepository;

  @Autowired
  private AccountService accountService;

  public StorageRepository getStorageRepository() {
    return storageRepository;
  }

  @Transactional
  public Storage publishStorageSpace(StorageForm storageForm) {

    Account currentAccount = accountService.getCurrentAccount();
    Storage storage = storageForm.createStorage();
    storage.setAccount(currentAccount);
    currentAccount.addStorage(storage);

    storageRepository.save(storage);
    return storage;
  }
}
