package com.spring.profile;

import com.spring.account.Account;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class ProfileForm {

  private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
  private static final String EMAIL_MESSAGE = "{email.message}";

  @NotBlank(message = NOT_BLANK_MESSAGE)
  @Email(message = EMAIL_MESSAGE)
  private String email;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String password;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String firstName;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String lastName;

  public ProfileForm() {

  }

  public ProfileForm(Account account) {
    setEmail(account.getEmail());
    setFirstName(account.getFirstName());
    setLastName(account.getLastName());
  }

  public void updateAccountFields(Account accountToUpdate) {
    accountToUpdate.setEmail(getEmail());
    accountToUpdate.setFirstName(getFirstName());
    accountToUpdate.setLastName(getLastName());
    accountToUpdate.setPassword(getPassword());
  }
}
