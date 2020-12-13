package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.vo.UserVO;

public interface UserService {
    String login(String phoneNumber, String password);

    void register(String name, String idNumber, String phoneNumber, String password);

    UserVO getInfo(Integer userId);
}
