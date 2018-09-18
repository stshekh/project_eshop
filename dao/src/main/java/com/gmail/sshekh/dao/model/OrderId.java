package com.gmail.sshekh.dao.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderId implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "ITEM_ID")
    private Long itemId;

    public OrderId(Long userId, Long itemId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    public OrderId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId itemId1 = (OrderId) o;
        return Objects.equals(userId, itemId1.userId) &&
                Objects.equals(itemId, itemId1.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, itemId);
    }
}
