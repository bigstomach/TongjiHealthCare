package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.model.FamilyMember;
import com.bigstomach.tongjihealthcare.vo.FamilyMemberVO;
import com.bigstomach.tongjihealthcare.vo.FamilyVO;

import java.util.List;

public interface FamilyService {
    public Boolean testInFamily(Integer UserId);
    public Integer createFamily(Integer UserId,String familyName);
    public FamilyVO getMyFamily(Integer UserId);
    public Boolean addInFamily(Integer UserId,Integer familyId,String creatorName,String relation);
    public List<FamilyMemberVO> getMemberList(Integer UserId);
}
