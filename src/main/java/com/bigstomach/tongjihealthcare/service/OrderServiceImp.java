package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.enums.OrderStatus;
import com.bigstomach.tongjihealthcare.mapper.DepartmentMapper;
import com.bigstomach.tongjihealthcare.mapper.OrderMapper;
import com.bigstomach.tongjihealthcare.mapper.QueueMapper;
import com.bigstomach.tongjihealthcare.model.Order;
import com.bigstomach.tongjihealthcare.vo.OrderVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private QueueMapper queueMapper;

    @Override
    public void addOrder(Integer patientId, String department, String expertName, LocalDate date, Integer timeSlot, Integer payerId) {
        Integer departmentId=departmentMapper.getDepartmentId(department,expertName);
        patientId=payerId;
        Order order=new Order(patientId,payerId,departmentId,date,timeSlot);
        orderMapper.addOrder(order);
    }

    @Override
    public List<OrderVO> getOrderListForMe(Integer userId) {
        return ObjectConverter.INSTANCE.orderInfoList2OrderVOList(orderMapper.getOrderList(userId,null,null));
    }

    @Override
    public List<OrderVO> getOrderListForFamily(Integer userId) {
        return ObjectConverter.INSTANCE.orderInfoList2OrderVOList(orderMapper.getOrderList(null,userId,null));
    }

    @Override
    public List<OrderVO> getTodayOrderList(Integer userId) {
        return ObjectConverter.INSTANCE.orderInfoList2OrderVOList(orderMapper.getOrderList(userId,null,LocalDate.now()));
    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderMapper.getOrder(orderId);
    }

    @Override
    public void increaseCancelTimes(Integer orderId) {
        orderMapper.increaseCancelTimes(orderId);
    }

    @Override
    @Transactional
    public void cancel(Integer orderId) {
        queueMapper.delete(orderId);
        orderMapper.modifyStatus(orderId, OrderStatus.CANCELED.getCode());
    }

    @Override
    public List<String> getExpertName(String department) {
        return departmentMapper.getExpertName(department);
    }

    @Override
    public void signIn(Integer orderId) {
        orderMapper.modifyStatus(orderId,OrderStatus.SIGN_IN.getCode());
    }

}
