package com.gmail.sshekh.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_COMMENT")

public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMMENT")
    private Long idComment;
    @Column(name = "CONTENT", columnDefinition = "TEXT", length = 65535)
    private String content;
    @Column(name = "CREATED")
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "NEWS_ID", nullable = false)
    private News news;

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
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

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getIdComment(), comment.getIdComment()) &&
                Objects.equals(getContent(), comment.getContent()) &&
                Objects.equals(getCreated(), comment.getCreated()) &&
                Objects.equals(getNews(), comment.getNews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComment(), getContent(), getCreated(), getNews());
    }
}
