package com.gmail.sshekh.service.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private UserDTO user;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(getId(), newsDTO.getId()) &&
                Objects.equals(getTitle(), newsDTO.getTitle()) &&
                Objects.equals(getContent(), newsDTO.getContent()) &&
                Objects.equals(getCreated(), newsDTO.getCreated()) &&
                Objects.equals(getUser(), newsDTO.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getCreated(), getUser());
    }
}
