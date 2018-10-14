package com.gmail.sshekh.service.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean enabled;
    private RoleDTO role;
    private ProfileDTO profile;
    private List<OrderDTO> orders = new ArrayList<>();
    private DiscountDTO discountDTO;
    private Set<CommentDTO> comments = new HashSet<>();
    private Set<BusinessCardDTO> businessCards = new HashSet<>();

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public DiscountDTO getDiscountDTO() {
        return discountDTO;
    }

    public void setDiscountDTO(DiscountDTO discountDTO) {
        this.discountDTO = discountDTO;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public Set<BusinessCardDTO> getBusinessCards() {
        return businessCards;
    }

    public void setBusinessCards(Set<BusinessCardDTO> businessCards) {
        this.businessCards = businessCards;
    }
}
