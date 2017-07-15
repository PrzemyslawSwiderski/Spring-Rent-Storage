package com.spring.storage;

import com.spring.account.Account;
import com.spring.location.Location;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "storage")
public class Storage implements java.io.Serializable {

  @Id
  @Column(name = "storage_id", unique = true, nullable = false)
  @GeneratedValue
  @Setter(AccessLevel.NONE)
  private Long id;

  @OneToOne(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Location location;

  @Column(name = "free_space", columnDefinition = "decimal", scale = 3)
  private BigDecimal freeSpace;

  @Column(name = "overall_space", columnDefinition = "decimal", scale = 3)
  private BigDecimal overallSpace;

  private String description;

  @Column(scale = 2)
  private BigDecimal price;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Account account;

  private Instant created = Instant.now();

  @Override
  public String toString() {
    return "Storage{" +
        "id=" + id +
        ", freeSpace=" + freeSpace +
        ", overallSpace=" + overallSpace +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", created=" + created +
        '}';
  }
}
