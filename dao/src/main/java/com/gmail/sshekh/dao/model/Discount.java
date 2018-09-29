package com.gmail.sshekh.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_DISCOUNT")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DISCOUNT_RATE")
    private BigDecimal rate;
    @Column(name = "EXPIRATION_DATE")
    private LocalDateTime expDate;

    //TODO create Unidirectional mapping with items
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "T_DISCOUNT_ITEM",
            joinColumns = {@JoinColumn(name = "DISCOUNT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")}
    )
    private Set<Item> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(getId(), discount.getId()) &&
                Objects.equals(getName(), discount.getName()) &&
                Objects.equals(getRate(), discount.getRate()) &&
                Objects.equals(getExpDate(), discount.getExpDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRate(), getExpDate());
    }
}
