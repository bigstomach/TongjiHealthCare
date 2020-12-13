package com.bigstomach.tongjihealthcare.model;

import lombok.Data;

@Data public class User {
    private Integer  id;
    private String idNumber;
    private String name;
    private String password;
    private String phoneNumber;

    public User(String idNumber, String name, String password, String phoneNumber) {
        this.idNumber = idNumber;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
