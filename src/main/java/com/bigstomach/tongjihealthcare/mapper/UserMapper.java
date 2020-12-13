package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> selectList();
    public User getUserByPhone(String phone);
    public void addUser(User user);
    public User getUserById(Integer userId);
}
