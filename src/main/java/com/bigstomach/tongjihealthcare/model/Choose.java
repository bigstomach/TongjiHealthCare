package com.bigstomach.tongjihealthcare.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data public class Choose {
    private Integer id;
    private Integer orderId;
    private Integer consultingId;
    private LocalDateTime enqueueTime;
}
