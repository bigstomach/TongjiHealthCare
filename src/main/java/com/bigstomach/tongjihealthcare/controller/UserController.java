package com.bigstomach.tongjihealthcare.controller;

import cn.hutool.json.JSONObject;
import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.annotation.JwtIgnore;
import com.bigstomach.tongjihealthcare.common.exception.Asserts;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;

import com.bigstomach.tongjihealthcare.common.response.ResultCode;
import com.bigstomach.tongjihealthcare.qo.RegisterQO;
import com.bigstomach.tongjihealthcare.qo.UserQO;
import com.bigstomach.tongjihealthcare.service.UserService;
import com.bigstomach.tongjihealthcare.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @JwtIgnore
    public CommonResult<JSONObject> login(@RequestBody UserQO userQO) {
        String token=userService.login(userQO.getPhoneNumber(),userQO.getPassword());
        JSONObject result = new JSONObject();
        result.put("token", token);
        return CommonResult.success(result);
    }

    @PostMapping("/register")
    @JwtIgnore
    public CommonResult<String> register(@RequestBody RegisterQO registerQO) {
        if (registerQO.getIdNumber().length()!=18||registerQO.getPhoneNumber().length()!=11)
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        userService.register(registerQO.getName(),registerQO.getIdNumber(),registerQO.getPhoneNumber(),registerQO.getPassword());
        return CommonResult.success("注册成功");
    }

    @GetMapping("/getInfo")
    public CommonResult<UserVO> getInfo(@CurrentUser String userId) {
        UserVO userVO=userService.getInfo(Integer.valueOf(userId));
        return CommonResult.success(userVO);
    }


}
