package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.ProductExample;
import xyz.bugcoder.bean.ProductImage;
import xyz.bugcoder.mapper.ProductMapper;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.ProductImageService;
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
    @Autowired
    ProductImageService productImageService;

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


    // 设置产品图片，默认显示第一张productSingle图片
    public void setProductImage(Product p){

        List<ProductImage> pis = productImageService.listSingleImages(p.getId());
        if (pis != null){

            p.setProductImage(pis.get(1));
        }
    }

    public void setProductImage(List<Product> ps){

        for (Product p : ps) {
            setProductImage(p);
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

    // 给产品设置分类
    public void setCategory(Product p){

        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
    }

    public void setCategory(List<Product> ps){

        for (Product p : ps) {

            setCategory(p);
        }
    }

    // 给分类设置产品
    @Override
    public void fill(Category c) {

        List<Product> ps = list(c.getId());
        setCategory(ps);
    }

    @Override
    public void fill(List<Category> cs) {

        for (Category c : cs) {
            fill(c);
        }
    }

}
