package com.spring.config;

import com.spring.account.Account;
import com.spring.account.AccountRepository;
import com.spring.location.LocationRepository;
import com.spring.storage.StorageRepository;
import com.spring.utils.AccountGenerator;
import java.util.List;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringInstanceTestClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
    ApplicationConfigTest.class
})
public abstract class WebAppConfigurationAware implements InstanceTestClassListener {

  @Value("${test.config.database.howManyAccountsShouldBeGenerated}")
  private int howManyAccountsShouldBeGenerated;

  @Value("${test.config.database.minStoragesToGenerate}")
  private int minStoragesToGenerate;

  @Value("${test.config.database.maxStoragesToGenerate}")
  private int maxStoragesToGenerate;

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private StorageRepository storageRepository;
  @Autowired
  private LocationRepository locationsRepository;

  @Inject
  protected WebApplicationContext wac;

  protected MockMvc mockMvc;

  @Override
  public void beforeClassSetup() {
    AccountGenerator accountGenerator = AccountGenerator.builder()
        .howManyAccountsShouldBeGenerated(howManyAccountsShouldBeGenerated)
        .maxStoragesToGenerate(maxStoragesToGenerate)
        .minStoragesToGenerate(minStoragesToGenerate)
        .build();
    List<Account> accounts = accountGenerator.generateMultipleEntities();
    accountRepository.save(accounts);
  }

  @Override
  public void afterClassSetup() {
    storageRepository.deleteAll();
    accountRepository.deleteAll();
    locationsRepository.deleteAll();
  }
}
