package xyz.bugcoder.service;

import xyz.bugcoder.bean.Order;
import xyz.bugcoder.bean.OrderItem;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019-09-18 19:35
 * @Author: Wyj
 */
public interface OrderItemService {

    void add(OrderItem oi);
    void delete(int id);
    void update(OrderItem oi);
    OrderItem get(int id);
    List<OrderItem> list();

    void fill(Order o);
    void fill(List<Order> os);

}
