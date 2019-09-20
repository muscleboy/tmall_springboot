package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bugcoder.bean.Product;
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

    @GetMapping("categories/{cid}/products")
    public List<Product> listByCid(@PathVariable(value = "cid") int cid){

        return productService.list(cid);
    }

    @PostMapping("/products")
    public void add(@RequestBody Product p){

        productService.add(p);
    }

}
