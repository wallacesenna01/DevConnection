package com.br.wallaceartur.DevConnection.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_bios")
public class UserBio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true )
    private User user;

    @Column(length = 500)
    private String bio;

    public UserBio() {}

    public UserBio(User user, String bio) {
        this.user = user;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
