package com.bigstomach.tongjihealthcare.controller;

import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.convert.ObjectConverter;
import com.bigstomach.tongjihealthcare.mapper.FamilyMapper;
import com.bigstomach.tongjihealthcare.qo.FamilyQO;
import com.bigstomach.tongjihealthcare.service.FamilyService;
import com.bigstomach.tongjihealthcare.vo.FamilyVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
