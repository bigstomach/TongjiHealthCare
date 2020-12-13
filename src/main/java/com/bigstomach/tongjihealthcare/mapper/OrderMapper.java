package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.Order;
import com.bigstomach.tongjihealthcare.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface OrderMapper {
    void addOrder(Order order);
    List<OrderInfo> getOrderList(Integer patientId, Integer payerId, LocalDate date);
    Order getOrder(Integer orderId);
    void increaseCancelTimes(Integer orderId);

    void cancel(Integer orderId);
}
