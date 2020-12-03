package com.bigstomach.tongjihealthcare.convert;

import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ObjectConverter {

    ObjectConverter INSTANCE = Mappers.getMapper(ObjectConverter.class);

    public UserVO user2UserVO(User user);
    public List<UserVO> userList2UserVOList(List<User> userList);

}
