package com.spring.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.spring.config.WebSecurityConfigurationAware;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Slf4j
public class UserAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

  private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

  @Autowired
  private AccountService accountService;

  @Before
  public void createNewAccounts() {
    Account account = AccountBuilder.anAccount().setEmail("user").setPassword("demo")
        .setFirstName("TEST").setLastName("Last").build();
    log.debug(String.format("Accounts after creation: %s", account));
    accountService.save(account);
  }

  @Test
  public void requiresAuthentication() throws Exception {
    mockMvc.perform(get("/account/current"))
        .andExpect(redirectedUrl("http://localhost/signin"));
  }

  @Test
  public void userAuthenticates() throws Exception {
    final String username = "user";

    mockMvc.perform(post("/authenticate").param("username", username).param("password", "demo"))
        .andExpect(redirectedUrl("/"))
        .andExpect(r -> Assert.assertEquals(
            ((SecurityContext) r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR))
                .getAuthentication().getName(), username));

  }

  @Test
  public void userAuthenticationFails() throws Exception {
    final String username = "user";
    mockMvc.perform(post("/authenticate").param("username", username).param("password", "invalid"))
        .andExpect(redirectedUrl("/signin?error=1"))
        .andExpect(
            r -> Assert.assertNull(r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)));
  }


  @After
  public void deleteNewAccount() {
    accountService.getAccountRepository().deleteAll();
    List<Account> returnedAccounts = accountService.getAccountRepository().findAll();
    log.debug(
        String.format("Accounts after deletion: %s", Arrays.toString(returnedAccounts.toArray())));
    assertThat(returnedAccounts.size()).isEqualTo(0);
  }
}
