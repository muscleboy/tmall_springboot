package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Order;
import xyz.bugcoder.bean.OrderExample;
import xyz.bugcoder.bean.User;
import xyz.bugcoder.mapper.OrderMapper;
import xyz.bugcoder.service.OrderItemService;
import xyz.bugcoder.service.OrderService;
import xyz.bugcoder.service.UserService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019-09-18 16:51
 * @Author: Wyj
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;


    @Override
    public void add(Order o) {

        orderMapper.insertSelective(o);
    }

    @Override
    public void delete(int id) {

        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order o) {

        orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order get(int id) {

        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {

        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> os = orderMapper.selectByExample(example);
        // 哪个用户的订单
        setUser(os);
        return os;
    }

    public void setUser(Order o){

        User u = userService.get(o.getUid());
        o.setUser(u);
    }

    public void setUser(List<Order> os){

        for (Order o : os) {

            setUser(o);
        }
    }

}
