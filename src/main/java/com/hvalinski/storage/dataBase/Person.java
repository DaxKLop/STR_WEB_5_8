package com.hvalinski.storage.dataBase;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "per_seq", sequenceName = "per_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_seq")
        private Long person_id;
    private String person_name, phone,mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Person(Long person_id, String person_name, String phone, String mail) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.phone = phone;
        this.mail = mail;
    }

    public Person( String person_name, String phone, String mail) {
        this.person_name = person_name;
        this.phone = phone;
        this.mail = mail;
    }

    public Person() {
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
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
}
