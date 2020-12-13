package com.bigstomach.tongjihealthcare.model;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Integer patientId;
    private Integer payerId;
    private Integer departmentId;
    private Integer status;
    private LocalDate date;
    private Integer timeSlot;
    private Integer cancelTimes;

    public Order(Integer patientId, Integer payerId, Integer departmentId, LocalDate date, Integer timeSlot) {
        this.patientId = patientId;
        this.payerId = payerId;
        this.departmentId = departmentId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.cancelTimes = 0;
        this.status = 1;
    }
}
