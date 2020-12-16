package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.vo.FamilyVO;

public interface FamilyService {
    public Boolean testInFamily(Integer UserId);
    public Integer createFamily(Integer UserId,String familyName);
    public FamilyVO getMyFamily(Integer UserId);
}
