package xyz.bugcoder.service;

import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.PropertyValue;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019-09-22 09:29
 * @Author: Wyj
 */
public interface PropertyValueService {

    void init(Product p);
    void update(PropertyValue pv);
    PropertyValue get(int pid, int ptid);
    // 根据PID获取到所有的属性值
    List<PropertyValue> list(int pid);

}
