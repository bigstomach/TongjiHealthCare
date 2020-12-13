package com.bigstomach.tongjihealthcare.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CREATED(0, "已挂号"),
    CANCELED(1,"取消就诊"),
    ENDED(2,"结束就诊");



    private Integer code;

    private String desc;

    public static OrderStatus getStatusByCode(Integer code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return CREATED;
    }
}
