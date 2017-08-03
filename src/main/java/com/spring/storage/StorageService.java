package com.spring.storage;

import com.spring.account.Account;
import com.spring.account.AccountService;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService {

  @Autowired
  private StorageRepository storageRepository;

  @Autowired
  private AccountService accountService;

  private void connectStorageToCurrentAccountAndSave(Storage storage) {
    Account currentAccount = accountService.getCurrentAccount();
    storage.setAccount(currentAccount);
    currentAccount.addStorage(storage);
    storageRepository.save(storage);
  }

  @Transactional
  public Storage saveStorageSpace(StorageForm storageForm) {
    Storage storage = storageForm.createStorage();
    connectStorageToCurrentAccountAndSave(storage);
    return storage;
  }

  @Transactional
  public List<Storage> getAllStorages() {
    List<Storage> storagesList;
    storagesList = storageRepository.findAll();
    return storagesList;
  }


  @Transactional
  public List<Storage> getSortedStorages(String fieldName, String order) {
    if (fieldName == null || order == null) {
      return getAllStorages();
    }
    Direction direction = order.equals("asc") ? Direction.ASC : Direction.DESC;
    List<Storage> storagesList;
    storagesList = storageRepository.findAll(new Sort(new Order(direction, fieldName)));
    return storagesList;
  }

  @Transactional
  public void deleteStorageByID(Long id) {
    storageRepository.delete(id);
  }

  @Transactional
  public List<Storage> getAllUserStorages() {
    List<Storage> storagesList;
    Account currentAccount = accountService.getCurrentAccount();
    Hibernate.initialize(currentAccount.getStorages());
    storagesList = currentAccount.getStorages();
    return storagesList;
  }

  @Transactional
  public Storage getStorageById(Long id) {
    Storage storage;
    storage = storageRepository.findOne(id);
    return storage;
  }
}
