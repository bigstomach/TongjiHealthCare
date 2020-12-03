package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.vo.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> selectList();
    public String login(String username,String password);
}
