package com.spring.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.storage.Storage;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

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
  private String role = RoleConstants.USER.getRoleConstant();
  private Instant created = Instant.now();
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.PERSIST)
  private List<Storage> storages = new ArrayList<>();

  protected Account() {

  }

  public String toString() {
    return String.format(
        "com.spring.account.Account(id=%d, email=%s, firstName=%s, lastName=%s, password=%s, role=%s, created=%s)",
        this.getId(), this.getEmail(), this.getFirstName(), this.getLastName(), this.getPassword(),
        this.getRole(), this.getCreated());
  }


  public enum RoleConstants {
    CLIENT("ROLE_CLIENT"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String userProfileType;

    RoleConstants(String userProfileType) {
      this.userProfileType = userProfileType;
    }

    public String getRoleConstant() {
      return userProfileType;
    }

  }


}
