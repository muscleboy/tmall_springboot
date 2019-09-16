package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.User;
import xyz.bugcoder.service.ProductService;
import xyz.bugcoder.service.UserService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/13 18:43
 * @Author: Wyj
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @RequestMapping("/admin_user_list")
    public String user(Model m){

        PageHelper.startPage(0, 5, "id desc");
        List<User> us = userService.list();
//        List<Product> ps = productService.list();
//        PageInfo<Product> page = new PageInfo<>(ps, 5);
        m.addAttribute("us", us);
//        m.addAttribute("page", page);

        return "admin/listUser";
    }
}
