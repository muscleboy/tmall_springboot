package xyz.bugcoder.service;

import xyz.bugcoder.bean.Review;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019-09-23 18:08
 * @Author: Wyj
 */
public interface ReviewService {

    void add(Review r);
    void delete(int id);
    List<Review> list(int pid);

}
