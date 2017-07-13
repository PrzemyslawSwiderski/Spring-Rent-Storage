package com.spring.account;

import com.spring.profile.ProfileForm;
import java.util.Collections;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public Account save(Account account) {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    Account savedAccount = accountRepository.save(account);
    return savedAccount;
  }

  public Account updateByEmailWithFormFields(@NotNull ProfileForm profileForm,
      String currentUserEmail) {
    Account accountToUpdate = findOneByEmail(currentUserEmail);
    profileForm.updateAccountFields(accountToUpdate);
    return accountToUpdate;
  }

  public boolean checkAccountIfExists(String email, Account accountToUpdate) {
    return !org.thymeleaf.util.StringUtils.equalsIgnoreCase(email, accountToUpdate.getEmail())
        && accountRepository.exists(accountToUpdate.getEmail());
  }

  @Transactional
  public Account findOneByEmail(@NotNull String email) {
    return accountRepository.findOneByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findOneByEmail(username);
    if (account == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return createUser(account);
  }

  public void signin(Account account) {
    SecurityContextHolder.getContext().setAuthentication(authenticate(account));
  }

  private Authentication authenticate(Account account) {
    return new UsernamePasswordAuthenticationToken(createUser(account), null,
        Collections.singleton(createAuthority(account)));
  }

  private User createUser(Account account) {
    return new User(account.getEmail(), account.getPassword(),
        Collections.singleton(createAuthority(account)));
  }

  private GrantedAuthority createAuthority(Account account) {
    return new SimpleGrantedAuthority(account.getRole());
  }

}
