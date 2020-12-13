package com.bigstomach.tongjihealthcare.vo;


import lombok.Data;

import java.time.LocalDate;

@Data public class OrderVO {
    private Integer id;
    private String patientName;
    private String departmentName;
    private String expertName;
    private String status;
    private String payerName;
    private LocalDate date;
    private Integer timeSlot;
}
