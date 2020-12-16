package com.bigstomach.tongjihealthcare.controller;


import com.bigstomach.tongjihealthcare.annotation.AdminNeed;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.ConsultingRoomQO;
import com.bigstomach.tongjihealthcare.qo.OrderStatusQO;
import com.bigstomach.tongjihealthcare.service.AdminService;
import com.bigstomach.tongjihealthcare.vo.ConsultingRoomVO;
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

    @PostMapping("/getConsultingRoomId")
    @ApiOperation("获取诊室id")
    public CommonResult<ConsultingRoomVO> getConsultingRoomId(@RequestBody ConsultingRoomQO consultingRoomQO)
    {
        return CommonResult.success(adminService.getConsultingRoomId(consultingRoomQO.getDepartment(),consultingRoomQO.getType()));
    }

    @GetMapping("/getQueueList/{id}")
    @ApiOperation("获取诊室中当前排队列表")
    public CommonResult<List<UserInQueueVO>> getQueueList(@PathVariable("id") Integer consultingRoomId)
    {
        return CommonResult.success(adminService.getConsultingRoom(consultingRoomId));
    }

    @PostMapping("/setQueueStatus")
    @ApiOperation("设置过号/就诊完成")
    public CommonResult<List<UserInQueueVO>> setQueueInvalid(@RequestBody OrderStatusQO orderStatusQO)
    {
        return CommonResult.success(adminService.setQueueStatus(orderStatusQO.getConsultingRoomId(),orderStatusQO.getStatusType()));
    }
}
