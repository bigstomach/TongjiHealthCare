package com.bigstomach.tongjihealthcare.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer patientId;
    private Integer departmentId;
    private Integer status;
    private Integer payerId;
    private Integer cancelTimes;
    private LocalDate date;
    private Integer timeSlot;


    public Order(Integer patientId, Integer payerId, Integer departmentId, LocalDate date, Integer timeSlot) {
        this.patientId = patientId;
        this.payerId = payerId;
        this.departmentId = departmentId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.cancelTimes = 0;
        this.status = 0;
    }
}
