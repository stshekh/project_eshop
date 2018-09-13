package com.gmail.sshekh.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private String unqueNumber;
    private BigDecimal price;
    private List<OrderDTO> orders=new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnqueNumber() {
        return unqueNumber;
    }

    public void setUnqueNumber(String unqueNumber) {
        this.unqueNumber = unqueNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
