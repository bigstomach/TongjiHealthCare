package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.FamilyMapper;
import com.bigstomach.tongjihealthcare.model.AddFamily;
import com.bigstomach.tongjihealthcare.model.Family;
import com.bigstomach.tongjihealthcare.vo.FamilyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FamilyServiceImp implements FamilyService {
    @Resource
    private FamilyMapper familyMapper;

    @Override
    public Boolean testInFamily(Integer UserId)
    {
        List<Family> familyList=familyMapper.getFamily(UserId);
        if (familyList.size()<=0)
        {
            return Boolean.FALSE;
        }
        else {
            System.out.println("加入的家庭名称："+familyList.get(0).getName());
            return Boolean.TRUE;
        }
    }

    @Override
    public Integer createFamily(Integer UserId,String familyName)
    {
        familyMapper.createFamily(familyName);
        Integer familyId=familyMapper.getFamilyId(familyName);
        AddFamily addFamily=new AddFamily();
        addFamily.setFamilyId(familyId);
        addFamily.setUserId(UserId);
        addFamily.setIs_creator(1);
        addFamily.setRelation("#");
        familyMapper.creatorAddInFamily(addFamily);
        return familyId;
    }

    @Override
    public FamilyVO getMyFamily(Integer UserId)
    {
        List<Family> familyList=familyMapper.getFamily(UserId);
        if (familyList.size()==1)
        {
            System.out.println("家庭信息："+familyList.get(0).getName());
            return ObjectConverter.INSTANCE.family2FamilyVO(familyList.get(0));
        }
        return null;
    }
}
