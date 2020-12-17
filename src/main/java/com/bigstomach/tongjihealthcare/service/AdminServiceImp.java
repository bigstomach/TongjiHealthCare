package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.common.response.ResultCode;
import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.QueueMapper;
import com.bigstomach.tongjihealthcare.model.UserBefore;
import com.bigstomach.tongjihealthcare.model.UserInQueue;
import com.bigstomach.tongjihealthcare.vo.ConsultingRoomVO;
import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<UserInQueue> userInQueues=queueMapper.getUserInQueueList(consultingRoomId);
        RestTemplate restTemplate=new RestTemplate();
        List<UserBefore> userBeforeList=new ArrayList<>();
        for (int i=0;i<userInQueues.size();i++)
        {
            System.out.println("通知的userId:"+userInQueues.get(i).getUserId().toString()+"前面的人的个数："+i);
            userBeforeList.add(new UserBefore(userInQueues.get(i).getUserId().toString(),i));
        }
        restTemplate.postForEntity("http://39.106.53.1:3000/push",userBeforeList, String.class);
        return ObjectConverter.INSTANCE.userInQueueList2UserInQueueVOList(userInQueues);
    }
}
