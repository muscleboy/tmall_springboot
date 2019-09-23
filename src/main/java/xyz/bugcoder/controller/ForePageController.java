package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.*;
import xyz.bugcoder.service.*;

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
    @Autowired
    ReviewService reviewService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;

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

    @RequestMapping("/foreproduct")
    public String foreproduct(int pid, Model m){

        Product p = productService.get(pid);
        List<ProductImage> singleImages = productImageService.listSingleImages(pid);
        List<ProductImage> detailImages = productImageService.listDetailImages(pid);
        List<Review> rs = reviewService.list(pid);
        List<Property> pts = propertyService.listByCid(p.getCid());
        List<PropertyValue> pvs = propertyValueService.list(pid);

        m.addAttribute("p", p);
        m.addAttribute("singleImages", singleImages);
        m.addAttribute("detailImages", detailImages);
        m.addAttribute("rs", rs);
        m.addAttribute("pts", pts);
        m.addAttribute("pvs", pvs);

        return "fore/product";
    }

}
