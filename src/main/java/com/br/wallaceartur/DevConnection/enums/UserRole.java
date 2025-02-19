package com.br.wallaceartur.DevConnection.enums;

public enum UserRole {

    USER("USER_ROLE"),
    ADMIN("ADMIN_ROLE");


    private String userRole;


    UserRole(String role) {

    }

    public String getUserRole(){
        return userRole;
    }
}
