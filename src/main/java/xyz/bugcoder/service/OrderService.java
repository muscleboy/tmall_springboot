package xyz.bugcoder.service;

import xyz.bugcoder.bean.Order;
import xyz.bugcoder.bean.OrderItem;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019-09-18 16:50
 * @Author: Wyj
 */
public interface OrderService {

    void add(Order o);
    void delete(int id);
    void update(Order o);
    Order get(int id);
    List<Order> list();
    List<Order> listByUid(int uid);
    // 计算订单的总价钱
    float getOrderTotal(Order o, List<OrderItem> ois);

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finished = "finished";
    String deleted = "deleted";
    String desc = "desc";

}
