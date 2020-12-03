package com.bigstomach.tongjihealthcare.controller;


import com.bigstomach.tongjihealthcare.annotation.AdminNeed;
import com.bigstomach.tongjihealthcare.annotation.JwtIgnore;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.UserQO;
import com.bigstomach.tongjihealthcare.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminNeed
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @JwtIgnore
    public CommonResult Login(@RequestBody UserQO userQO) {
        return CommonResult.success();
    }
}
