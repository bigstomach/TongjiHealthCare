package com.bigstomach.tongjihealthcare.controller;

import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.FamilyQO;
import com.bigstomach.tongjihealthcare.qo.InFamilyQO;
import com.bigstomach.tongjihealthcare.service.FamilyService;
import com.bigstomach.tongjihealthcare.vo.FamilyMemberVO;
import com.bigstomach.tongjihealthcare.vo.FamilyVO;
import com.bigstomach.tongjihealthcare.vo.UserInFamiyVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Resource
    private FamilyService familyService;

    @GetMapping("/testInFamily")
    @ApiOperation("测试是否在家庭中")
    public CommonResult<Boolean> testInFamily(@CurrentUser String userId)
    {
        return CommonResult.success(familyService.testInFamily(Integer.valueOf(userId)));
    }

    @PostMapping("/createFamily")
    @ApiOperation("创建家庭")
    public CommonResult<Integer> createFamily(@CurrentUser String userId, @RequestBody FamilyQO familyQO)
    {
        Integer familyId=familyService.createFamily(Integer.valueOf(userId),familyQO.getName());
        return CommonResult.success(familyId);
    }

    @GetMapping("/getMyFamily")
    @ApiOperation("获取家庭信息")
    public CommonResult<FamilyVO> getMyFamily(@CurrentUser String userId)
    {
        return CommonResult.success(familyService.getMyFamily(Integer.valueOf(userId)));
    }

    @PostMapping("/addInFamily")
    @ApiOperation("加入家庭")
    public CommonResult<String> addInFamily(@CurrentUser String userId,@RequestBody InFamilyQO inFamilyQO)
    {
        Boolean result=familyService.addInFamily(Integer.valueOf(userId),inFamilyQO.getFamilyId(),inFamilyQO.getCreatorName(),inFamilyQO.getRelation());
        if (result)
        {
            return CommonResult.success("加入家庭成功");
        }
        else {
            return CommonResult.success("加入家庭失败");
        }
    }

    @GetMapping("/getFamilyMemberList")
    @ApiOperation("获取家庭成员列表")
    public CommonResult<List<FamilyMemberVO>> getFamilyMemberList(@CurrentUser String userId)
    {
        return CommonResult.success(familyService.getMemberList(Integer.valueOf(userId)));
    }

    @GetMapping("/getPatientName")
    @ApiOperation("获取就诊人姓名")
    public CommonResult<List<UserInFamiyVO>> getPatientName(@CurrentUser String userId)
    {
        return CommonResult.success(familyService.getPatientName(Integer.valueOf(userId)));
    }
}
