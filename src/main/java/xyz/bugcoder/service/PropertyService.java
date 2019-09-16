package xyz.bugcoder.service;

import xyz.bugcoder.bean.Property;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019/9/16 16:46
 * @Author: Wyj
 */
public interface PropertyService {

    void add(Property p);
    void delete(int id);
    void update(Property p);
    Property get(int id);
    List<Property> list();
    List<Property> listByCid(int cid);

}
