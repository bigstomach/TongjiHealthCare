package com.bigstomach.tongjihealthcare.controller;

import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.OrderQO;
import com.bigstomach.tongjihealthcare.service.OrderService;
import com.bigstomach.tongjihealthcare.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/addOrder")
    @ApiOperation(value = "创建订单")
    public  CommonResult addOrder(@RequestBody OrderQO orderQO, @CurrentUser String userId) {
        orderService.addOrder(orderQO.getPatientId(),orderQO.getDepartment(),orderQO.getExpertName(),orderQO.getDate(),orderQO.getTimeSlot(),Integer.valueOf(userId));
        return CommonResult.success("订单创建成功");
    }

    @GetMapping("/getOrderListForMe")
    public CommonResult<List<OrderVO>> getOrderListForMe(@CurrentUser String userId) {
        return CommonResult.success(orderService.getOrderListForMe(Integer.valueOf(userId)));
    }

    @GetMapping("/getOrderListForFamily")
    public CommonResult<List<OrderVO>> getOrderListForFamily(@CurrentUser String userId) {
        return CommonResult.success(orderService.getOrderListForFamily(Integer.valueOf(userId)));
    }

    @GetMapping("/getTodayOrderList")
    public CommonResult<List<OrderVO>> getTodayOrderList(@CurrentUser String userId) {
        return CommonResult.success(orderService.getTodayOrderList(Integer.valueOf(userId)));
    }


    @PostMapping("/cancel/{orderId}")
    public CommonResult cancel(@PathVariable("orderId") Integer orderId) {
        orderService.cancel(orderId);
        return CommonResult.success("放弃就诊成功");
    }
}
