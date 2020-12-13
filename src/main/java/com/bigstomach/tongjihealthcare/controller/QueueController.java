package com.bigstomach.tongjihealthcare.controller;

import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.service.OrderService;
import com.bigstomach.tongjihealthcare.service.QueueService;
import com.bigstomach.tongjihealthcare.vo.OrderInQueueVO;
import com.bigstomach.tongjihealthcare.vo.QueueVO;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/queue")
public class QueueController {
    @Resource
    private QueueService queueService;
    @Resource
    private OrderService orderService;

    @GetMapping("getQueueOrder")
    public CommonResult<OrderInQueueVO> getQueueOrder(@CurrentUser String userId) {
        return CommonResult.success(queueService.getQueueOrder(Integer.valueOf(userId)));
    }


    @GetMapping("/getQueueInfo/{orderId}")
    @ResponseBody
    public CommonResult<List<QueueVO>> getQueueInfo(@PathVariable("orderId") Integer orderId) {
        return CommonResult.success(queueService.getQueueInfo(orderId));
    }

    @GetMapping("/checkInQueue/{orderId}")
    @ResponseBody
    public CommonResult<Boolean> checkInQueue(@PathVariable("orderId") Integer orderId) {
        return CommonResult.success(queueService.checkInQueue(orderId));
    }

    @GetMapping("/getCancelTimes/{orderId}")
    @ResponseBody
    public CommonResult<Integer> getCancelTimes(@PathVariable("orderId") Integer orderId) {
        return CommonResult.success(orderService.getOrder(orderId).getCancelTimes());
    }

    @PostMapping("/choose/{orderId}/{consultingId}")
    @ResponseBody
    public CommonResult<String> choose(@PathVariable("orderId") Integer orderId,
                                       @PathVariable("consultingId") Integer consultingId) {
        queueService.choose(orderId,consultingId);
        return CommonResult.success("选择成功");
    }

    @PostMapping("/cancel/{orderId}")
    @ResponseBody
    public CommonResult<String> cancel(@PathVariable("orderId") Integer orderId) {
        queueService.cancel(orderId);
        return CommonResult.success("取消排队成功");
    }

    @GetMapping("/getPeopleBefore/{orderId}")
    public CommonResult getPeopleBefore(@PathVariable("orderId") Integer orderId) {
        return CommonResult.success(queueService.getPeopleBefore(orderId));
    }


}
