package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.bugcoder.bean.Order;
import xyz.bugcoder.service.OrderItemService;
import xyz.bugcoder.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019-09-18 16:49
 * @Author: Wyj
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orders")
    public PageInfo<Order> listOrder(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "5") int size){

        start = start < 0 ? 0 : start;
        PageHelper.startPage(start, size, "id desc");
        List<Order> os = orderService.list();
        // 给订单设置订单项属性
        orderItemService.fill(os);
        PageInfo<Order> page = new PageInfo<>(os, 5);
        return page;
    }

    // 发货
    @GetMapping("/orders/delivery/{id}")
    public String delivery(@PathVariable(value = "id")int id){

        Order o = orderService.get(id);
        // 发货时间
        o.setDeliveryDate(new Date());
        // 订单状态：待收货
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);

        return "success";
    }

}
