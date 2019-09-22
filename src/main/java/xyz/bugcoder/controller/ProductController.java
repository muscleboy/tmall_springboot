package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.ProductImage;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.ProductImageService;
import xyz.bugcoder.service.ProductService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/14 09:46
 * @Author: Wyj
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    // 获取一个分类下的所有产品，所以应该用category
    @GetMapping("category/{cid}/products")
    public List<Product> listByCid(@PathVariable(value = "cid") int cid){

        List<Product> ps = productService.list(cid);
        for (Product p : ps) {

            List<ProductImage> pis = productImageService.listSingleImages(p.getId());
            if (pis.isEmpty()){

                System.out.println("111111111");
                ProductImage pi = productImageService.get(p.getId());
                p.setProductImage(pi);
                System.out.println(pi);
                return ps;
            }

            p.setProductImage(pis.get(0));
            System.out.println("22222222");
        }

        return ps;
    }

    // 新增产品
    @PostMapping("/products")
    public void add(@RequestBody Product p){

        productService.add(p);
        System.out.println(p);
    }

    // 更新产品，返回cid到前端
    @PutMapping("/products")
    public void update(@RequestBody Product p){

//        Category c = categoryService.get(p.getCid());
//        System.out.println(c.getId());
        productService.update(p);
    }

    // 获取分类
    @GetMapping("/categories/products/{pid}")
    public Category getCategory(@PathVariable(value = "pid")int pid){

        Product p = productService.get(pid);
        return categoryService.get(p.getCid());
    }

    // 获取产品
    @GetMapping("/products/{cid}")
    public Product get(@PathVariable(value = "cid") int cid){

        return productService.get(cid);
    }

    // 删除产品
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable(value = "id")int id){

        productService.delete(id);
    }

}
