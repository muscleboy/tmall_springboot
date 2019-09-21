package xyz.bugcoder.service;

import xyz.bugcoder.bean.ProductImage;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019-09-21 16:08
 * @Author: Wyj
 */
public interface ProductImageService {

    String type_single = "single";
    String type_detail = "detail";

    void add(ProductImage pi);
    void delete(int id);
    ProductImage get(int pid);
    List<ProductImage> listSingleImages(int pid);
    List<ProductImage> listDetailImages(int pid);

}
