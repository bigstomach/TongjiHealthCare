package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.vo.ConsultingRoomVO;
import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    ConsultingRoomVO getConsultingRoomId(String departmentName, Integer type);
    List<UserInQueueVO> getConsultingRoom(Integer consultingRoomId);
    List<UserInQueueVO> setQueueStatus(Integer consultingRoomId,Integer statusType);
}
