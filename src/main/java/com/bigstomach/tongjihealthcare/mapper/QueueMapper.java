package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.*;
import org.apache.ibatis.annotations.*;


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

    List<Integer> getConsultingRoom(String departmentName);

    List<UserInQueue> getUserInQueueList(Integer id);

    Integer getOrderIdInQueueFirst(Integer consultingRoomId);

    void setOrderStatus(@Param("status")Integer status,@Param("orderId")Integer orderId);

    void deleteChoose(Integer orderId);
}
