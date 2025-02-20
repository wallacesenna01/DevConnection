package com.br.wallaceartur.DevConnection.model;

import jakarta.persistence.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String bios;
    private String favTech;

    private String profilePictureUrl;
    private String coverPictureUrl;

    public UserProfile() {}

    public UserProfile(Long id, User user, String name, String bios, String favTech, String profilePictureUrl, String coverPictureUrl) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.bios = bios;
        this.favTech = favTech;
        this.profilePictureUrl = profilePictureUrl;
        this.coverPictureUrl = coverPictureUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public String getFavTech() {
        return favTech;
    }

    public void setFavTech(String favTech) {
        this.favTech = favTech;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }
}
