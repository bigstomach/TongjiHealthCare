package com.bigstomach.tongjihealthcare.controller;

import cn.hutool.json.JSONObject;
import com.bigstomach.tongjihealthcare.annotation.JwtIgnore;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;

import com.bigstomach.tongjihealthcare.model.User;
import com.bigstomach.tongjihealthcare.qo.UserQO;
import com.bigstomach.tongjihealthcare.service.UserService;
import com.bigstomach.tongjihealthcare.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public CommonResult addUser(@RequestBody User user) {
        return CommonResult.success(null);
    }

    @GetMapping("/selectList")
    public CommonResult<List<UserVO>> selectList() {
        return CommonResult.success(userService.selectList());
    }

    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }


    @PostMapping("/login")
    @JwtIgnore
    public CommonResult Login(@RequestBody UserQO userQO) {
        String token=userService.login(userQO.username,userQO.password);
        if (token==null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        JSONObject result = new JSONObject();
        result.put("token", token);
        return CommonResult.success(result);
    }


}
