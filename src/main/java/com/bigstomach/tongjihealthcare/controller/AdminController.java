package com.bigstomach.tongjihealthcare.controller;


import com.bigstomach.tongjihealthcare.annotation.AdminNeed;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.ConsultingRoomQO;
import com.bigstomach.tongjihealthcare.service.AdminService;
import com.bigstomach.tongjihealthcare.vo.UserInQueueVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@AdminNeed
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/getQueueList")
    @ApiOperation("获取诊室中当前排队列表")
    public CommonResult<List<UserInQueueVO>> getQueueList(@RequestBody ConsultingRoomQO consultingRoomQO)
    {
        return CommonResult.success(adminService.getConsultingRoom(consultingRoomQO.getDepartment(), consultingRoomQO.getType()));
    }
}
