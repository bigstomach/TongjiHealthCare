package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface FamilyMapper {
    List<Family> getFamily(Integer UserId);
    void createFamily(String familyName);
    Integer getFamilyId(String familyName);
    void AddInFamily(AddFamily addFamily);
    Integer getFamilyIdByCreator(String creatorName);
    List<FamilyMember> getMemberList(@Param("familyId") Integer familyId, @Param("UserId") Integer UserId);
    FamilyMember getMyFamilyInfo(Integer UserId);
    UserInFamily getMyInfo(Integer UserId);
}
