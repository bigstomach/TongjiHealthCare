package com.bigstomach.tongjihealthcare.model;

import lombok.Data;

@Data public class AddFamily {
    private Integer UserId;
    private Integer FamilyId;
    private Integer is_creator;
    private String relation;
}
