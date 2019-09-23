package xyz.bugcoder.service;

import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019/9/14 09:40
 * @Author: Wyj
 */
public interface ProductService {

    void add(Product p);
    void delete(int id);
    void update(Product p);
    Product get(int id);
    List<Product> list(int cid);

    void fill(Category c);
    void fill(List<Category> cs);
//    Category getCategoryByPid(int id);

}
