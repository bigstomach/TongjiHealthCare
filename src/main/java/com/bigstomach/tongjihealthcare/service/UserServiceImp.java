package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.common.exception.Asserts;
import com.bigstomach.tongjihealthcare.common.response.ResultCode;
import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.UserMapper;
import com.bigstomach.tongjihealthcare.model.Audience;
import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.util.JwtTokenUtil;
import com.bigstomach.tongjihealthcare.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private Audience audience;


    @Override
    public String login(String phoneNumber, String password) {
        String role = "user";
        String token = null;
        User user = userMapper.getUserByPhone(phoneNumber);
        if (user == null) Asserts.fail(ResultCode.FAILED_AUTHENTICATION);
        if (user.getPassword().equals(password)) {
            Integer userId = user.getId();
            token = JwtTokenUtil.createJWT(userId.toString(), phoneNumber, role, audience);
        }
        return token;
    }

    @Override
    public void register(String name, String idNumber, String phoneNumber, String password) {
        User user = userMapper.getUserByPhone(phoneNumber);
        if (user != null) Asserts.fail(ResultCode.RECORD_EXISTS);
        user = new User(idNumber, name, password, phoneNumber);
        userMapper.addUser(user);
    }

    @Override
    public UserVO getInfo(Integer userId) {
        return ObjectConverter.INSTANCE.user2UserVO(userMapper.getUserById(userId));
    }
}
