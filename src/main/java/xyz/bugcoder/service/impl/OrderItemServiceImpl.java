package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Order;
import xyz.bugcoder.bean.OrderItem;
import xyz.bugcoder.bean.OrderItemExample;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.mapper.OrderItemMapper;
import xyz.bugcoder.service.OrderItemService;
import xyz.bugcoder.service.ProductService;

import java.util.List;


/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019-09-18 19:36
 * @Author: Wyj
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem oi) {

        orderItemMapper.insert(oi);
    }

    @Override
    public void delete(int id) {

        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem oi) {

        orderItemMapper.updateByPrimaryKeySelective(oi);
    }

    @Override
    public OrderItem get(int id) {

        return orderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderItem> list() {

        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    // 设置订单的总价钱，总数量，订单项(设置产品)
    @Override
    public void fill(Order o) {

        OrderItemExample example = new OrderItemExample();
        example.createCriteria()
                .andOidEqualTo(o.getId());
        example.setOrderByClause("id");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        // 总价钱，总数量
        float total = 0;
        int totalNum = 0;
        for (OrderItem oi : ois) {

            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNum += oi.getNumber();
        }
        // 设置订单的总价钱，总数量，订单项
        o.setTotal(total);
        o.setTotalNum(totalNum);
        o.setOrderItemList(ois);
    }

    @Override
    public void fill(List<Order> os) {

        for (Order o : os) {
            fill(o);
        }
    }

    // 给订单项设置产品，后台订单页查看详情
    public void setProduct(OrderItem oi){

        Product p = productService.get(oi.getPid());
        oi.setProduct(p);
    }

    public void  setProduct(List<OrderItem> ois){

        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

}
