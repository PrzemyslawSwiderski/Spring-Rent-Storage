package com.spring.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.utils.Builder;

import javax.persistence.*;
import java.time.Instant;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    public final class RoleConstants {
        private RoleConstants(){}

        public static final String CLIENT="ROLE_CLIENT";
        public static final String USER="ROLE_USER";
        public static final String ADMIN="ROLE_ADMIN";
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    private String password;

    private String role = "ROLE_USER";

    private Instant created = Instant.now();

    protected Account() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreated() {
        return created;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
