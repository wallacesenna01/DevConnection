package com.br.wallaceartur.DevConnection.dtos;

public class UpdateProfileDto {

    private String name;
    private String bios;
    private String favTech;

    private String profilePictureUrl;
    private String coverPictureUrl;

    public  UpdateProfileDto () {}

    public UpdateProfileDto(String name, String bios, String favTech, String profilePictureUrl, String coverPictureUrl) {
        this.name = name;
        this.bios = bios;
        this.favTech = favTech;
        this.profilePictureUrl = profilePictureUrl;
        this.coverPictureUrl = coverPictureUrl;
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
