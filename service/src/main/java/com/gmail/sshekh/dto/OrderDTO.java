package com.gmail.sshekh.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private OrderIdDTO id;
    private LocalDateTime created;
    private Long quantity;
    private UserDTO user;
    private ItemDTO item;

    public OrderIdDTO getId() {
        return id;
    }

    public void setId(OrderIdDTO id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
