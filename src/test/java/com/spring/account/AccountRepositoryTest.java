package com.spring.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.config.WebAppConfigurationAware;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class AccountRepositoryTest extends WebAppConfigurationAware {

  @Value("${test.config.database.howManyAccountsShouldBeGenerated}")
  private int howManyAccountsShouldBeGenerated;

  @Autowired
  private AccountRepository accountRepository;

  @Test
  public void shouldGetAllAccounts() {
    List<Account> returnedAccounts = accountRepository.findAll();
    log.debug(String.format("Accounts found: %s", Arrays.toString(returnedAccounts.toArray())));
    assertThat(returnedAccounts.size()).isEqualTo(howManyAccountsShouldBeGenerated);
  }

}