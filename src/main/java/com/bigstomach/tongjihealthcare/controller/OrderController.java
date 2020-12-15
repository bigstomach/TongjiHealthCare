package com.bigstomach.tongjihealthcare.controller;

import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import com.bigstomach.tongjihealthcare.common.response.CommonResult;
import com.bigstomach.tongjihealthcare.qo.DepartmentQO;
import com.bigstomach.tongjihealthcare.qo.OrderQO;
import com.bigstomach.tongjihealthcare.service.OrderService;
import com.bigstomach.tongjihealthcare.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/getExpertName")
    public CommonResult<List<String>> getExpertName(@RequestBody DepartmentQO departmentQO) {
        return CommonResult.success(orderService.getExpertName(departmentQO.getDepartment()));
    }

    @PostMapping("/addOrder")
    @ApiOperation(value = "创建订单")
    public  CommonResult<String> addOrder(@RequestBody OrderQO orderQO, @CurrentUser String userId) {
        orderService.addOrder(orderQO.getPatientId(),orderQO.getDepartment(),orderQO.getExpertName(),
                LocalDate.parse(orderQO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                orderQO.getTimeSlot(),Integer.valueOf(userId));
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
    public CommonResult<String> cancel(@PathVariable("orderId") Integer orderId) {
        orderService.cancel(orderId);
        return CommonResult.success("放弃就诊成功");
    }
}
