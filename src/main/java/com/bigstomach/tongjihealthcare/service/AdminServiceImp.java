package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.QueueMapper;
import com.bigstomach.tongjihealthcare.model.UserInQueue;
import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Resource
    private QueueMapper queueMapper;

    @Override
    public List<UserInQueueVO> getConsultingRoom(String departmentName, Integer type)
    {
        List<Integer> consultingRoomIdList=queueMapper.getConsultingRoom(departmentName);
        System.out.println("查出来的诊室号："+consultingRoomIdList.get(type));
        Integer consultingRoomId=consultingRoomIdList.get(type);
        List<UserInQueue> queue=queueMapper.getUserInQueueList(consultingRoomId);
        System.out.println("查出的用户"+queue.get(0).getUserId()+queue.get(0).getUserName());
        return ObjectConverter.INSTANCE.userInQueueList2UserInQueueVOList(queue);
    }
}
