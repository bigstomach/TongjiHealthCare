package com.bigstomach.tongjihealthcare.service;

import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.FamilyMapper;
import com.bigstomach.tongjihealthcare.model.AddFamily;
import com.bigstomach.tongjihealthcare.model.Family;
import com.bigstomach.tongjihealthcare.model.FamilyMember;
import com.bigstomach.tongjihealthcare.model.UserInFamily;
import com.bigstomach.tongjihealthcare.vo.FamilyMemberVO;
import com.bigstomach.tongjihealthcare.vo.FamilyVO;
import com.bigstomach.tongjihealthcare.vo.UserInFamiyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        addFamily.setRelation("创建者");
        familyMapper.AddInFamily(addFamily);
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

    @Override
    public Boolean addInFamily(Integer UserId, Integer familyId, String creatorName, String relation) {
        Integer familyIdfortest=familyMapper.getFamilyIdByCreator(creatorName);
        if (!familyIdfortest.equals(familyId))
        {
            //表示不一致，加入失败
            return Boolean.FALSE;
        }
        else{
            AddFamily addFamily=new AddFamily();
            addFamily.setUserId(UserId);
            addFamily.setFamilyId(familyId);
            addFamily.setIs_creator(0);
            addFamily.setRelation(relation);
            familyMapper.AddInFamily(addFamily);
            //表示成功加入家庭
            return Boolean.TRUE;
        }
    }

    @Override
    public List<FamilyMemberVO> getMemberList(Integer UserId) {
        List<Family> familyList=familyMapper.getFamily(UserId);
        if (familyList.size()>0) {
            Integer familyId=familyList.get(0).getId();
            List<FamilyMember> familyMemberList=familyMapper.getMemberList(familyId,UserId);
            familyMemberList.add(0,familyMapper.getMyFamilyInfo(UserId));
            return ObjectConverter.INSTANCE.familyMemberList2FamilyMemberVOList(familyMemberList);
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserInFamiyVO> getPatientName(Integer UserId) {
        List<Family> familyList=familyMapper.getFamily(UserId);
        if (familyList.size()>0) {
            Integer familyId=familyList.get(0).getId();
            List<FamilyMember> familyMemberList=familyMapper.getMemberList(familyId,UserId);
            familyMemberList.add(0,familyMapper.getMyFamilyInfo(UserId));
            return ObjectConverter.INSTANCE.familyMemberList2UserInFamilyList(familyMemberList);
        }
        else {
            UserInFamily userInFamily=familyMapper.getMyInfo(UserId);
            List<UserInFamiyVO> userInFamiyVOList=new ArrayList<>();
            userInFamiyVOList.add(ObjectConverter.INSTANCE.userInfamily2UserInfamilyVO(userInFamily));
            return userInFamiyVOList;
        }
    }


}
