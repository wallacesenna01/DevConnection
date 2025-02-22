package com.br.wallaceartur.DevConnection.enums;

public enum UserRole {

    USER("USER_ROLE"),
    ADMIN("ADMIN_ROLE");

    private final String userRole;

    UserRole(String role) {
        this.userRole = role;  // Atribuindo o valor do parâmetro à variável
    }

    public String getUserRole(){
        return userRole;
    }
}
