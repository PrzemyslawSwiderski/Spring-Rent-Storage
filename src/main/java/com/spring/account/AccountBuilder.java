package com.spring.account;

import com.github.javafaker.Faker;
import com.spring.storage.Storage;
import java.util.ArrayList;
import java.util.List;

public final class AccountBuilder implements IAccountBuilder {

  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private String role = "ROLE_USER";
  private List<Storage> storages = new ArrayList<>();

  private AccountBuilder() {
  }

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  public AccountBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public AccountBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public AccountBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public AccountBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  public AccountBuilder setRole(String role) {
    this.role = role;
    return this;
  }

  public AccountBuilder setStorages(List<Storage> storages) {
    this.storages = storages;
    return this;
  }

  public Account build() {
    Account account = new Account();
    account.setEmail(email);
    account.setFirstName(firstName);
    account.setLastName(lastName);
    account.setPassword(password);
    account.setRole(role);
    account.setStorages(storages);
    return account;
  }

  @Override
  public Account generateExample() {
    Faker faker = new Faker();

    Account account = AccountBuilder.anAccount()
        .setEmail(faker.internet().emailAddress())
        .setPassword(faker.internet().password())
        .setRole(Account.RoleConstants.USER.getRoleConstant())
        .setFirstName(faker.name().firstName())
        .setLastName(faker.name().lastName()).build();

    return account;
  }
}
