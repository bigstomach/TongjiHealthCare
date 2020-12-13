package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.Choose;
import com.bigstomach.tongjihealthcare.model.OrderInQueue;
import com.bigstomach.tongjihealthcare.model.QueueInfo;
import org.apache.ibatis.annotations.Mapper;


import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface QueueMapper {

    List<QueueInfo> getQueueInfo(Integer orderId);

    void add(Integer orderId, Integer consultingId);

    void delete(Integer orderId);

    OrderInQueue getQueueOrder(Integer userId);

    Choose getChooseByOrderId(Integer orderId);

    Integer getPeopleBefore(Integer consultingId, LocalDateTime enqueueTime);
}
