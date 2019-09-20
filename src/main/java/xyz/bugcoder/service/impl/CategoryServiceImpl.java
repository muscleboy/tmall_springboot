package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.CategoryExample;
import xyz.bugcoder.mapper.CategoryMapper;
import xyz.bugcoder.service.CategoryService;


import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl.impl
 * @Description:
 * @Date: 2019/9/13 19:09
 * @Author: Wyj
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(Category c) {

        categoryMapper.insert(c);
    }

    @Override
    public void delete(int id) {

        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Category c) {

        categoryMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Category get(int id) {

        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> list() {

        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }
}
