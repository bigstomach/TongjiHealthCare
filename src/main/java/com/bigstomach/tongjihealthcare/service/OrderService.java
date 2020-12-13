package com.bigstomach.tongjihealthcare.service;


import com.bigstomach.tongjihealthcare.model.Order;
import com.bigstomach.tongjihealthcare.vo.OrderVO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void addOrder(Integer patientId, String department, String expertName, LocalDate date, Integer timeSlot, Integer payerId);

    List<OrderVO> getOrderListForMe(Integer userId);

    List<OrderVO> getOrderListForFamily(Integer userId);

    List<OrderVO> getTodayOrderList(Integer userId);

    Order getOrder(Integer orderId);

    void increaseCancelTimes(Integer orderId);

    void cancel(Integer orderId);
}
