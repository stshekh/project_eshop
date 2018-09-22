package com.gmail.sshekh.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(getId(), orderDTO.getId()) &&
                Objects.equals(getCreated(), orderDTO.getCreated()) &&
                Objects.equals(getQuantity(), orderDTO.getQuantity()) &&
                Objects.equals(getUser(), orderDTO.getUser()) &&
                Objects.equals(getItem(), orderDTO.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreated(), getQuantity(), getUser(), getItem());
    }
}
