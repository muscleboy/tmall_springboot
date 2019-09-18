package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.bugcoder.bean.Order;
import xyz.bugcoder.service.OrderService;

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

    @GetMapping("/orders")
    public List<Order> listOrder(){

        return orderService.list();
    }

}
