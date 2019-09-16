package xyz.bugcoder.service;

import xyz.bugcoder.bean.Category;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019/9/13 19:09
 * @Author: Wyj
 */
public interface CategoryService {

    void add(Category c);
    void delete(int id);
    void update(Category c);
    Category get(int id);
    List<Category> list();

}
