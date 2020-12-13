package com.bigstomach.tongjihealthcare.service;


import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.vo.OrderInQueueVO;
import com.bigstomach.tongjihealthcare.vo.OrderVO;
import com.bigstomach.tongjihealthcare.vo.QueueVO;

import java.util.List;

public interface QueueService {
    List<QueueVO> getQueueInfo(Integer orderId);

    Boolean checkInQueue(Integer orderId);

    void choose(Integer orderId, Integer consultingId);

    void cancel(Integer orderId);


    OrderInQueueVO getQueueOrder(Integer userId);

    Integer getPeopleBefore(Integer orderId);
}
