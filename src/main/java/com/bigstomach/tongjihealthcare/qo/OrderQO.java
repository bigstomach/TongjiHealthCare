package com.bigstomach.tongjihealthcare.qo;

import lombok.Data;

import java.time.LocalDate;

@Data public class OrderQO {
    private String department;
    private String expertName;
    private String date;
    private Integer timeSlot;
    private Integer patientId;
}
