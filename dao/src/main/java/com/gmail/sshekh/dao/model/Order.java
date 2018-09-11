package com.gmail.sshekh.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_ORDER")
public class Order implements Serializable {

    @EmbeddedId
    private OrderId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("USER_ID")
    private User user;


    @Column(name = "CREATED")
    private LocalDateTime created;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public Order(User user, Item item) {
        this.user = user;
        this.item = item;
        this.id = new OrderId(user.getId(), item.getId());
    }

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(user, order.user) &&
                Objects.equals(item, order.item) &&
                Objects.equals(created, order.created) &&
                Objects.equals(quantity, order.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, item, created, quantity);
    }
}
