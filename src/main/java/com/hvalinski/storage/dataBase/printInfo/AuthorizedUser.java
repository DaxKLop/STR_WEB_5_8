package com.hvalinski.storage.dataBase.printInfo;

public class AuthorizedUser {
    private boolean isAuthorized = false;
    private String role = "Пользователь";
    private Long user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public AuthorizedUser(boolean isAuthorized, String role, Long user_id) {
        this.isAuthorized = isAuthorized;
        this.role = role;
        this.user_id = user_id;
    }

    public AuthorizedUser() {
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
