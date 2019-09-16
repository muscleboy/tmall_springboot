package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.ProductExample;
import xyz.bugcoder.mapper.ProductMapper;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.ProductService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019/9/14 09:42
 * @Author: Wyj
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public void add(Product p) {

        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {

        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {

        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {

        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);

        return p;
    }

    public void setCategory(Product p){

        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
    }

    public void setCategory(List<Product> ps){

        for (Product p : ps) {

            setCategory(p);
        }
    }

    @Override
    public List<Product> list(int cid) {

        ProductExample example = new ProductExample();
        example.createCriteria()
                .andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setCategory(result);

        return result;
    }
}
