package com.spring.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.storage.Storage;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    public final class RoleConstants {
        private RoleConstants() {
        }

        public static final String CLIENT = "ROLE_CLIENT";
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    @Id
    @GeneratedValue
    @Column(name = "account_id", unique = true, nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Storage> storages = new HashSet<Storage>(
            0);

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

    public Set<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }

}
