package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.ProductImageService;
import xyz.bugcoder.service.ProductService;

import java.util.List;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-23 09:43
 */
@Controller
public class ForePageController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @RequestMapping("/forehome")
    public String home(Model m){

        List<Category> cs = categoryService.list();
        productService.fill(cs);
        for (Category c : cs) {

            System.out.println(c);
        }
        m.addAttribute("cs", cs);

        return "fore/home";
    }



}
