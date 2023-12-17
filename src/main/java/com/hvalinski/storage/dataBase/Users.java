package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @SequenceGenerator(name = "use_seq", sequenceName = "use_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "use_seq")
    private Long user_id;
    private String login, password;


    public Users() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(Long user_id, String login, String password) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
    }

    public Users(String login, String password) {

        this.login = login;
        this.password = password;
    }
}
