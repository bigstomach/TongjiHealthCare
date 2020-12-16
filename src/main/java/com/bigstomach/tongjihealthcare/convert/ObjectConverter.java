package com.bigstomach.tongjihealthcare.convert;

import com.bigstomach.tongjihealthcare.model.*;
import com.bigstomach.tongjihealthcare.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ObjectConverter {

    ObjectConverter INSTANCE = Mappers.getMapper(ObjectConverter.class);

    @Mapping(target = "sex", expression = "java(CustomMapping.getSex(user.getIdNumber()))")
    @Mapping(target = "age", expression = "java(CustomMapping.getAge(user.getIdNumber()))")
    UserVO user2UserVO(User user);

    @Mapping(target = "status", expression = "java(com.bigstomach.tongjihealthcare.enums.OrderStatus.getStatusByCode(orderInfo.getStatus()).getDesc())")
    OrderVO orderInfo2OrderVO(OrderInfo orderInfo);

    @Mapping(target = "status", expression = "java(com.bigstomach.tongjihealthcare.enums.OrderStatus.getStatusByCode(orderInQueue.getStatus()).getDesc())")
    OrderInQueueVO orderQueue2OrderQueueVO(OrderInQueue orderInQueue);

    List<OrderVO> orderInfoList2OrderVOList(List<OrderInfo> orderList);

    List<QueueVO> queueInfoList2QueueVOList(List<QueueInfo> queueList);

    List<UserInQueueVO> userInQueueList2UserInQueueVOList(List<UserInQueue> userInqueue);
}
