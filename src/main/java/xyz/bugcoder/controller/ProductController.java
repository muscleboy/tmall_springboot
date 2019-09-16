package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/admin_list_product")
    public String listProduct(Model m){

//        PageHelper.startPage(0, 5,"id desc");
//        List<Product> ps = productService.list();
//        PageInfo<Product> page = new PageInfo<>(ps, 3);
//        m.addAttribute("ps", ps);
//        m.addAttribute("page", page);

        return "admin/listProduct";
    }
}
