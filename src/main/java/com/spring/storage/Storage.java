package com.spring.storage;

import com.spring.account.Account;
import com.spring.location.Location;

import javax.persistence.*;
import java.time.Instant;

@SuppressWarnings("serial")
@Entity
@Table(name = "storage")
public class Storage implements java.io.Serializable {

    @Id
    @Column(name = "storage_id", unique = true, nullable = false)
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Location location;

    @Column(name = "free_space", precision = 6)
    private Float freeSpace;

    @Column(name = "overall_space", precision = 6)
    private Float overallSpace;

    private String description;

    @Column(precision = 2)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Instant created = Instant.now();

    public Instant getCreated() {
        return created;
    }

    public Long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Float getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Float freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Float getOverallSpace() {
        return overallSpace;
    }

    public void setOverallSpace(Float overallSpace) {
        this.overallSpace = overallSpace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
