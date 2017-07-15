package com.spring.storage;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.account.Account;
import com.spring.account.AccountBuilder;
import com.spring.account.AccountRepository;
import com.spring.config.WebAppConfigurationAware;
import java.util.Arrays;
import java.util.List;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class StorageRepositoryTest extends WebAppConfigurationAware {


  @Autowired
  private StorageRepository storageRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Before
  public void createNewStorages() {
    Account account = AccountBuilder.anAccount().generateExample();
    Storage storage = StorageBuilder.aStorage().generateExample();
    storage.setAccount(account);
    storageRepository.save(storage);
    Account account2 = AccountBuilder.anAccount().generateExample();
    Storage storage2 = StorageBuilder.aStorage().generateExample();
    storage2.setAccount(account2);
    storageRepository.save(storage2);
  }

  @After
  public void deleteAllNewStorages() {
    List<Storage> returnedStorages = storageRepository.findAll();
    log.debug(
        String.format("Storages before deletion: %s", Arrays.toString(returnedStorages.toArray())));
    storageRepository.deleteAll();
    returnedStorages = storageRepository.findAll();
    log.debug(
        String.format("Storages after deletion: %s", Arrays.toString(returnedStorages.toArray())));
    assertThat(returnedStorages.size()).isEqualTo(0);
  }

  @After
  public void deleteAllNewAccounts() {
    List<Account> returnedAccounts = accountRepository.findAll();
    log.debug(
        String.format("Accounts before deletion: %s", Arrays.toString(returnedAccounts.toArray())));
    accountRepository.deleteAll();
    returnedAccounts = accountRepository.findAll();
    log.debug(
        String.format("Accounts after deletion: %s", Arrays.toString(returnedAccounts.toArray())));
    assertThat(returnedAccounts.size()).isEqualTo(0);
  }

  @Test
  public void shouldReturnStorages() {
    List<Storage> returnedStorages = storageRepository.findAll();
    log.debug(String.format("All storages: %s", Arrays.toString(returnedStorages.toArray())));
    assertThat(returnedStorages.size()).isGreaterThanOrEqualTo(2);
  }

}