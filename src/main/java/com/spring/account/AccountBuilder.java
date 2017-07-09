package com.spring.account;

public class AccountBuilder implements IAccountBuilder{

    private Account account = new Account();

    public AccountBuilder(){

    }

    public AccountBuilder(String email,String password,String role){
        this.account.setEmail(email);
        this.account.setPassword(password);
        this.account.setRole(role);
    }

    @Override
    public Account build() {
        return this.account;
    }

    @Override
    public IAccountBuilder setEmail(String email) {
        this.account.setEmail(email);
        return this;
    }

    @Override
    public IAccountBuilder setPassword(String password) {
        this.account.setPassword(password);
        return this;
    }

    @Override
    public IAccountBuilder setRole(String role) {
        this.account.setRole(role);
        return this;
    }

    @Override
    public IAccountBuilder setLastName(String lastName) {
        this.account.setLastName(lastName);
        return this;
    }

    @Override
    public IAccountBuilder setFirstName(String firstName) {
        this.account.setFirstName(firstName);
        return this;
    }
}
