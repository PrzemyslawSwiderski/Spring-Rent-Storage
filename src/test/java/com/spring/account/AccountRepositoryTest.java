package com.spring.account;

import com.spring.config.WebAppConfigurationAware;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AccountRepositoryTest extends WebAppConfigurationAware {

    @Autowired
    private AccountRepository accountRepository;

    private final int howManyAccountsShouldBeGenerated = 20;

    @Before
    public void createNewAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 1; i <= howManyAccountsShouldBeGenerated; i++) {
            accounts.add(AccountBuilder.anAccount().generateExample());
        }
        log.debug(String.format("Accounts after creation: %s", Arrays.toString(accounts.toArray())));
        accountRepository.save(accounts);
    }

    @Test
    public void shouldGetAllAccounts() {
        List<Account> returnedAccounts = accountRepository.findAll();
        log.debug(String.format("Accounts found: %s", Arrays.toString(returnedAccounts.toArray())));
        assertThat(returnedAccounts.size()).isEqualTo(howManyAccountsShouldBeGenerated);
    }

    @After
    public void deleteAllNewAccounts() {
        accountRepository.deleteAll();
        List<Account> returnedAccounts = accountRepository.findAll();
        log.debug(String.format("Accounts after deletion: %s", Arrays.toString(returnedAccounts.toArray())));
        assertThat(returnedAccounts.size()).isEqualTo(0);
    }

}