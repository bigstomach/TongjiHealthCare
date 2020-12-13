package com.bigstomach.tongjihealthcare.model;

import lombok.Data;

import java.time.LocalDate;

@Data public class OrderInQueue {
    private Integer id;
    private String departmentName;
    private String expertName;
    private Integer status;
    private LocalDate date;
    private Integer timeSlot;
}
