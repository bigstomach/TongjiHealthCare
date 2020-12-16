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

    @Select("select cr.id\n" +
            "from consulting_room cr join department d on cr.department_id=d.id\n" +
            "where d.level2=#{departmentName}\n" +
            "order by cr.id")
    List<Integer> getConsultingRoom(String departmentName);

    @Select("select u.id userId,u.name userName\n" +
            "from consulting_room cr join choose c join `order` o join user u on cr.id=c.consulting_id and c.order_id=o.id and o.patient_id=u.id\n" +
            "where cr.id=#{id}")
    @Results({
            @Result(column = "userId",property = "userId"),
            @Result(column = "userName",property = "userName")
    })
    List<UserInQueue> getUserInQueueList(Integer id);
}
