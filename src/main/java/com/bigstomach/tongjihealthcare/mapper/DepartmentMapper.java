package com.bigstomach.tongjihealthcare.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Integer getDepartmentId(String department, String expertName);

    List<String> getExpertName(String department);
}
