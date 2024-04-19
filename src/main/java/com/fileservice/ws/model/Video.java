package com.fileservice.ws.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private String fileName;
    private LocalDate timeCreate;
    public LocalDate getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDate timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getFileName() {
        return fileName;
    }
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
