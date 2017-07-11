package com.spring.account;

import com.spring.utils.IBuilder;

public interface IAccountBuilder extends IBuilder<Account> {

    IAccountBuilder setEmail(String email);

    IAccountBuilder setPassword(String password);

    IAccountBuilder setRole(String role);

    IAccountBuilder setLastName(String lastName);

    IAccountBuilder setFirstName(String firstName);
}
