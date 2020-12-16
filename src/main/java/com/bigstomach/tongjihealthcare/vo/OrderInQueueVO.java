package com.bigstomach.tongjihealthcare.vo;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderInQueueVO {
    private Integer id;
    private String departmentName;
    private String expertName;
    private String status;
    private Integer consultingId;
}
