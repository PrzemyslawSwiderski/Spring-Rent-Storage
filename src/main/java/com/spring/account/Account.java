package com.spring.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.storage.Storage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
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
    @Setter(AccessLevel.NONE)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.PERSIST)
    private Set<Storage> storages = new HashSet<Storage>(
            0);

    protected Account() {

    }


    public String toString() {
        return String.format("com.spring.account.Account(id=%d, email=%s, firstName=%s, lastName=%s, password=%s, role=%s, created=%s)", this.getId(), this.getEmail(), this.getFirstName(), this.getLastName(), this.getPassword(), this.getRole(), this.getCreated());
    }


}
