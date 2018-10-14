package com.gmail.sshekh.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "T_BUSINESS_CARD")
public class BusinessCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "WORKING_TELEPHONE")
    private String workingTelephone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingTelephone() {
        return workingTelephone;
    }

    public void setWorkingTelephone(String workingTelephone) {
        this.workingTelephone = workingTelephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessCard that = (BusinessCard) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getWorkingTelephone(), that.getWorkingTelephone()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getFullName(), getWorkingTelephone(), getUser());
    }
}
