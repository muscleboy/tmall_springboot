package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.service.CategoryService;
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

    @GetMapping("categories/{cid}/products")
    public List<Product> listByCid(@PathVariable(value = "cid") int cid){

        return productService.list(cid);
    }

    // 新增产品
    @PostMapping("/products")
    public void add(@RequestBody Product p){

        productService.add(p);
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
    @GetMapping("/products/{id}")
    public Product get(@PathVariable(value = "id") int id){

        return productService.get(id);
    }

}
