package xyz.bugcoder.service;

import xyz.bugcoder.bean.Order;

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
//    List<Order> listByUidAndStatus(int uid, String excludeStatus);
//     设置订单项
//    void fill(Order o);
//    void fill(List<Order> o);

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finished = "finished";
    String deleted = "deleted";
    String desc = "desc";

}
