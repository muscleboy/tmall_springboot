package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Review;
import xyz.bugcoder.bean.ReviewExample;
import xyz.bugcoder.mapper.ReviewMapper;
import xyz.bugcoder.service.ReviewService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019-09-23 18:09
 * @Author: Wyj
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public void add(Review r) {

        reviewMapper.insert(r);
    }

    @Override
    public void delete(int id) {

        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pid) {

        ReviewExample example = new ReviewExample();
        example.createCriteria()
                .andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        return reviewMapper.selectByExample(example);
    }
}
