package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserInQueueVO> getConsultingRoom(String departmentName, Integer type);
}
