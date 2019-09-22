package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.ProductImage;
import xyz.bugcoder.bean.ProductImageExample;
import xyz.bugcoder.mapper.ProductImageMapper;
import xyz.bugcoder.service.ProductImageService;
import xyz.bugcoder.service.ProductService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019-09-21 16:11
 * @Author: Wyj
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(ProductImage pi) {

        productImageMapper.insert(pi);
    }

    @Override
    public void delete(int id) {

        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductImage get(int pid) {

////        type = "single";
        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid);
//                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        List<ProductImage> list = productImageMapper.selectByExample(example);
        return list.get(0);
    }

    public void setProduct(ProductImage pi){

        Product p = productService.get(pi.getPid());
        pi.setProduct(p);
    }

    public void setProduct(List<ProductImage> pis){

        for (ProductImage pi : pis) {

            setProduct(pi);
        }
    }

    // 获取一个产品的单个图片
    @Override
    public List<ProductImage> listSingleImages(int pid) {

        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type_single);
        example.setOrderByClause("id desc");
        List<ProductImage> singleImages = productImageMapper.selectByExample(example);
        return singleImages;
    }

    // 获取一个产品的详情图片
    @Override
    public List<ProductImage> listDetailImages(int pid) {

        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type_detail);
        example.setOrderByClause("id desc");
        List<ProductImage> detailImages = productImageMapper.selectByExample(example);
        return detailImages;
    }
}
