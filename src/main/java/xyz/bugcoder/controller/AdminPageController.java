package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.ProductService;
import xyz.bugcoder.service.UserService;

import java.util.List;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-16 10:29
 */
@RestController
public class AdminPageController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/categories")
    public List<Category> listCategory(){

//        List<Product> ps = productService.list()
        return categoryService.list();
    }

}
