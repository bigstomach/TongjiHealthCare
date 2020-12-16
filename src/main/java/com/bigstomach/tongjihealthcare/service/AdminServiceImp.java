package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.QueueMapper;
import com.bigstomach.tongjihealthcare.model.UserInQueue;
import com.bigstomach.tongjihealthcare.vo.ConsultingRoomVO;
import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Resource
    private QueueMapper queueMapper;

    @Override
    public ConsultingRoomVO getConsultingRoomId(String departmentName, Integer type)
    {
        List<Integer> consultingRoomIdList=queueMapper.getConsultingRoom(departmentName);
        System.out.println("查出来的诊室号："+consultingRoomIdList.get(type));
        Integer consultingRoomId=consultingRoomIdList.get(type);
        ConsultingRoomVO consultingRoomVO=new ConsultingRoomVO();
        consultingRoomVO.setConsultingRoomId(consultingRoomId);
        return consultingRoomVO;
    }

    @Override
    public List<UserInQueueVO> getConsultingRoom(Integer consultingRoomId)
    {
        List<UserInQueue> queue=queueMapper.getUserInQueueList(consultingRoomId);
        System.out.println("查出的用户"+queue.get(0).getUserId()+queue.get(0).getUserName());
        return ObjectConverter.INSTANCE.userInQueueList2UserInQueueVOList(queue);
    }

    @Override
    public List<UserInQueueVO> setQueueStatus(Integer consultingRoomId,Integer statusType)
    {
        Integer orderId=queueMapper.getOrderIdInQueueFirst(consultingRoomId);
        System.out.println("查出的orderId"+orderId);
        switch (statusType)
        {
            case 0:{
                queueMapper.setOrderStatus(4,orderId);
                queueMapper.deleteChoose(orderId);
                break;
            }
            case 1:{
                queueMapper.setOrderStatus(3,orderId);
                queueMapper.deleteChoose(orderId);
                break;
            }
            default:{
            }
        }
        return ObjectConverter.INSTANCE.userInQueueList2UserInQueueVOList(queueMapper.getUserInQueueList(consultingRoomId));
    }
}
