package com.bigstomach.tongjihealthcare.convert;

import com.bigstomach.tongjihealthcare.model.OrderInQueue;
import com.bigstomach.tongjihealthcare.model.OrderInfo;
import com.bigstomach.tongjihealthcare.model.QueueInfo;
import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.vo.OrderInQueueVO;
import com.bigstomach.tongjihealthcare.vo.OrderVO;
import com.bigstomach.tongjihealthcare.vo.QueueVO;
import com.bigstomach.tongjihealthcare.vo.UserVO;
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

    List<UserVO> userList2UserVOList(List<User> userList);

    @Mapping(target = "status", expression = "java(com.bigstomach.tongjihealthcare.enums.OrderStatus.getStatusByCode(orderInfo.getStatus()).getDesc())")
    OrderVO orderInfo2OrderVO(OrderInfo orderInfo);

    @Mapping(target = "status", expression = "java(com.bigstomach.tongjihealthcare.enums.OrderStatus.getStatusByCode(orderInQueue.getStatus()).getDesc())")
    OrderInQueueVO orderQueue2OrderQueueVO(OrderInQueue orderInQueue);

    List<OrderVO> orderInfoList2OrderVOList(List<OrderInfo> orderList);

    List<QueueVO> queueInfoList2QueueVOList(List<QueueInfo> queueList);
}
