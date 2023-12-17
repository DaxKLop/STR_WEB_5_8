package com.hvalinski.storage.dataBase.printInfo;

public class PrintUsers {
    private Long user_id;
    private String login, password, role_name, person_name, phone, mail;

    public PrintUsers() {
    }

    public PrintUsers(Long user_id, String login, String password, String role_name, String person_name, String phone, String mail) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role_name = role_name;
        this.person_name = person_name;
        this.phone = phone;
        this.mail = mail;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
