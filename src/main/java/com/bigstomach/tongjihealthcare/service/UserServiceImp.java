package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.UserMapper;
import com.bigstomach.tongjihealthcare.model.Audience;
import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.util.JwtTokenUtil;
import com.bigstomach.tongjihealthcare.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Audience audience;


    @Override
    public List<UserVO> selectList() {
        ObjectConverter objectConverter=ObjectConverter.INSTANCE;
        return objectConverter.userList2UserVOList(userMapper.selectList());
    }

    @Override
    public String login(String username, String password) {
        String role = "user";
        String token = null;
        User user=userMapper.getUser(username);
        if (user.getPassword().equals(password)) {
            Long userId=user.getId();
            token= JwtTokenUtil.createJWT(userId.toString(), username, role, audience);
        }
        return token;
    }
}
