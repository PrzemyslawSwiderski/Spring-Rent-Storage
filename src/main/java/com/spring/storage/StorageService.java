package com.spring.storage;

import com.spring.account.Account;
import com.spring.account.AccountService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService {

  @Autowired
  private StorageRepository storageRepository;

  @Autowired
  private AccountService accountService;

  @Transactional
  public Storage publishStorageSpace(StorageForm storageForm) {

    Account currentAccount = accountService.getCurrentAccount();
    Storage storage = storageForm.createStorage();
    storage.setAccount(currentAccount);
    currentAccount.addStorage(storage);

    storageRepository.save(storage);
    return storage;
  }

  @Transactional
  public List<Storage> getAllStorages() {
    List<Storage> storagesList;
    storagesList = storageRepository.findAll();
    return storagesList;
  }
}
