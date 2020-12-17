package com.bigstomach.tongjihealthcare.model;

import lombok.Data;

@Data public class UserBefore {
    private String userId;
    private Integer peopleBefore;

    public UserBefore(String userId, Integer peopleBefore) {
        this.userId = userId;
        this.peopleBefore = peopleBefore;
    }
}
