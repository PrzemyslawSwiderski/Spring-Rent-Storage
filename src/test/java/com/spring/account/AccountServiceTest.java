package com.spring.account;

import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @InjectMocks
  private AccountService accountService = new AccountService();
  @Mock
  private AccountRepository accountRepositoryMock;
  @Mock
  private PasswordEncoder passwordEncoder;

  @Test
  public void shouldInitializeWithTwoDemoUsers() {
    Account account1 = AccountBuilder.anAccount().setEmail("user").setPassword("demo")
        .setRole(Account.RoleConstants.USER.getRoleConstant()).setFirstName("userFirstName")
        .setLastName("userLastName").build();
    Account account2 = AccountBuilder.anAccount().setEmail("admin").setPassword("admin")
        .setRole(Account.RoleConstants.ADMIN.getRoleConstant()).setFirstName("adminFirstName")
        .setLastName("adminLastName").build();

    // act
    accountService.save(account1);
    accountService.save(account2);
    // assert
    verify(accountRepositoryMock, times(2)).save(any(Account.class));
  }

  @Test
  public void shouldThrowExceptionWhenUserNotFound() {
    // arrange
    thrown.expect(UsernameNotFoundException.class);
    thrown.expectMessage("User not found with the given username: user@example.com");

    when(accountRepositoryMock.findOneByEmail("user@example.com")).thenReturn(null);
    // act
    accountService.loadUserByUsername("user@example.com");
  }

  @Test
  public void shouldReturnUserDetails() {
    // arrange
    Account demoUser = AccountBuilder.anAccount().setEmail("user@example.com").setPassword("demo")
        .setRole(Account.RoleConstants.USER.getRoleConstant()).build();
    when(accountRepositoryMock.findOneByEmail("user@example.com")).thenReturn(demoUser);

    // act
    UserDetails userDetails = accountService.loadUserByUsername("user@example.com");

    // assert
    assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
    assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
    assertThat(hasAuthority(userDetails, demoUser.getRole())).isTrue();
  }

  private boolean hasAuthority(UserDetails userDetails, String role) {
    return userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .anyMatch(isEqual(role));
  }
}
