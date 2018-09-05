package com.gmail.sshekh.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_AUDIT")

public class Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUDIT")
    private Long idAudit;
    @Column(name = "EVENT_TYPE", updatable = false, nullable = false)
    private String eventType;
    @Column(name = "CREATED")
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Long getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(Long idAudit) {
        this.idAudit = idAudit;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
        Audit audit = (Audit) o;
        return Objects.equals(idAudit, audit.idAudit) &&
                Objects.equals(eventType, audit.eventType) &&
                Objects.equals(created, audit.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAudit, eventType, created);
    }
}
