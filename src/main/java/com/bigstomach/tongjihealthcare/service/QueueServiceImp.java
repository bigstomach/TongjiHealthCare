package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.common.exception.Asserts;
import com.bigstomach.tongjihealthcare.common.response.ResultCode;
import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.QueueMapper;
import com.bigstomach.tongjihealthcare.model.Choose;
import com.bigstomach.tongjihealthcare.model.Order;
import com.bigstomach.tongjihealthcare.vo.OrderInQueueVO;
import com.bigstomach.tongjihealthcare.vo.QueueVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QueueServiceImp implements QueueService{
    @Resource
    private QueueMapper queueMapper;
    @Resource
    private OrderService orderService;
    @Override
    public List<QueueVO> getQueueInfo(Integer orderId) {
        Order order=orderService.getOrder(orderId);
        return ObjectConverter.INSTANCE.queueInfoList2QueueVOList(queueMapper.getQueueInfo(order.getDepartmentId()));
    }

    @Override
    public Boolean checkInQueue(Integer orderId) {
        return queueMapper.getChooseByOrderId(orderId)!=null;
    }

    @Override
    public void choose(Integer orderId, Integer consultingId) {
        if (checkInQueue(orderId)!=null) Asserts.fail(ResultCode.ALREADY_IN_QUEUE);
        queueMapper.add(orderId,consultingId);
    }

    @Override
    @Transactional
    public void cancel(Integer orderId) {
        queueMapper.delete(orderId);
        orderService.increaseCancelTimes(orderId);
    }

    @Override
    public OrderInQueueVO getQueueOrder(Integer userId) {
        return ObjectConverter.INSTANCE.orderQueue2OrderQueueVO(queueMapper.getQueueOrder(userId));
    }

    @Override
    public Integer getPeopleBefore(Integer orderId) {
        Choose choose=queueMapper.getChooseByOrderId(orderId);
        if (choose==null) Asserts.fail("您暂未排队");
        return queueMapper.getPeopleBefore(choose.getConsultingId(),choose.getEnqueueTime());
    }

}
