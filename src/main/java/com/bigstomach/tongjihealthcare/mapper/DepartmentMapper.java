package com.bigstomach.tongjihealthcare.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    Integer getDepartmentId(String department, String expertName);
}
