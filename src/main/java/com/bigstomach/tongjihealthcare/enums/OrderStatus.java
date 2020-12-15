package com.bigstomach.tongjihealthcare.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CREATED(0, "已挂号"),
    SIGN_IN(1,"已签到"),
    CANCELED(2,"取消就诊"),
    ENDED(3,"结束就诊");



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
