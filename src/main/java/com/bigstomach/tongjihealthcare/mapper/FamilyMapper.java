package com.bigstomach.tongjihealthcare.mapper;

import com.bigstomach.tongjihealthcare.model.AddFamily;
import com.bigstomach.tongjihealthcare.model.Family;
import com.bigstomach.tongjihealthcare.model.FamilyMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMapper {
    List<Family> getFamily(Integer UserId);
    void createFamily(String familyName);
    Integer getFamilyId(String familyName);
    void AddInFamily(AddFamily addFamily);
    Integer getFamilyIdByCreator(String creatorName);
    List<FamilyMember> getMemberList(Integer familyId);
}
