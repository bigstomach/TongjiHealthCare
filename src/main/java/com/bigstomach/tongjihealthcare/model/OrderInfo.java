package com.bigstomach.tongjihealthcare.model;


import lombok.Data;

import java.time.LocalDate;

@Data public class OrderInfo {
    private Integer id;
    private String patientName;
    private String departmentName;
    private String expertName;
    private Integer status;
    private String payerName;
    private LocalDate date;
    private Integer timeSlot;
}
